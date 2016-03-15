package brandsma.kas.mazemod.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemMazeStoneBlock extends ItemBlock {

   public ItemMazeStoneBlock(Block par1) {
      super(par1);
      this.func_77627_a(true);
   }

   public String func_77667_c(ItemStack itemstack) {
      String name = "";
      switch(itemstack.func_77960_j()) {
      case 0:
         name = "desert";
         break;
      case 1:
         name = "jungle";
         break;
      default:
         name = "broken";
      }

      return this.func_77658_a() + "." + name;
   }

   public int func_77647_b(int par1) {
      return par1;
   }
}
