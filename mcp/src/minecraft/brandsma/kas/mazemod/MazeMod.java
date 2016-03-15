package brandsma.kas.mazemod;

import brandsma.kas.mazemod.block.ModBlocks;
import brandsma.kas.mazemod.command.CommandsKM;
import brandsma.kas.mazemod.creativetab.CreativeTabMazeMod;
import brandsma.kas.mazemod.lib.Reference;
import brandsma.kas.mazemod.tileentity.TileEntityTeleporter;
import brandsma.kas.mazemod.world.gen.Generate;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;

@Mod(
    modid = "KM",
    name = "Kasslim\'s Mazes",
    version = "1.7.10_A"
)
public class MazeMod
{
    public static CreativeTabs creativeTabMazeMod = new CreativeTabMazeMod(CreativeTabs.getNextID(), "kmCreativeTab");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        Reference.initializeConfig();
        ModBlocks.init();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        GameRegistry.registerTileEntity(TileEntityTeleporter.class, "KMTeleporter");
        GameRegistry.registerWorldGenerator(new Generate(), 0);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        notify("Kasslim\'s Mazes was initialized, notifications are on. (Mod version: \'1.7.10_A\').");
    }

    @EventHandler
    public void initServer(FMLServerStartingEvent event)
    {
        notify("initiating server");
        event.registerServerCommand(new CommandsKM());
    }

    public static void notify(String string)
    {
        if (Reference.NOTIFY)
        {
            System.out.println("[KM]: " + string);
        }
    }
}
