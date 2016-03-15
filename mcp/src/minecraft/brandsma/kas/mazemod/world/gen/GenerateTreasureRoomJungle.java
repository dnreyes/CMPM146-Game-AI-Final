package brandsma.kas.mazemod.world.gen;

import brandsma.kas.mazemod.block.ModBlocks;
import brandsma.kas.mazemod.lib.Reference;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public class GenerateTreasureRoomJungle extends GenerateTreasureRoom
{
    private int metadata = 1;

    public GenerateTreasureRoomJungle(World world, Random random, int[] corner1, int mazesize, int cellsize, int foundationheight, int pathwidth, int baseheight)
    {
        super(world, random, corner1, mazesize, cellsize, foundationheight, pathwidth, baseheight);
        int putX = corner1[0] + this.startpointaddon;
        int putZ = corner1[2] + this.startpointaddon;
        this.generateCorner1(putX, baseheight, putZ);
        putX += Reference.roomsize * (cellsize - 1) - 2;
        this.generateCorner2(putX, baseheight, putZ);
        putZ += Reference.roomsize * (cellsize - 1) - 2;
        this.generateCorner3(putX, baseheight, putZ);
        WeightedRandomChestContent[] chestcontent_food = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.golden_apple, 0, 1, 2, 1), new WeightedRandomChestContent(Items.apple, 0, 2, 6, 3), new WeightedRandomChestContent(Items.cookie, 0, 8, 30, 2), new WeightedRandomChestContent(Items.bread, 0, 3, 6, 4), new WeightedRandomChestContent(Items.carrot, 0, 6, 10, 2), new WeightedRandomChestContent(Items.potato, 0, 8, 15, 2)};
        super.generateStructureChestContents(world, random, putX, baseheight, putZ - 1, chestcontent_food, 10);
        WeightedRandomChestContent[] chestcontent_weapons = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.iron_sword, 0, 1, 1, 2), new WeightedRandomChestContent(Items.bow, 0, 1, 1, 2), new WeightedRandomChestContent(Items.arrow, 0, 9, 16, 2), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.torch), 0, 3, 11, 15), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.log), 0, 3, 11, 9), new WeightedRandomChestContent(Items.golden_sword, 0, 1, 1, 1)};
        super.generateStructureChestContents(world, random, putX - 2, baseheight, putZ, chestcontent_weapons, 8);
        super.generateStructureChestContents(world, random, putX - 1, baseheight, putZ, chestcontent_weapons, 8);
        putX -= Reference.roomsize * (cellsize - 1) - 2;
        this.generateCorner4(putX, baseheight, putZ);
        WeightedRandomChestContent[] chestcontent_treasure = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Items.emerald, 0, 1, 2, 1), new WeightedRandomChestContent(Items.diamond, 0, 3, Reference.diamondmaxstacksize, 2), new WeightedRandomChestContent(Items.dye, 4, 3, 8, 4), new WeightedRandomChestContent(Items.gold_ingot, 0, 2, 6, 3), new WeightedRandomChestContent(Items.iron_ingot, 0, 3, 14, 4)};
        super.generateStructureChestContents(world, random, putX + 1, baseheight, putZ, chestcontent_treasure, 10);
        super.generateStructureChestContents(world, random, putX + 2, baseheight, putZ, chestcontent_treasure, 6);
        WeightedRandomChestContent[] chestcontent_other = new WeightedRandomChestContent[] {new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.dirt), 0, 12, 64, 10), new WeightedRandomChestContent(Items.bone, 0, 1, 1, 6), new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.grass), 0, 0, 1, 1)};
        super.generateStructureChestContents(world, random, putX, baseheight, putZ - 1, chestcontent_other, 15);
        putX = corner1[0] + this.startpointaddon + Math.round((float)(Reference.roomsize / 2)) * (cellsize - 1) - 1;
        putZ = corner1[2] + this.startpointaddon + Math.round((float)(Reference.roomsize / 2)) * (cellsize - 1) - 1;
        world.setBlock(putX, baseheight + 3, putZ, Blocks.glowstone);
        world.setBlock(putX + 3, baseheight + 3, putZ, Blocks.glowstone);
        world.setBlock(putX, baseheight + 3, putZ + 3, Blocks.glowstone);
        world.setBlock(putX + 3, baseheight + 3, putZ + 3, Blocks.glowstone);
        world.setBlock(corner1[0] + Reference.TELEPORTER_LOCATION[this.metadata][0], corner1[1] + Reference.TELEPORTER_LOCATION[this.metadata][1], corner1[2] + Reference.TELEPORTER_LOCATION[this.metadata][2], ModBlocks.teleporter, this.metadata, 2);
    }

    public void generateCorner1(int putX, int putY, int putZ)
    {
        this.world.setBlock(putX, putY, putZ + 2, Blocks.mossy_cobblestone, 1, 2);
        this.world.setBlock(putX + 1, putY, putZ + 1, Blocks.mossy_cobblestone, 1, 2);
        this.world.setBlock(putX + 2, putY, putZ, Blocks.mossy_cobblestone, 1, 2);
        this.world.setBlock(putX, putY + 2, putZ, Blocks.flowing_water);
    }

    public void generateCorner2(int putX, int putY, int putZ)
    {
        this.world.setBlock(putX, putY, putZ + 2, Blocks.mossy_cobblestone, 1, 2);
        this.world.setBlock(putX - 1, putY, putZ + 1, Blocks.mossy_cobblestone, 1, 2);
        this.world.setBlock(putX - 2, putY, putZ, Blocks.mossy_cobblestone, 1, 2);
        this.world.setBlock(putX, putY + 2, putZ, Blocks.flowing_water);
    }

    public void generateCorner3(int putX, int putY, int putZ)
    {
        this.world.setBlock(putX, putY, putZ, Blocks.crafting_table);
        this.world.setBlock(putX - 3, putY, putZ, Blocks.jukebox);
    }

    public void generateCorner4(int putX, int putY, int putZ)
    {
        this.world.setBlock(putX, putY, putZ, Blocks.crafting_table);
        this.world.setBlock(putX + 3, putY, putZ, Blocks.enchanting_table);
    }
}
