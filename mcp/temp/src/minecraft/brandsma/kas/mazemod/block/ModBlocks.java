package brandsma.kas.mazemod.block;

import brandsma.kas.mazemod.block.BlockStairsMossy;
import brandsma.kas.mazemod.block.ItemMazeStoneBlock;
import brandsma.kas.mazemod.block.ItemTeleporterBlock;
import brandsma.kas.mazemod.block.MazeStone;
import brandsma.kas.mazemod.block.Teleporter;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class ModBlocks {

   public static Block mazestone;
   public static Block teleporter;
   public static Block stairsmossycobblestone;


   public static void init() {
      mazestone = (new MazeStone()).func_149722_s().func_149752_b(6000000.0F).func_149663_c("mazestone").func_149672_a(Block.field_149769_e);
      GameRegistry.registerBlock(mazestone, ItemMazeStoneBlock.class, mazestone.func_149739_a().replace("tile.", ""));
      teleporter = (new Teleporter("teleporter")).func_149722_s().func_149752_b(6000000.0F).func_149663_c("teleporter").func_149672_a(Block.field_149769_e);
      GameRegistry.registerBlock(teleporter, ItemTeleporterBlock.class, teleporter.func_149739_a().replace("tile.", ""));
      stairsmossycobblestone = (new BlockStairsMossy(Blocks.field_150341_Y, 0)).func_149663_c("stairsmossycobblestone");
      GameRegistry.registerBlock(stairsmossycobblestone, stairsmossycobblestone.func_149739_a().replace("tile.", ""));
   }
}
