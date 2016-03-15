package brandsma.kas.mazemod.world.gen;

import brandsma.kas.mazemod.MazeMod;
import brandsma.kas.mazemod.block.ModBlocks;
import java.util.Random;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class GenerateTempleDesert extends GenerateTemple
{
    private int metadata = 0;

    public GenerateTempleDesert(World world, Random random)
    {
        super(world, random);
    }

    public void callGeneration(int BlockX, int BlockZ, boolean forceGeneration)
    {
        if (forceGeneration)
        {
            super.setup(BiomeGenBase.desert, BlockX, BlockZ);
            this.generatePyramid();
        }
        else if (super.setup(BiomeGenBase.desert, BlockX, BlockZ))
        {
            this.generatePyramid();
        }
    }

    private void generatePyramid()
    {
        int baseheight = Math.min(Math.min(this.corner1[1], this.corner2[1]), Math.min(this.corner3[1], this.corner4[1]));
        MazeMod.notify("Generating a pyramid At: X " + this.corner1[0] + ", Y " + baseheight + ", Z " + this.corner1[2]);
        MazeMod.notify("Full coordinate overview of all corners: " + this.corner1[0] + ", " + this.corner1[1] + ", " + this.corner1[2] + " || " + this.corner2[0] + ", " + this.corner2[1] + ", " + this.corner2[2] + " || " + this.corner3[0] + ", " + this.corner3[1] + ", " + this.corner3[2] + " || " + this.corner4[0] + ", " + this.corner4[1] + ", " + this.corner4[2] + ".");
        this.generateFoundation(baseheight, this.metadata);
        this.generateEntrance(baseheight + this.foundationheight, this.metadata);
        new GenerateMaze(this.world, this.random, 0, Math.round((float)(this.mazesize / 2)), this.mazesize, this.cellsize, this.corner1[0] + this.foundationheight + this.pathwidth + this.cellsize, baseheight + this.foundationheight, this.corner1[2] + this.foundationheight + this.pathwidth + this.cellsize, 0);
        new GenerateSpawners(this.world, this.random, this.mazesize, this.cellsize, this.corner1[0] + this.foundationheight + this.pathwidth + this.cellsize, baseheight + this.foundationheight, this.corner1[2] + this.foundationheight + this.pathwidth + this.cellsize, 10, 0);
        int layer;

        for (layer = 1; this.margin * layer * 2 < this.mazesize - 4; ++layer)
        {
            int Zstart = this.random.nextInt(this.mazesize - this.margin * layer * 2);
            int Xstart;

            if (layer % 2 == 0)
            {
                Xstart = 0;
            }
            else
            {
                Xstart = this.mazesize - this.margin * layer * 2 - 1;
            }

            int Xoffset = this.corner1[0] + this.foundationheight + this.pathwidth + 1 + (this.cellsize - 1) * (1 + this.margin * layer);
            int Zoffset = this.corner1[2] + this.foundationheight + this.pathwidth + 1 + (this.cellsize - 1) * (1 + this.margin * layer);
            this.generateLadderDown(Xstart, baseheight + this.foundationheight + layer * 4, Zstart, Xoffset, Zoffset, this.metadata);
            new GenerateMaze(this.world, this.random, Xstart, Zstart, this.mazesize - this.margin * layer * 2, this.cellsize, Xoffset, baseheight + this.foundationheight + layer * 4, Zoffset, this.metadata);
            new GenerateSpawners(this.world, this.random, this.mazesize - this.margin * layer * 2, this.cellsize, Xoffset, baseheight + this.foundationheight + layer * 4, Zoffset, Math.round((float)(6 / layer)), layer);
        }

        new GenerateTreasureRoomDesert(this.world, this.random, new int[] {this.corner1[0], baseheight, this.corner1[2]}, this.mazesize, this.cellsize, this.foundationheight, this.pathwidth, baseheight + this.foundationheight + layer * 4);
        this.generateLadderDown(Math.round((float)(this.mazesize / 2)), baseheight + this.foundationheight + layer * 4, Math.round((float)(this.mazesize / 2)), this.corner1[0] + this.foundationheight + this.pathwidth + this.cellsize, this.corner1[2] + this.foundationheight + this.pathwidth + this.cellsize, this.metadata);
    }

    protected void generateEntrance(int baseheight, int metadata)
    {
        int Xoffset = this.corner1[0] + this.foundationheight + this.pathwidth;
        int Zoffset = this.corner1[2] + this.foundationheight + this.pathwidth + Math.round((float)((this.mazesize + 2) / 2)) * (this.cellsize - 1);
        int putX;
        int putZ;
        int putY;

        for (putX = Xoffset; putX < Xoffset + this.cellsize; ++putX)
        {
            for (putZ = Zoffset; putZ < Zoffset + this.cellsize; ++putZ)
            {
                for (putY = baseheight; putY < baseheight + 4; ++putY)
                {
                    this.world.setBlock(putX, putY, putZ, ModBlocks.mazestone, metadata, 2);
                }
            }
        }

        for (putX = Xoffset; putX < Xoffset + this.cellsize; ++putX)
        {
            for (putZ = Zoffset + 1; putZ < Zoffset + this.cellsize - 1; ++putZ)
            {
                for (putY = baseheight; putY < baseheight + 3; ++putY)
                {
                    this.world.setBlockToAir(putX, putY, putZ);
                }
            }
        }
    }
}
