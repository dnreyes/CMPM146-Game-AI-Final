package brandsma.kas.mazemod.world.gen;

import brandsma.kas.mazemod.world.gen.Cell;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.world.World;

public class GenerateMaze {

   private List<Cell> bordercells = new ArrayList();
   private List<List<Cell>> cellsX = new ArrayList();
   private int mazesize;
   private int cellsize;
   private int Xoffset;
   private int Yoffset;
   private int Zoffset;
   private World world;


   public GenerateMaze(World world, Random random, int startX, int startZ, int mazesize, int cellsize, int Xoffset, int Yoffset, int Zoffset, int metadata) {
      this.world = world;
      this.mazesize = mazesize;
      this.cellsize = cellsize;
      this.Xoffset = Xoffset;
      this.Yoffset = Yoffset;
      this.Zoffset = Zoffset;

      int j;
      int i;
      for(int currentcell = 0; currentcell < mazesize; ++currentcell) {
         ArrayList surroundingcells = new ArrayList();

         for(int surroundingcellsoutsidemaze = 0; surroundingcellsoutsidemaze < mazesize; ++surroundingcellsoutsidemaze) {
            surroundingcells.add(new Cell(currentcell, surroundingcellsoutsidemaze));

            for(j = Yoffset; j < Yoffset + 3; ++j) {
               for(i = Xoffset + currentcell * 3; i < Xoffset + currentcell * 3 + cellsize - 2; ++i) {
                  for(int putZ = Zoffset + surroundingcellsoutsidemaze * 3; putZ < Zoffset + surroundingcellsoutsidemaze * 3 + cellsize - 2; ++putZ) {
                     world.func_147468_f(i, j, putZ);
                  }
               }
            }
         }

         this.cellsX.add(surroundingcells);
      }

      Cell var17 = this.getCellAt(startX, startZ);
      this.bordercells.addAll(this.getSurroundingCellsOutsideMaze(var17));
      var17.addToMaze();

      for(; !this.bordercells.isEmpty(); this.bordercells.remove(var17)) {
         var17 = (Cell)this.bordercells.get(random.nextInt(this.bordercells.size()));
         List var18 = this.getSurroundingCellsInMaze(var17);
         if(!var18.isEmpty()) {
            this.connect(var17, (Cell)var18.get(random.nextInt(var18.size())));
            var17.addToMaze();
            List var19 = this.getSurroundingCellsOutsideMaze(var17);
            j = var19.size();

            for(i = 0; i < j; ++i) {
               if(this.bordercells.contains(var19.get(i))) {
                  var19.remove(i);
                  --i;
                  --j;
               } else {
                  this.bordercells.add(var19.get(i));
               }
            }

            var19.clear();
            var18.clear();
         } else {
            System.out.println("Surroundingcells list was empty. currentcell: " + var17.getX() + " " + var17.getZ() + " surroundingcells: " + ((Cell)var18.get(0)).getX() + " " + ((Cell)var18.get(0)).getZ());
         }
      }

   }

   public Cell getCellAt(int x, int z) {
      if(x >= 0 && z >= 0 && x < this.mazesize && z < this.mazesize) {
         return (Cell)((List)this.cellsX.get(x)).get(z);
      } else {
         Cell invalid = new Cell(-1, -1);
         invalid.setInvalid();
         return invalid;
      }
   }

   private void connect(Cell cell1, Cell cell2) {
      int putX;
      int putZ;
      int putY;
      if(cell1.getX() == cell2.getX()) {
         if(cell1.getZ() == cell2.getZ() - 1) {
            putX = this.Zoffset + (cell1.getZ() + 1) * (this.cellsize - 1) - 1;

            for(putZ = this.Xoffset + cell1.getX() * (this.cellsize - 1); putZ < this.Xoffset + cell1.getX() * (this.cellsize - 1) + 2; ++putZ) {
               for(putY = this.Yoffset; putY < this.Yoffset + 3; ++putY) {
                  this.world.func_147468_f(putZ, putY, putX);
               }
            }
         } else {
            putX = this.Zoffset + cell1.getZ() * (this.cellsize - 1) - 1;

            for(putZ = this.Xoffset + cell1.getX() * (this.cellsize - 1); putZ < this.Xoffset + cell1.getX() * (this.cellsize - 1) + 2; ++putZ) {
               for(putY = this.Yoffset; putY < this.Yoffset + 3; ++putY) {
                  this.world.func_147468_f(putZ, putY, putX);
               }
            }
         }
      } else {
         if(cell1.getZ() != cell2.getZ()) {
            System.out.println("The cells passed in connect(Cell, Cell), were not adjecent. " + cell1.getX() + " " + cell1.getZ() + " " + cell2.getX() + " " + cell2.getZ());
            return;
         }

         if(cell1.getX() == cell2.getX() - 1) {
            putX = this.Xoffset + (cell1.getX() + 1) * (this.cellsize - 1) - 1;

            for(putZ = this.Zoffset + cell1.getZ() * (this.cellsize - 1); putZ < this.Zoffset + cell1.getZ() * (this.cellsize - 1) + 2; ++putZ) {
               for(putY = this.Yoffset; putY < this.Yoffset + 3; ++putY) {
                  this.world.func_147468_f(putX, putY, putZ);
               }
            }
         } else {
            putX = this.Xoffset + cell1.getX() * (this.cellsize - 1) - 1;

            for(putZ = this.Zoffset + cell1.getZ() * (this.cellsize - 1); putZ < this.Zoffset + cell1.getZ() * (this.cellsize - 1) + 2; ++putZ) {
               for(putY = this.Yoffset; putY < this.Yoffset + 3; ++putY) {
                  this.world.func_147468_f(putX, putY, putZ);
               }
            }
         }
      }

   }

   private List<Cell> getSurroundingCellsOutsideMaze(Cell currentcell) {
      ArrayList surroundingcells = new ArrayList();
      int x = currentcell.getX();
      int z = currentcell.getZ();
      surroundingcells.add(this.getCellAt(x - 1, z));
      surroundingcells.add(this.getCellAt(x, z + 1));
      surroundingcells.add(this.getCellAt(x + 1, z));
      surroundingcells.add(this.getCellAt(x, z - 1));
      int j = 4;

      for(int i = 0; i < j; ++i) {
         if(((Cell)surroundingcells.get(i)).isInMaze() || ((Cell)surroundingcells.get(i)).invalid) {
            surroundingcells.remove(i);
            --i;
            --j;
         }
      }

      return surroundingcells;
   }

   private List<Cell> getSurroundingCellsInMaze(Cell currentcell) {
      ArrayList surroundingcells = new ArrayList();
      int x = currentcell.getX();
      int z = currentcell.getZ();
      surroundingcells.add(this.getCellAt(x - 1, z));
      surroundingcells.add(this.getCellAt(x, z + 1));
      surroundingcells.add(this.getCellAt(x + 1, z));
      surroundingcells.add(this.getCellAt(x, z - 1));
      int j = 4;

      for(int i = 0; i < j; ++i) {
         if(!((Cell)surroundingcells.get(i)).isInMaze() || ((Cell)surroundingcells.get(i)).invalid) {
            surroundingcells.remove(i);
            --i;
            --j;
         }
      }

      return surroundingcells;
   }
}
