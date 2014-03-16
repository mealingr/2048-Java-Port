package nc.player;

import java.util.Random;

public abstract class AbstractPlayer implements Player
{
    protected final Random random;
    
    public AbstractPlayer(final Random random)
    {
        this.random = random;
    }
}