package brandsma.kas.mazemod.block;

import brandsma.kas.mazemod.MazeMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class MazeStone extends Block {

   public MazeStone() {
      super(Material.field_151576_e);
      this.func_149647_a(MazeMod.creativeTabMazeMod);
   }

   public int func_149745_a(Random random) {
      return 1;
   }

   public int func_149692_a(int par1) {
      return par1;
   }

   @SideOnly(Side.CLIENT)
   public void func_149651_a(IIconRegister ir) {}

   @SideOnly(Side.CLIENT)
   public IIcon func_149691_a(int side, int metadata) {
      switch(metadata) {
      case 0:
         return Blocks.field_150322_A.func_149691_a(side, metadata);
      case 1:
         return Blocks.field_150341_Y.func_149691_a(side, metadata);
      default:
         System.out.println("Invalid metadata for " + this.func_149739_a());
         return null;
      }
   }

   @SideOnly(Side.CLIENT)
   public void func_149666_a(Item block, CreativeTabs creativetab, List blocklist) {
      for(int i = 0; i < 2; ++i) {
         blocklist.add(new ItemStack(block, 1, i));
      }

   }
}
