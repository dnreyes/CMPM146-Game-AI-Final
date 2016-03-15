package brandsma.kas.mazemod.world.gen;

import brandsma.kas.mazemod.lib.Reference;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public class Generate implements IWorldGenerator
{
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch (world.provider.dimensionId)
        {
            case 0:
                if (random.nextInt(Reference.SPAWNCHANCE) == 0)
                {
                    int x = chunkX * 16 + random.nextInt(16);
                    int z = chunkZ * 16 + random.nextInt(16);

                    if (world.getBiomeGenForCoords(x, z) == BiomeGenBase.desert)
                    {
                        (new GenerateTempleDesert(world, random)).callGeneration(x, z, false);
                    }
                    else if (world.getBiomeGenForCoords(x, z) == BiomeGenBase.jungle)
                    {
                        (new GenerateTempleJungle(world, random)).callGeneration(x, z, false);
                    }
                }

            default:
        }
    }
}
