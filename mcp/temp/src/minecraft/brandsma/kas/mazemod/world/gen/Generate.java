package brandsma.kas.mazemod.world.gen;

import brandsma.kas.mazemod.lib.Reference;
import brandsma.kas.mazemod.world.gen.GenerateTempleDesert;
import brandsma.kas.mazemod.world.gen.GenerateTempleJungle;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;

public class Generate implements IWorldGenerator {

   public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
      switch(world.field_73011_w.field_76574_g) {
      case 0:
         if(random.nextInt(Reference.SPAWNCHANCE) == 0) {
            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            if(world.func_72807_a(x, z) == BiomeGenBase.field_76769_d) {
               (new GenerateTempleDesert(world, random)).callGeneration(x, z, false);
            } else if(world.func_72807_a(x, z) == BiomeGenBase.field_76782_w) {
               (new GenerateTempleJungle(world, random)).callGeneration(x, z, false);
            }
         }
      default:
      }
   }
}
