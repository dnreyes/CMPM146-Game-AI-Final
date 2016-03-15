package brandsma.kas.mazemod.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemTeleporterBlock extends ItemBlock
{
    public ItemTeleporterBlock(Block par1)
    {
        super(par1);
        this.setHasSubtypes(true);
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack itemstack)
    {
        String name = "";

        switch (itemstack.getItemDamage())
        {
            case 0:
                name = "desert";
                break;

            case 1:
                name = "jungle";
                break;

            default:
                name = "broken";
        }

        return this.getUnlocalizedName() + "." + name;
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    public int getMetadata(int par1)
    {
        return par1;
    }
}
