package brandsma.kas.mazemod.block;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class ModBlocks
{
    public static Block mazestone;
    public static Block teleporter;
    public static Block stairsmossycobblestone;

    public static void init()
    {
        mazestone = (new MazeStone()).setBlockUnbreakable().setResistance(6000000.0F).setBlockName("mazestone").setStepSound(Block.soundTypeStone);
        GameRegistry.registerBlock(mazestone, ItemMazeStoneBlock.class, mazestone.getUnlocalizedName().replace("tile.", ""));
        teleporter = (new Teleporter("teleporter")).setBlockUnbreakable().setResistance(6000000.0F).setBlockName("teleporter").setStepSound(Block.soundTypeStone);
        GameRegistry.registerBlock(teleporter, ItemTeleporterBlock.class, teleporter.getUnlocalizedName().replace("tile.", ""));
        stairsmossycobblestone = (new BlockStairsMossy(Blocks.mossy_cobblestone, 0)).setBlockName("stairsmossycobblestone");
        GameRegistry.registerBlock(stairsmossycobblestone, stairsmossycobblestone.getUnlocalizedName().replace("tile.", ""));
    }
}
