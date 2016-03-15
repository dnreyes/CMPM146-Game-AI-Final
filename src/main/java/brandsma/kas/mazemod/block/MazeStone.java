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

public class MazeStone extends Block
{
    public MazeStone()
    {
        super(Material.rock);
        this.setCreativeTab(MazeMod.creativeTabMazeMod);
    }

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    public int quantityDropped(Random random)
    {
        return 1;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister ir) {}

    @SideOnly(Side.CLIENT)

    /**
     * Gets the block's texture. Args: side, meta
     */
    public IIcon getIcon(int side, int metadata)
    {
        switch (metadata)
        {
            case 0:
                return Blocks.sandstone.getIcon(side, metadata);

            case 1:
                return Blocks.mossy_cobblestone.getIcon(side, metadata);

            default:
                System.out.println("Invalid metadata for " + this.getUnlocalizedName());
                return null;
        }
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativetab, List blocklist)
    {
        for (int i = 0; i < 2; ++i)
        {
            blocklist.add(new ItemStack(block, 1, i));
        }
    }
}
