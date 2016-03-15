package brandsma.kas.mazemod.world.gen;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class GenerateSpawners {

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


   public GenerateSpawners(World world, Random random, int mazesize, int cellsize, int Xoffset, int Yoffset, int Zoffset, int amount, int difficulty) {
      this.world = world;
      this.random = random;
      this.mazesize = mazesize;
      this.cellsize = cellsize;
      this.Xoffset = Xoffset;
      this.Yoffset = Yoffset;
      this.Zoffset = Zoffset;
      switch(difficulty) {
      case 0:
         this.moblist = new String[]{"Zombie", "Skeleton"};
         break;
      case 1:
         this.moblist = new String[]{"Creeper", "Skeleton", "Spider"};
         break;
      case 2:
         this.moblist = new String[]{"Blaze", "Enderman"};
         break;
      case 3:
         this.moblist = new String[]{"Zombie", "Skeleton"};
         this.gearedup = true;
         break;
      default:
         this.moblist = new String[]{"Creeper", "Skeleton", "Spider", "Zombie", "PigZombie", "Enderman", "Blaze"};
         this.gearedup = true;
      }

      for(int count = 0; count < amount; ++count) {
         this.generateSpawners();
      }

   }

   private void generateSpawners() {
      String currentmobID = this.moblist[this.random.nextInt(this.moblist.length)];
      int putX = this.Xoffset + (this.cellsize - 1) * this.random.nextInt(this.mazesize);
      int putY = this.Yoffset;
      int putZ = this.Zoffset + (this.cellsize - 1) * this.random.nextInt(this.mazesize);
      this.world.func_147465_d(putX, putY, putZ, Blocks.field_150474_ac, 0, 2);
      TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)this.world.func_147438_o(putX, putY, putZ);
      tileentitymobspawner.func_145881_a().func_98272_a(currentmobID);
      if(this.gearedup) {
         int rand = this.random.nextInt(100);
         if(rand < 30) {
            this.equipment = new ItemStack[]{new ItemStack(Items.field_151052_q), new ItemStack(Items.field_151024_Q), new ItemStack(Items.field_151027_R), new ItemStack(Items.field_151026_S), new ItemStack(Items.field_151021_T)};
         } else if(rand >= 30 && rand < 70) {
            this.equipment = new ItemStack[]{new ItemStack(Items.field_151040_l), new ItemStack(Items.field_151028_Y), new ItemStack(Items.field_151030_Z), new ItemStack(Items.field_151165_aa), new ItemStack(Items.field_151167_ab)};
         } else if(rand >= 70 && rand < 85) {
            this.equipment = new ItemStack[]{new ItemStack(Items.field_151010_B), new ItemStack(Items.field_151169_ag), new ItemStack(Items.field_151171_ah), new ItemStack(Items.field_151149_ai), new ItemStack(Items.field_151151_aj)};
         } else if(rand >= 85 && rand < 90) {
            this.equipment = new ItemStack[]{new ItemStack(Items.field_151031_f), new ItemStack(Items.field_151020_U), new ItemStack(Items.field_151023_V), new ItemStack(Items.field_151022_W), new ItemStack(Items.field_151029_X)};
         } else if(rand >= 90) {
            this.equipment = new ItemStack[]{new ItemStack(Items.field_151048_u), new ItemStack(Items.field_151161_ac), new ItemStack(Items.field_151163_ad), new ItemStack(Items.field_151173_ae), new ItemStack(Items.field_151175_af)};
         }

         NBTTagCompound nbt = new NBTTagCompound();
         tileentitymobspawner.func_145841_b(nbt);
         NBTTagList nbttaglist = new NBTTagList();
         NBTTagCompound nbt1 = new NBTTagCompound();

         for(int i = 0; i < this.equipment.length; ++i) {
            NBTTagCompound nbt2 = new NBTTagCompound();
            if(this.equipment[i] != null) {
               this.equipment[i].func_77955_b(nbt2);
            }

            nbttaglist.func_74742_a(nbt2);
         }

         nbt1.func_74782_a("Equipment", nbttaglist);
         nbt.func_74782_a("SpawnData", nbt1);
         tileentitymobspawner.func_145839_a(nbt);
      }

   }
}
