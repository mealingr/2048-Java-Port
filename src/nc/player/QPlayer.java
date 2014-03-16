package nc.player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import nc.Game;
import nc.S2;

public final class QPlayer extends AbstractPlayer
{
    private final Game game;
    private final Map<S2, double[]> qTable;
    private double learningRate;
    private double discountFactor;
    private double explorationRate;
    private int previousScore;
    private int score;
    private S2 previousState;
    private S2 state;
    private int action;
    private int currentGame;
    
    public QPlayer(final Random random,
                   final Game game,
                   final double learningRate,
                   final double discountFactor,
                   final double explorationRate)
    {
        super(random);
        this.game = game;
        qTable = new HashMap<S2, double[]>();
        this.learningRate = learningRate;
        this.discountFactor = discountFactor;
        this.explorationRate = explorationRate;
        reset();
    }
    
    public final Map<S2, double[]> getQTable()
    {
        return qTable;
    }
    
    private final void reset()
    {
        previousScore = 0;
        score = 0;
        previousState = null;
        state = null;
        action = -1;
        currentGame = game.getGame();
    }
    
    private final double getMaxQValue(final S2 state)
    {
        final double[] qValues = qTable.get(state);
        double maxQValue = Double.NEGATIVE_INFINITY;
        for (int action = 0; action < qValues.length; action++)
        {
            if (qValues[action] > maxQValue)
            {
                maxQValue = qValues[action];
            }
        }
        return maxQValue;
    }
    
    private final void updateQValue(final S2 previousState,
                                    final int action,
                                    final S2 state,
                                    final double reward)
    {
        qTable.get(previousState)[action] = (1.0d - learningRate) * qTable.get(previousState)[action] +
                                            learningRate * (reward + discountFactor * getMaxQValue(state));
    }
    
    private final List<Integer> getMaxQValueActions(final S2 state)
    {
        final List<Integer> maxQValueActions = new ArrayList<Integer>();
        final double[] qValues = qTable.get(state);
        double maxQValue = Double.NEGATIVE_INFINITY;
        for (int action = 0; action < qValues.length; action++)
        {
            if (qValues[action] > maxQValue)
            {
                maxQValue = qValues[action];
                maxQValueActions.clear();
                maxQValueActions.add(action);
            }
            else if (qValues[action] == maxQValue)
            {
                maxQValueActions.add(action);
            }
        }
        return maxQValueActions;
    }
    
    @Override
    public final int getAction()
    {
        if (currentGame != game.getGame())
        {
            reset();
        }
        previousState = state;
        state = new S2(game.getGrid());
        if (!qTable.containsKey(state))
        {
            final double[] qValues = new double[Game.ACTIONS.length];
            Arrays.fill(qValues, 0.0d);
            qTable.put(state, qValues);
        }
        previousScore = score;
        score = game.getScore();
        final double reward = score - previousScore;
        if (previousState != null)
        {
            updateQValue(previousState,
                         action,
                         state,
                         reward);
        }
        if (random.nextDouble() < explorationRate)
        {
            action = Game.ACTIONS[random.nextInt(Game.ACTIONS.length)];
        }
        else
        {
            final List<Integer> maxQValueActions = getMaxQValueActions(state);
            action = maxQValueActions.get(random.nextInt(maxQValueActions.size()));
        }
        return action;
    }
}