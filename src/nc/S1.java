package nc;

import java.util.Arrays;

public final class S1
{
    private final int[][] grid;
    
    public S1(final int[][] grid)
    {
        this.grid = new int[grid.length][];
        for (int row = 0; row < grid.length; row++)
        {
            this.grid[row] = Arrays.copyOf(grid[row], grid[row].length);
        }
    }
    
    @Override
    public final int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(grid);
        return result;
    }
    
    @Override
    public final boolean equals(final Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final S1 other = (S1) obj;
        if (!Arrays.deepEquals(grid, other.grid))
        {
            return false;
        }
        return true;
    }
}