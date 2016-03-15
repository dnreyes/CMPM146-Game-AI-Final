package brandsma.kas.mazemod.world.gen;

import brandsma.kas.mazemod.MazeMod;
import brandsma.kas.mazemod.block.ModBlocks;
import brandsma.kas.mazemod.world.gen.GenerateMaze;
import brandsma.kas.mazemod.world.gen.GenerateSpawners;
import brandsma.kas.mazemod.world.gen.GenerateTemple;
import brandsma.kas.mazemod.world.gen.GenerateTreasureRoomJungle;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class GenerateTempleJungle extends GenerateTemple {

   private int metadata = 1;
   private int maxY;


   public GenerateTempleJungle(World world, Random random) {
      super(world, random);
   }

   public void callGeneration(int BlockX, int BlockZ, boolean forceGeneration) {
      if(forceGeneration) {
         super.setup(BiomeGenBase.field_76782_w, BlockX, BlockZ);
         this.generatePyramid();
      } else if(super.setup(BiomeGenBase.field_76782_w, BlockX, BlockZ)) {
         this.generatePyramid();
      }

   }

   private void generatePyramid() {
      int baseheight = Math.min(Math.min(this.corner1[1], this.corner2[1]), Math.min(this.corner3[1], this.corner4[1]));
      MazeMod.notify("Generating a jungle pyramid At: X " + this.corner1[0] + ", Y " + baseheight + ", Z " + this.corner1[2]);
      MazeMod.notify("Full coordinate overview of all corners: " + this.corner1[0] + ", " + this.corner1[1] + ", " + this.corner1[2] + " || " + this.corner2[0] + ", " + this.corner2[1] + ", " + this.corner2[2] + " || " + this.corner3[0] + ", " + this.corner3[1] + ", " + this.corner3[2] + " || " + this.corner4[0] + ", " + this.corner4[1] + ", " + this.corner4[2] + ".");
      this.generateFoundation(baseheight, this.metadata);
      this.generateStairs(baseheight + this.foundationheight);
      new GenerateMaze(this.world, this.random, 0, Math.round((float)(this.mazesize / 2)), this.mazesize, this.cellsize, this.corner1[0] + this.foundationheight + this.pathwidth + this.cellsize, baseheight + this.foundationheight, this.corner1[2] + this.foundationheight + this.pathwidth + this.cellsize, 0);
      new GenerateSpawners(this.world, this.random, this.mazesize, this.cellsize, this.corner1[0] + this.foundationheight + this.pathwidth + this.cellsize, baseheight + this.foundationheight, this.corner1[2] + this.foundationheight + this.pathwidth + this.cellsize, 10, 3);

      for(int layer = 1; this.margin * layer * 2 < this.mazesize - 4; ++layer) {
         int Zstart = this.random.nextInt(this.mazesize - this.margin * layer * 2);
         int Xstart;
         if(layer % 2 == 0) {
            Xstart = 0;
         } else {
            Xstart = this.mazesize - this.margin * layer * 2 - 1;
         }

         int Xoffset = this.corner1[0] + this.foundationheight + this.pathwidth + 1 + (this.cellsize - 1) * (1 + this.margin * layer);
         int Zoffset = this.corner1[2] + this.foundationheight + this.pathwidth + 1 + (this.cellsize - 1) * (1 + this.margin * layer);
         this.generateLadderDown(Xstart, baseheight + this.foundationheight + layer * 4, Zstart, Xoffset, Zoffset, this.metadata);
         new GenerateMaze(this.world, this.random, Xstart, Zstart, this.mazesize - this.margin * layer * 2, this.cellsize, Xoffset, baseheight + this.foundationheight + layer * 4, Zoffset, this.metadata);
         new GenerateSpawners(this.world, this.random, this.mazesize - this.margin * layer * 2, this.cellsize, Xoffset, baseheight + this.foundationheight + layer * 4, Zoffset, Math.round((float)(6 / layer)), 3 - layer);
      }

      new GenerateTreasureRoomJungle(this.world, this.random, new int[]{this.corner1[0], baseheight, this.corner1[2]}, this.mazesize, this.cellsize, this.foundationheight, this.pathwidth, baseheight + 1);
      this.generateLadderDown(Math.round((float)(this.mazesize / 2)), baseheight + this.foundationheight, Math.round((float)(this.mazesize / 2)), this.corner1[0] + this.foundationheight + this.pathwidth + this.cellsize, this.corner1[2] + this.foundationheight + this.pathwidth + this.cellsize, this.metadata);
      this.generateLadderFromTop();
   }

   private void generateStairs(int baseheight) {
      int putX = this.corner1[0] + this.foundationheight + this.pathwidth + Math.round((float)(this.size / 2)) - 1;
      int putZ = this.corner1[2] + this.foundationheight + this.pathwidth - 1;

      int putY;
      for(putY = baseheight; putY < baseheight + this.size / 2; ++putY) {
         this.world.func_147465_d(putX - 1, putY, putZ, ModBlocks.stairsmossycobblestone, 2, 2);
         this.world.func_147465_d(putX, putY, putZ, ModBlocks.stairsmossycobblestone, 2, 2);
         this.world.func_147465_d(putX + 1, putY, putZ, ModBlocks.stairsmossycobblestone, 2, 2);
         this.world.func_147465_d(putX + 2, putY, putZ, ModBlocks.stairsmossycobblestone, 2, 2);
         ++putZ;
      }

      putX = this.corner1[0] + this.foundationheight + this.pathwidth + this.size;
      putZ = this.corner1[2] + this.foundationheight + this.pathwidth + Math.round((float)(this.size / 2)) - 1;

      for(putY = baseheight; putY < baseheight + this.size / 2; ++putY) {
         this.world.func_147465_d(putX, putY, putZ - 1, ModBlocks.stairsmossycobblestone, 1, 2);
         this.world.func_147465_d(putX, putY, putZ, ModBlocks.stairsmossycobblestone, 1, 2);
         this.world.func_147465_d(putX, putY, putZ + 1, ModBlocks.stairsmossycobblestone, 1, 2);
         this.world.func_147465_d(putX, putY, putZ + 2, ModBlocks.stairsmossycobblestone, 1, 2);
         --putX;
      }

      putX = this.corner1[0] + this.foundationheight + this.pathwidth + Math.round((float)(this.size / 2)) - 1;
      putZ = this.corner1[2] + this.foundationheight + this.pathwidth + this.size;

      for(putY = baseheight; putY < baseheight + this.size / 2; ++putY) {
         this.world.func_147465_d(putX - 1, putY, putZ, ModBlocks.stairsmossycobblestone, 3, 2);
         this.world.func_147465_d(putX, putY, putZ, ModBlocks.stairsmossycobblestone, 3, 2);
         this.world.func_147465_d(putX + 1, putY, putZ, ModBlocks.stairsmossycobblestone, 3, 2);
         this.world.func_147465_d(putX + 2, putY, putZ, ModBlocks.stairsmossycobblestone, 3, 2);
         --putZ;
      }

      putX = this.corner1[0] + this.foundationheight + this.pathwidth - 1;
      putZ = this.corner1[2] + this.foundationheight + this.pathwidth + Math.round((float)(this.size / 2)) - 1;

      for(putY = baseheight; putY < baseheight + this.size / 2; ++putY) {
         this.world.func_147465_d(putX, putY, putZ - 1, ModBlocks.stairsmossycobblestone, 0, 2);
         this.world.func_147465_d(putX, putY, putZ, ModBlocks.stairsmossycobblestone, 0, 2);
         this.world.func_147465_d(putX, putY, putZ + 1, ModBlocks.stairsmossycobblestone, 0, 2);
         this.world.func_147465_d(putX, putY, putZ + 2, ModBlocks.stairsmossycobblestone, 0, 2);
         ++putX;
      }

      this.maxY = putY;
      int x = this.corner1[0] + this.foundationheight + this.pathwidth + Math.round((float)(this.size / 2)) - 4;
      int z = this.corner1[2] + this.foundationheight + this.pathwidth + Math.round((float)(this.size / 2)) - 4;

      for(putY = this.maxY - 4; putY < this.maxY; ++putY) {
         for(putX = x - 2; putX < x + 12; ++putX) {
            for(putZ = z - 2; putZ < z + 12; ++putZ) {
               this.world.func_147468_f(putX, putY, putZ);
            }
         }
      }

      for(putY = this.maxY - 4; putY < this.maxY; ++putY) {
         for(putX = x; putX < x + 8; ++putX) {
            for(putZ = z; putZ < z + 8; ++putZ) {
               this.world.func_147465_d(putX, putY, putZ, ModBlocks.mazestone, this.metadata, 2);
            }
         }
      }

      for(putY = this.maxY - 4; putY < this.maxY - 1; ++putY) {
         for(putX = x + 1; putX < x + 7; ++putX) {
            for(putZ = z + 1; putZ < z + 7; ++putZ) {
               this.world.func_147468_f(putX, putY, putZ);
            }
         }
      }

      for(putY = this.maxY - 4; putY < this.maxY - 2; ++putY) {
         this.world.func_147468_f(x + 3, putY, z);
         this.world.func_147468_f(x + 4, putY, z);
         this.world.func_147468_f(x + 7, putY, z + 3);
         this.world.func_147468_f(x + 7, putY, z + 4);
         this.world.func_147468_f(x + 3, putY, z + 7);
         this.world.func_147468_f(x + 4, putY, z + 7);
         this.world.func_147468_f(x, putY, z + 3);
         this.world.func_147468_f(x, putY, z + 4);
      }

   }

   private void generateLadderFromTop() {
      int putX = this.corner1[0] + this.foundationheight + this.pathwidth + Math.round((float)(this.size / 2)) - 4 + 4;
      int putY = this.maxY - 5;
      int putZ = this.corner1[2] + this.foundationheight + this.pathwidth + Math.round((float)(this.size / 2)) - 4 + 3;

      do {
         this.world.func_147465_d(putX, putY--, putZ + 1, Blocks.field_150468_ap, 3, 2);
      } while(this.world.func_147439_a(putX, putY, putZ) != Blocks.field_150350_a);

      do {
         this.world.func_147465_d(putX, putY, putZ, ModBlocks.mazestone, this.metadata, 2);
         this.world.func_147465_d(putX, putY--, putZ + 1, Blocks.field_150468_ap, 3, 2);
      } while(this.world.func_147439_a(putX, putY, putZ) == Blocks.field_150350_a);

   }
}
