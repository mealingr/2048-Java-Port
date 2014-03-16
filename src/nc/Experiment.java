package nc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import nc.player.HumanPlayer;
import nc.player.IDDFSABPlayer;
import nc.player.QPlayer;

public final class Experiment
{
    private Experiment()
    {
        throw new AssertionError();
    }
    
    public static final void testHumanPlayer()
    {
        final Random random = new Random();
        final Game game = new Game(random);
        final GUI gui = new GUI(game);
        final ActionListener taskPerformer = new ActionListener()
        {
            public final void actionPerformed(final ActionEvent e)
            {
                gui.update();
            }
        };
        new Timer(100, taskPerformer).start();
        final HumanPlayer player = new HumanPlayer(random, gui.getFrame());
        game.setPlayer(player);
        while (true)
        {
            game.playGame();
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
        }
    }
    
    public static final void testIDDFSABPlayer()
    {
        final Random random = new Random();
        final Game game = new Game(random);
        final GUI gui = new GUI(game);
        final ActionListener taskPerformer = new ActionListener()
        {
            public final void actionPerformed(final ActionEvent e)
            {
                gui.update();
            }
        };
        new Timer(100, taskPerformer).start();
        final IDDFSABPlayer player = new IDDFSABPlayer(random, game);
        game.setPlayer(player);
        while (true)
        {
            game.playGame();
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e1)
            {
                e1.printStackTrace();
            }
        }
    }
    
    public static final void testQPlayer()
    {
        final Random random = new Random();
        final Game game = new Game(random);
        final QPlayer player = new QPlayer(random, game, 0.1d, 0.1d, 0.05d);
        game.setPlayer(player);
        while (true)
        {
            game.playGame();
            game.reset();
            System.out.println(player.getQTable().size());
        }
    }
    
    public static final void main(final String[] args)
    {
        testIDDFSABPlayer();
    }
}
