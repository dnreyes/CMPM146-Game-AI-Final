package brandsma.kas.mazemod.tileentity;

import brandsma.kas.mazemod.MazeMod;
import brandsma.kas.mazemod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;

public class TileEntityTeleporter extends TileEntity
{
    int x;
    int y;
    int z;
    public boolean isBreakable = false;

    public TileEntityTeleporter(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        MazeMod.notify("A TileEntityTeleporter was created, X: " + x + " Y: " + y + " Z: " + z + ".");

        if (this.x == 0 && this.y == 0 && this.z == 0)
        {
            this.invalidate();
        }
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setIntArray("corner", new int[] {this.x, this.y, this.z});
        nbttagcompound.setBoolean("isBreakable", this.isBreakable);
        MazeMod.notify("written " + this.x + " " + this.y + " " + this.z + " to the worldfile");
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        int[] corner1 = nbttagcompound.getIntArray("corner");
        this.x = corner1[0];
        this.y = corner1[1];
        this.z = corner1[2];
        this.isBreakable = nbttagcompound.getBoolean("isBreakable");
        MazeMod.notify("read " + this.x + " " + this.y + " " + this.z + " from the worldfile");
    }

    public void makeStructureBreakable()
    {
        WorldServer world = MinecraftServer.getServer().worldServerForDimension(0);
        MazeMod.notify("makeStructureBreakable coords: " + this.x + " " + this.y + " " + this.z);
        int putX = this.x;
        int putY = this.y;
        int putZ = this.z;
        Block currentID;

        do
        {
            currentID = world.getBlock(putX, putY, putZ);
            ++putX;
        }
        while (currentID == ModBlocks.mazestone);

        int size = putX - this.x;
        int metadata = world.getBlockMetadata(this.x, this.y, this.z);
        Block block;

        switch (metadata)
        {
            case 0:
                block = Blocks.sandstone;
                break;

            case 1:
                block = Blocks.mossy_cobblestone;
                break;

            default:
                block = Blocks.air;
        }

        for (putX = this.x; putX < size + this.x; ++putX)
        {
            for (putZ = this.z; putZ < size + this.z; ++putZ)
            {
                for (putY = this.y; putY < size + this.y; ++putY)
                {
                    if (world.getBlock(putX, putY, putZ) == ModBlocks.mazestone)
                    {
                        world.setBlock(putX, putY, putZ, block, 0, 2);
                    }
                }
            }
        }

        if (size > 0)
        {
            MazeMod.notify("The pyramid at: " + this.x + " " + this.y + " " + this.z + " is now breakable.");
            this.isBreakable = true;
            world.setBlockToAir(this.x, this.y, this.z);
            this.invalidate();
            MazeMod.notify("Removed teleporter at: " + this.x + " " + this.y + " " + this.z + ".");
        }
    }
}
