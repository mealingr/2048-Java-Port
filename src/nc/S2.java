package nc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class S2
{
    private final int[][] grid;
    
    public S2(final int[][] grid)
    {
        this.grid = new int[grid.length][];
        final Map<Integer, Integer> numbers = new HashMap<Integer, Integer>();
        for (int row = 0; row < grid.length; row++)
        {
            this.grid[row] = new int[grid[row].length];
            for (int column = 0; column < grid[row].length; column++)
            {
                if (grid[row][column] != Game.EMPTY)
                {
                    if (!numbers.containsKey(grid[row][column]))
                    {
                        numbers.put(grid[row][column], numbers.size());
                    }
                    this.grid[row][column] = numbers.get(grid[row][column]);
                }
                else
                {
                    this.grid[row][column] = grid[row][column];
                }
            }
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
        final S2 other = (S2) obj;
        if (!Arrays.deepEquals(grid, other.grid))
        {
            return false;
        }
        return true;
    }
}