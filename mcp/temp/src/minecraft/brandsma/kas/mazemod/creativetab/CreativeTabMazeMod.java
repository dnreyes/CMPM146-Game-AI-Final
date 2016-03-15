package brandsma.kas.mazemod.creativetab;

import brandsma.kas.mazemod.block.ModBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class CreativeTabMazeMod extends CreativeTabs {

   public CreativeTabMazeMod(int par1, String par2Str) {
      super(par1, par2Str);
   }

   @SideOnly(Side.CLIENT)
   public Item func_78016_d() {
      return Item.func_150898_a(ModBlocks.mazestone);
   }
}
