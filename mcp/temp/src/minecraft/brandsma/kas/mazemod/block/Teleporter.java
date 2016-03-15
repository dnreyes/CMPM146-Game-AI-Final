package brandsma.kas.mazemod.block;

import brandsma.kas.mazemod.MazeMod;
import brandsma.kas.mazemod.block.ModBlocks;
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

public class Teleporter extends Block implements ITileEntityProvider {

   World world;
   int x;
   int y;
   int z;


   public Teleporter(String texture) {
      super(Material.field_151576_e);
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
      this.func_149675_a(true);
      this.func_149647_a(MazeMod.creativeTabMazeMod);
      this.func_111047_d(0);
   }

   @SideOnly(Side.CLIENT)
   public IIcon func_149691_a(int par1, int par2) {
      return ModBlocks.mazestone.func_149691_a(par1, par2);
   }

   @SideOnly(Side.CLIENT)
   public void func_149666_a(Item block, CreativeTabs creativetab, List blocklist) {
      for(int i = 0; i < 2; ++i) {
         blocklist.add(new ItemStack(block, 1, i));
      }

   }

   public void func_149670_a(World world, int x, int y, int z, Entity entity) {
      int metadata = world.func_72805_g(x, y, z);
      switch(metadata) {
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
      this.func_149915_a(world, metadata);
      if(entity != null) {
         int highestblock = world.func_72976_f(Math.round((float)Math.round(entity.field_70165_t)), Math.round((float)Math.round(entity.field_70161_v)));
         if(entity.getClass() != EntityPlayerMP.class) {
            return;
         }

         ((EntityPlayerMP)entity).field_71135_a.func_147364_a(entity.field_70165_t, (double)highestblock, entity.field_70161_v, entity.field_70177_z, entity.field_70125_A);
         MazeMod.notify("Player teleported.");
         TileEntityTeleporter tet = (TileEntityTeleporter)world.func_147438_o(x, y, z);
         if(tet != null) {
            if(!tet.isBreakable) {
               tet.makeStructureBreakable();
               tet.func_145843_s();
            }
         } else {
            System.out.println("The TileEntity for the Teleporter was not there! :(");
         }
      }

   }

   public TileEntity func_149915_a(World world, int metadata) {
      return new TileEntityTeleporter(this.x, this.y, this.z);
   }

   public AxisAlignedBB func_149668_a(World world, int par2, int par3, int par4) {
      return null;
   }

   public boolean func_149662_c() {
      return false;
   }

   public boolean func_149686_d() {
      return false;
   }

   public void func_149683_g() {
      this.func_111047_d(0);
   }

   public void func_149719_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4) {
      this.func_111047_d(par1IBlockAccess.func_72805_g(par2, par3, par4));
   }

   protected void func_111047_d(int par1) {
      byte b0 = 0;
      float f = (float)(1 * (1 + b0)) / 16.0F;
      this.func_149676_a(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
   }

   public boolean func_149742_c(World par1World, int par2, int par3, int par4) {
      return super.func_149742_c(par1World, par2, par3, par4) && this.func_149718_j(par1World, par2, par3, par4);
   }

   public boolean func_149718_j(World par1World, int par2, int par3, int par4) {
      return !par1World.func_147437_c(par2, par3 - 1, par4);
   }

   @SideOnly(Side.CLIENT)
   public boolean func_149646_a(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
      return par5 == 1?true:super.func_149646_a(par1IBlockAccess, par2, par3, par4, par5);
   }

   @SideOnly(Side.CLIENT)
   public void func_149651_a(IIconRegister par1IconRegister) {}
}
