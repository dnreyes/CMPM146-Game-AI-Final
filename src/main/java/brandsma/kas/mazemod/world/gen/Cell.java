package brandsma.kas.mazemod.world.gen;

public class Cell
{
    private int x;
    private int z;
    private boolean inmaze = false;
    public boolean invalid = false;

    public Cell(int x, int z)
    {
        this.x = x;
        this.z = z;
    }

    public int getX()
    {
        return this.x;
    }

    public int getZ()
    {
        return this.z;
    }

    public boolean isInMaze()
    {
        return this.inmaze;
    }

    public void addToMaze()
    {
        this.inmaze = true;
    }

    public void setInvalid()
    {
        this.invalid = true;
    }
}
