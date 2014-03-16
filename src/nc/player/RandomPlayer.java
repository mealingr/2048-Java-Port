package nc.player;

import java.util.Random;

import nc.Game;

public final class RandomPlayer extends AbstractPlayer
{
    public RandomPlayer(final Random random)
    {
        super(random);
    }
    
    @Override
    public final int getAction()
    {
        return Game.ACTIONS[random.nextInt(Game.ACTIONS.length)];
    }
}