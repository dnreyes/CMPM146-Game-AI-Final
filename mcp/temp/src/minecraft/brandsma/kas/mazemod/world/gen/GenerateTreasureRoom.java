package brandsma.kas.mazemod.world.gen;

import brandsma.kas.mazemod.lib.Reference;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public class GenerateTreasureRoom {

   protected World world;
   protected Random random;
   protected int[] corner1;
   protected int mazesize;
   protected int cellsize;
   protected int foundationheight;
   protected int pathwidth;
   protected int baseheight;
   protected int startpointaddon;


   public GenerateTreasureRoom(World world, Random random, int[] corner1, int mazesize, int cellsize, int foundationheight, int pathwidth, int baseheight) {
      this.corner1 = corner1;
      this.world = world;
      this.random = random;
      this.mazesize = mazesize;
      this.cellsize = cellsize;
      this.foundationheight = foundationheight;
      this.pathwidth = pathwidth;
      this.baseheight = baseheight;
      this.startpointaddon = foundationheight + pathwidth + 1 + (cellsize - 1) * (Math.round((float)(mazesize / 2)) - (Reference.roomsize - 1) / 2 + 1);

      for(int putY = baseheight; putY < baseheight + 3; ++putY) {
         for(int putX = corner1[0] + this.startpointaddon; putX < corner1[0] + this.startpointaddon + Reference.roomsize * (cellsize - 1) - 1; ++putX) {
            for(int putZ = corner1[2] + this.startpointaddon; putZ < corner1[2] + this.startpointaddon + Reference.roomsize * (cellsize - 1) - 1; ++putZ) {
               world.func_147468_f(putX, putY, putZ);
            }
         }
      }

   }

   protected void generateStructureChestContents(World world, Random random, int x, int y, int z, WeightedRandomChestContent[] ArrayOfWeightedRandomChestContent, int par8) {
      world.func_147465_d(x, y, z, Blocks.field_150486_ae, 0, 2);
      TileEntityChest tileentitychest = (TileEntityChest)world.func_147438_o(x, y, z);
      if(tileentitychest != null) {
         WeightedRandomChestContent.func_76293_a(random, ArrayOfWeightedRandomChestContent, tileentitychest, par8);
      }

   }
}
