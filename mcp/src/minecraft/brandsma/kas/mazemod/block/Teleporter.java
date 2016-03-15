package brandsma.kas.mazemod.block;

import brandsma.kas.mazemod.MazeMod;
import brandsma.kas.mazemod.lib.Reference;
import brandsma.kas.mazemod.tileentity.TileEntityTeleporter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class Teleporter extends Block implements ITileEntityProvider
{
    World world;
    int x;
    int y;
    int z;

    public Teleporter(String texture)
    {
        super(Material.rock);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
        this.setTickRandomly(true);
        this.setCreativeTab(MazeMod.creativeTabMazeMod);
        this.func_111047_d(0);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Gets the block's texture. Args: side, meta
     */
    public IIcon getIcon(int par1, int par2)
    {
        return ModBlocks.mazestone.getIcon(par1, par2);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item block, CreativeTabs creativetab, List blocklist)
    {
        for (int i = 0; i < 2; ++i)
        {
            blocklist.add(new ItemStack(block, 1, i));
        }
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        int metadata = world.getBlockMetadata(x, y, z);

        switch (metadata)
        {
            case 0:
                this.x = x - Reference.TELEPORTER_LOCATION[metadata][0];
                this.y = y - Reference.TELEPORTER_LOCATION[metadata][1];
                this.z = z - Reference.TELEPORTER_LOCATION[metadata][2];
                break;

            case 1:
                this.x = x - Reference.TELEPORTER_LOCATION[metadata][0];
                this.y = y - Reference.TELEPORTER_LOCATION[metadata][1];
                this.z = z - Reference.TELEPORTER_LOCATION[metadata][2];
        }

        MazeMod.notify("onCollide passed x: " + x + " y: " + y + " z: " + z + " after coordinate correction.");
        this.createNewTileEntity(world, metadata);

        if (entity != null)
        {
            int highestblock = world.getHeightValue(Math.round((float)Math.round(entity.posX)), Math.round((float)Math.round(entity.posZ)));

            if (entity.getClass() != EntityPlayerMP.class)
            {
                return;
            }

            ((EntityPlayerMP)entity).playerNetServerHandler.setPlayerLocation(entity.posX, (double)highestblock, entity.posZ, entity.rotationYaw, entity.rotationPitch);
            MazeMod.notify("Player teleported.");
            TileEntityTeleporter tet = (TileEntityTeleporter)world.getTileEntity(x, y, z);

            if (tet != null)
            {
                if (!tet.isBreakable)
                {
                    tet.makeStructureBreakable();
                    tet.invalidate();
                }
            }
            else
            {
                System.out.println("The TileEntity for the Teleporter was not there! :(");
            }
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityTeleporter(this.x, this.y, this.z);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int par2, int par3, int par4)
    {
        return null;
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
    public void setBlockBoundsForItemRender()
    {
        this.func_111047_d(0);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        this.func_111047_d(par1IBlockAccess.getBlockMetadata(par2, par3, par4));
    }

    protected void func_111047_d(int par1)
    {
        byte b0 = 0;
        float f = (float)(1 * (1 + b0)) / 16.0F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
    }

    public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return super.canPlaceBlockAt(par1World, par2, par3, par4) && this.canBlockStay(par1World, par2, par3, par4);
    }

    /**
     * Can this block stay at this position.  Similar to canPlaceBlockAt except gets checked often with plants.
     */
    public boolean canBlockStay(World par1World, int par2, int par3, int par4)
    {
        return !par1World.isAirBlock(par2, par3 - 1, par4);
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
        return par5 == 1 ? true : super.shouldSideBeRendered(par1IBlockAccess, par2, par3, par4, par5);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister par1IconRegister) {}
}
