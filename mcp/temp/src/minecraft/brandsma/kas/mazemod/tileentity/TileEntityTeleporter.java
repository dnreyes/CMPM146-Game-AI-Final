package brandsma.kas.mazemod.tileentity;

import brandsma.kas.mazemod.MazeMod;
import brandsma.kas.mazemod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;

public class TileEntityTeleporter extends TileEntity {

   int x;
   int y;
   int z;
   public boolean isBreakable = false;


   public TileEntityTeleporter(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
      MazeMod.notify("A TileEntityTeleporter was created, X: " + x + " Y: " + y + " Z: " + z + ".");
      if(this.x == 0 && this.y == 0 && this.z == 0) {
         this.func_145843_s();
      }

   }

   public void func_145841_b(NBTTagCompound nbttagcompound) {
      super.func_145841_b(nbttagcompound);
      nbttagcompound.func_74783_a("corner", new int[]{this.x, this.y, this.z});
      nbttagcompound.func_74757_a("isBreakable", this.isBreakable);
      MazeMod.notify("written " + this.x + " " + this.y + " " + this.z + " to the worldfile");
   }

   public void func_145839_a(NBTTagCompound nbttagcompound) {
      super.func_145839_a(nbttagcompound);
      int[] corner1 = nbttagcompound.func_74759_k("corner");
      this.x = corner1[0];
      this.y = corner1[1];
      this.z = corner1[2];
      this.isBreakable = nbttagcompound.func_74767_n("isBreakable");
      MazeMod.notify("read " + this.x + " " + this.y + " " + this.z + " from the worldfile");
   }

   public void makeStructureBreakable() {
      WorldServer world = MinecraftServer.func_71276_C().func_71218_a(0);
      MazeMod.notify("makeStructureBreakable coords: " + this.x + " " + this.y + " " + this.z);
      int putX = this.x;
      int putY = this.y;
      int putZ = this.z;

      Block currentID;
      do {
         currentID = world.func_147439_a(putX, putY, putZ);
         ++putX;
      } while(currentID == ModBlocks.mazestone);

      int size = putX - this.x;
      int metadata = world.func_72805_g(this.x, this.y, this.z);
      Block block;
      switch(metadata) {
      case 0:
         block = Blocks.field_150322_A;
         break;
      case 1:
         block = Blocks.field_150341_Y;
         break;
      default:
         block = Blocks.field_150350_a;
      }

      for(putX = this.x; putX < size + this.x; ++putX) {
         for(putZ = this.z; putZ < size + this.z; ++putZ) {
            for(putY = this.y; putY < size + this.y; ++putY) {
               if(world.func_147439_a(putX, putY, putZ) == ModBlocks.mazestone) {
                  world.func_147465_d(putX, putY, putZ, block, 0, 2);
               }
            }
         }
      }

      if(size > 0) {
         MazeMod.notify("The pyramid at: " + this.x + " " + this.y + " " + this.z + " is now breakable.");
         this.isBreakable = true;
         world.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
         this.func_145843_s();
         MazeMod.notify("Removed teleporter at: " + this.field_145851_c + " " + this.field_145848_d + " " + this.field_145849_e + ".");
      }

   }
}
