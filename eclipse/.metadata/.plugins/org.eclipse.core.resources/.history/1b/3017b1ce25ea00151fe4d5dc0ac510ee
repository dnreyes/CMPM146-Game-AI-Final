package brandsma.kas.mazemod.world.gen;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class GenerateSpawners
{
    private int mazesize;
    private int cellsize;
    private int Xoffset;
    private int Yoffset;
    private int Zoffset;
    private String[] moblist;
    private boolean gearedup = false;
    private World world;
    private Random random;
    private ItemStack[] equipment;

    public GenerateSpawners(World world, Random random, int mazesize, int cellsize, int Xoffset, int Yoffset, int Zoffset, int amount, int difficulty)
    {
        this.world = world;
        this.random = random;
        this.mazesize = mazesize;
        this.cellsize = cellsize;
        this.Xoffset = Xoffset;
        this.Yoffset = Yoffset;
        this.Zoffset = Zoffset;

        switch (difficulty)
        {
            case 0:
                this.moblist = new String[] {"Zombie", "Skeleton"};
                break;
            case 1:
                this.moblist = new String[] {"Creeper", "Skeleton", "Spider"};
                break;
            case 2:
                this.moblist = new String[] {"Blaze", "Enderman"};
                break;
            case 3:
                this.moblist = new String[] {"Zombie", "Skeleton"};
                this.gearedup = true;
                break;
            default:
                this.moblist = new String[] {"Creeper", "Skeleton", "Spider", "Zombie", "PigZombie", "Enderman", "Blaze"};
                this.gearedup = true;
        }

        for (int count = 0; count < amount; ++count)
        {
            this.generateSpawners();
        }
    }

    private void generateSpawners()
    {
        String currentmobID = this.moblist[this.random.nextInt(this.moblist.length)];
        int putX = this.Xoffset + (this.cellsize - 1) * this.random.nextInt(this.mazesize);
        int putY = this.Yoffset;
        int putZ = this.Zoffset + (this.cellsize - 1) * this.random.nextInt(this.mazesize);
        this.world.setBlock(putX, putY, putZ, Blocks.mob_spawner, 0, 2);
        TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)this.world.getTileEntity(putX, putY, putZ);
        tileentitymobspawner.func_145881_a().setMobID(currentmobID);

        if (this.gearedup)
        {
            int rand = this.random.nextInt(100);

            if (rand < 30)
            {
                this.equipment = new ItemStack[] {new ItemStack(Items.stone_sword), new ItemStack(Items.leather_helmet), new ItemStack(Items.leather_chestplate), new ItemStack(Items.leather_leggings), new ItemStack(Items.leather_boots)};
            }
            else if (rand >= 30 && rand < 70)
            {
                this.equipment = new ItemStack[] {new ItemStack(Items.iron_sword), new ItemStack(Items.iron_helmet), new ItemStack(Items.iron_chestplate), new ItemStack(Items.iron_leggings), new ItemStack(Items.iron_boots)};
            }
            else if (rand >= 70 && rand < 85)
            {
                this.equipment = new ItemStack[] {new ItemStack(Items.golden_sword), new ItemStack(Items.golden_helmet), new ItemStack(Items.golden_chestplate), new ItemStack(Items.golden_leggings), new ItemStack(Items.golden_boots)};
            }
            else if (rand >= 85 && rand < 90)
            {
                this.equipment = new ItemStack[] {new ItemStack(Items.bow), new ItemStack(Items.chainmail_helmet), new ItemStack(Items.chainmail_chestplate), new ItemStack(Items.chainmail_leggings), new ItemStack(Items.chainmail_boots)};
            }
            else if (rand >= 90)
            {
                this.equipment = new ItemStack[] {new ItemStack(Items.diamond_sword), new ItemStack(Items.diamond_helmet), new ItemStack(Items.diamond_chestplate), new ItemStack(Items.diamond_leggings), new ItemStack(Items.diamond_boots)};
            }

            NBTTagCompound nbt = new NBTTagCompound();
            tileentitymobspawner.writeToNBT(nbt);
            NBTTagList nbttaglist = new NBTTagList();
            NBTTagCompound nbt1 = new NBTTagCompound();

            for (int i = 0; i < this.equipment.length; ++i)
            {
                NBTTagCompound nbt2 = new NBTTagCompound();

                if (this.equipment[i] != null)
                {
                    this.equipment[i].writeToNBT(nbt2);
                }

                nbttaglist.appendTag(nbt2);
            }

            nbt1.setTag("Equipment", nbttaglist);
            nbt.setTag("SpawnData", nbt1);
            tileentitymobspawner.readFromNBT(nbt);
        }
    }
}
