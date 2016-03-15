package brandsma.kas.mazemod.world.gen;

import brandsma.kas.mazemod.block.ModBlocks;
import brandsma.kas.mazemod.lib.Reference;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class GenerateTemple {

   protected int[] corner1 = new int[3];
   protected int[] corner2 = new int[3];
   protected int[] corner3 = new int[3];
   protected int[] corner4 = new int[3];
   private int[] mazevars = Reference.getMazeVars();
   protected int foundationheight;
   protected int cellsize;
   protected int mazesize;
   protected int pathwidth;
   protected int margin;
   protected int size;
   protected World world;
   protected Random random;


   public GenerateTemple(World world, Random random) {
      this.foundationheight = this.mazevars[0];
      this.cellsize = this.mazevars[1];
      this.mazesize = this.mazevars[2];
      this.pathwidth = this.mazevars[3];
      this.margin = this.mazevars[4];
      this.world = world;
      this.random = random;
   }

   protected boolean setup(BiomeGenBase biome, int BlockX, int BlockZ) {
      this.corner1[0] = BlockX;
      this.corner1[2] = BlockZ;
      this.corner1[1] = this.getGroundLevel(this.corner1[0], this.world.func_72976_f(this.corner1[0], this.corner1[2]), this.corner1[2]);
      this.size = (this.mazesize + 2) * (this.cellsize - 1) + 1;
      this.corner3[0] = this.corner1[0] + this.size + this.pathwidth * 2 + this.foundationheight * 2;
      this.corner3[2] = this.corner1[2] + this.size + this.pathwidth * 2 + this.foundationheight * 2;
      this.world.func_72938_d(this.corner3[0], this.corner3[2]);
      this.corner3[1] = this.getGroundLevel(this.corner3[0], this.world.func_72976_f(this.corner3[0], this.corner3[2]), this.corner3[2]);
      this.corner4[0] = this.corner1[0];
      this.corner4[2] = this.corner3[2];
      this.world.func_72938_d(this.corner2[0], this.corner2[2]);
      this.corner4[1] = this.getGroundLevel(this.corner4[0], this.world.func_72976_f(this.corner2[0], this.corner2[2]), this.corner4[2]);
      this.corner2[0] = this.corner3[0];
      this.corner2[2] = this.corner1[2];
      this.world.func_72938_d(this.corner4[0], this.corner4[2]);
      this.corner2[1] = this.getGroundLevel(this.corner2[0], this.world.func_72976_f(this.corner4[0], this.corner4[2]), this.corner2[2]);
      return this.runChecksOnBlock(this.corner1[0], this.corner1[1], this.corner1[2], biome) && this.runChecksOnBlock(this.corner2[0], this.corner2[1], this.corner2[2], biome) && this.runChecksOnBlock(this.corner3[0], this.corner3[1], this.corner3[2], biome) && this.runChecksOnBlock(this.corner4[0], this.corner4[1], this.corner4[2], biome) && Math.max(Math.max(this.corner1[1], this.corner2[1]), Math.max(this.corner3[1], this.corner4[1])) - Math.min(Math.min(this.corner1[1], this.corner2[1]), Math.min(this.corner3[1], this.corner4[1])) <= this.foundationheight;
   }

   protected void generateFoundation(int baseheight, int metadata) {
      int turn = 0;

      for(int putY = baseheight; putY < this.foundationheight + baseheight; ++putY) {
         for(int putX = this.corner1[0] + turn; putX < this.corner3[0] - turn; ++putX) {
            for(int putZ = this.corner1[2] + turn; putZ < this.corner3[2] - turn; ++putZ) {
               this.world.func_147465_d(putX, putY, putZ, ModBlocks.mazestone, metadata, 2);
            }
         }

         ++turn;
      }

      this.generateSolidPyramid(baseheight + this.foundationheight, metadata);
   }

   protected void generateSolidPyramid(int baseheight, int metadata) {
      int turn = 0;

      for(int putY = baseheight; putY < baseheight + this.size / 2; ++putY) {
         for(int putX = this.corner1[0] + turn + this.foundationheight + this.pathwidth; putX < this.corner3[0] - turn - this.foundationheight - this.pathwidth; ++putX) {
            for(int putZ = this.corner1[2] + turn + this.foundationheight + this.pathwidth; putZ < this.corner3[2] - turn - this.foundationheight - this.pathwidth; ++putZ) {
               this.world.func_147465_d(putX, putY, putZ, ModBlocks.mazestone, metadata, 2);
            }
         }

         ++turn;
      }

   }

   public void generateLadderDown(int Xstart, int baseheight, int Zstart, int Xoffset, int Zoffset, int metadata) {
      baseheight -= 4;
      int putX = Xoffset + (this.cellsize - 1) * Xstart + 1;
      int putZ = Zoffset + (this.cellsize - 1) * Zstart;

      int putY;
      for(putY = baseheight; putY < baseheight + 3; ++putY) {
         this.world.func_147465_d(putX, putY, putZ, ModBlocks.mazestone, metadata, 2);
      }

      ++putZ;

      for(putY = baseheight; putY < baseheight + 4; ++putY) {
         this.world.func_147465_d(putX, putY, putZ, Blocks.field_150468_ap, 3, 2);
      }

      --putX;
   }

   public int getGroundLevel(int putX, int putY, int putZ) {
      Block currentBlock;
      do {
         --putY;
         currentBlock = this.world.func_147439_a(putX, putY, putZ);
      } while(currentBlock == Blocks.field_150350_a || currentBlock == Blocks.field_150362_t || currentBlock == Blocks.field_150364_r || currentBlock == Blocks.field_150395_bd);

      ++putY;
      return putY;
   }

   private boolean runChecksOnBlock(int x, int y, int z, BiomeGenBase biome) {
      y = Math.min(Math.min(this.corner1[1], this.corner2[1]), Math.min(this.corner3[1], this.corner4[1]));
      return this.world.func_72807_a(x, z) == biome && this.world.func_147439_a(x, y - 1, z) != ModBlocks.mazestone && this.world.func_147439_a(x, y, z) != ModBlocks.mazestone && this.world.func_147439_a(x, y + 1, z) != ModBlocks.mazestone && this.world.func_147439_a(x, y + 2, z) != ModBlocks.mazestone && this.world.func_147439_a(x, y + 3, z) != ModBlocks.mazestone && this.world.func_147439_a(x, y + 4, z) != ModBlocks.mazestone && this.world.func_147439_a(x, y + 5, z) != ModBlocks.mazestone;
   }
}
