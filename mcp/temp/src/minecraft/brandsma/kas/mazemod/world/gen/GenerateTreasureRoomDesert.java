package brandsma.kas.mazemod.world.gen;

import brandsma.kas.mazemod.block.ModBlocks;
import brandsma.kas.mazemod.lib.Reference;
import brandsma.kas.mazemod.world.gen.GenerateTreasureRoom;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public class GenerateTreasureRoomDesert extends GenerateTreasureRoom {

   private int metadata = 0;


   public GenerateTreasureRoomDesert(World world, Random random, int[] corner1, int mazesize, int cellsize, int foundationheight, int pathwidth, int baseheight) {
      super(world, random, corner1, mazesize, cellsize, foundationheight, pathwidth, baseheight);
      int putX = corner1[0] + this.startpointaddon;
      int putZ = corner1[2] + this.startpointaddon;
      this.generateCorner1(putX, baseheight, putZ);
      putX += Reference.roomsize * (cellsize - 1) - 2;
      this.generateCorner2(putX, baseheight, putZ);
      putZ += Reference.roomsize * (cellsize - 1) - 2;
      this.generateCorner3(putX, baseheight, putZ);
      WeightedRandomChestContent[] chestcontent_food = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.field_151153_ao, 0, 1, 2, 1), new WeightedRandomChestContent(Items.field_151034_e, 0, 2, 6, 3), new WeightedRandomChestContent(Items.field_151106_aX, 0, 8, 30, 2), new WeightedRandomChestContent(Items.field_151025_P, 0, 3, 6, 4), new WeightedRandomChestContent(Items.field_151172_bF, 0, 6, 10, 2), new WeightedRandomChestContent(Items.field_151174_bG, 0, 8, 15, 2)};
      super.generateStructureChestContents(world, random, putX, baseheight, putZ - 1, chestcontent_food, 10);
      WeightedRandomChestContent[] chestcontent_weapons = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.field_151040_l, 0, 1, 1, 2), new WeightedRandomChestContent(Items.field_151031_f, 0, 1, 1, 2), new WeightedRandomChestContent(Items.field_151032_g, 0, 9, 16, 2), new WeightedRandomChestContent(Item.func_150898_a(Blocks.field_150478_aa), 0, 3, 11, 15), new WeightedRandomChestContent(Item.func_150898_a(Blocks.field_150364_r), 0, 3, 11, 9), new WeightedRandomChestContent(Items.field_151010_B, 0, 1, 1, 1)};
      super.generateStructureChestContents(world, random, putX - 2, baseheight, putZ, chestcontent_weapons, 8);
      super.generateStructureChestContents(world, random, putX - 1, baseheight, putZ, chestcontent_weapons, 8);
      putX -= Reference.roomsize * (cellsize - 1) - 2;
      this.generateCorner4(putX, baseheight, putZ);
      WeightedRandomChestContent[] chestcontent_treasure = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Items.field_151166_bC, 0, 1, 2, 1), new WeightedRandomChestContent(Items.field_151045_i, 0, 3, Reference.diamondmaxstacksize, 2), new WeightedRandomChestContent(Items.field_151100_aR, 4, 3, 8, 4), new WeightedRandomChestContent(Items.field_151043_k, 0, 2, 6, 3), new WeightedRandomChestContent(Items.field_151042_j, 0, 3, 14, 4)};
      super.generateStructureChestContents(world, random, putX + 1, baseheight, putZ, chestcontent_treasure, 10);
      super.generateStructureChestContents(world, random, putX + 2, baseheight, putZ, chestcontent_treasure, 6);
      WeightedRandomChestContent[] chestcontent_other = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Item.func_150898_a(Blocks.field_150346_d), 0, 12, 64, 10), new WeightedRandomChestContent(Items.field_151103_aS, 0, 1, 1, 6), new WeightedRandomChestContent(Item.func_150898_a(Blocks.field_150349_c), 0, 0, 1, 1)};
      super.generateStructureChestContents(world, random, putX, baseheight, putZ - 1, chestcontent_other, 15);
      putX = corner1[0] + this.startpointaddon + Math.round((float)(Reference.roomsize / 2)) * (cellsize - 1) - 1;
      putZ = corner1[2] + this.startpointaddon + Math.round((float)(Reference.roomsize / 2)) * (cellsize - 1) - 1;
      world.func_147449_b(putX + 1, baseheight + 3, putZ + 1, Blocks.field_150426_aN);
      world.func_147449_b(putX + 2, baseheight + 3, putZ + 1, Blocks.field_150426_aN);
      world.func_147449_b(putX + 1, baseheight + 3, putZ + 2, Blocks.field_150426_aN);
      world.func_147449_b(putX + 2, baseheight + 3, putZ + 2, Blocks.field_150426_aN);
      world.func_147465_d(corner1[0] + Reference.TELEPORTER_LOCATION[this.metadata][0], corner1[1] + Reference.TELEPORTER_LOCATION[this.metadata][1], corner1[2] + Reference.TELEPORTER_LOCATION[this.metadata][2], ModBlocks.teleporter, 0, 2);
   }

   public void generateCorner1(int putX, int putY, int putZ) {
      this.world.func_147449_b(putX, putY, putZ + 1, Blocks.field_150322_A);
      this.world.func_147465_d(putX, putY, putZ + 2, Blocks.field_150333_U, 1, 2);
      this.world.func_147449_b(putX + 1, putY, putZ, Blocks.field_150322_A);
      this.world.func_147465_d(putX + 1, putY, putZ + 1, Blocks.field_150333_U, 1, 2);
      this.world.func_147465_d(putX + 2, putY, putZ, Blocks.field_150333_U, 1, 2);
      this.world.func_147449_b(putX, putY + 3, putZ, Blocks.field_150358_i);
   }

   public void generateCorner2(int putX, int putY, int putZ) {
      this.world.func_147449_b(putX, putY, putZ + 1, Blocks.field_150322_A);
      this.world.func_147465_d(putX, putY, putZ + 2, Blocks.field_150333_U, 1, 2);
      this.world.func_147449_b(putX - 1, putY, putZ, Blocks.field_150322_A);
      this.world.func_147465_d(putX - 1, putY, putZ + 1, Blocks.field_150333_U, 1, 2);
      this.world.func_147465_d(putX - 2, putY, putZ, Blocks.field_150333_U, 1, 2);
      this.world.func_147449_b(putX, putY + 3, putZ, Blocks.field_150358_i);
   }

   public void generateCorner3(int putX, int putY, int putZ) {
      this.world.func_147449_b(putX, putY, putZ, Blocks.field_150462_ai);
      this.world.func_147449_b(putX, this.baseheight + 3, putZ, Blocks.field_150426_aN);
      this.world.func_147449_b(putX - 3, putY, putZ, Blocks.field_150421_aI);
   }

   public void generateCorner4(int putX, int putY, int putZ) {
      this.world.func_147449_b(putX, putY, putZ, Blocks.field_150462_ai);
      this.world.func_147449_b(putX, this.baseheight + 3, putZ, Blocks.field_150426_aN);
      this.world.func_147449_b(putX + 3, putY, putZ, Blocks.field_150381_bn);
   }
}
