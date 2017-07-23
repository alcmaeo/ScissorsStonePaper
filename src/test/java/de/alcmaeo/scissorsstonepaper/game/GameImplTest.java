package de.alcmaeo.scissorsstonepaper.game;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith( JUnit4.class )
public class GameImplTest {

    private Game game = null;

    @Before
    public void initialize() {
        game = new GameImpl();
    }

    @Test
    public void testDraw() {
        Assert.assertEquals(game.play(GameValue.SCISSORS, GameValue.SCISSORS), GameResult.DRAW);
        Assert.assertEquals(game.play(GameValue.PAPER, GameValue.PAPER), GameResult.DRAW);
        Assert.assertEquals(game.play(GameValue.STONE, GameValue.STONE), GameResult.DRAW);
    }

    @Test
    public void wins() {
        Assert.assertEquals(game.play(GameValue.SCISSORS, GameValue.PAPER), GameResult.PLAYER1_WON);
        Assert.assertEquals(game.play(GameValue.PAPER, GameValue.STONE), GameResult.PLAYER1_WON);
        Assert.assertEquals(game.play(GameValue.STONE, GameValue.SCISSORS), GameResult.PLAYER1_WON);
    }

    @Test
    public void loses() {
        Assert.assertEquals(game.play(GameValue.PAPER, GameValue.SCISSORS), GameResult.PLAYER2_WON);
        Assert.assertEquals(game.play(GameValue.STONE, GameValue.PAPER), GameResult.PLAYER2_WON);
        Assert.assertEquals(game.play(GameValue.SCISSORS, GameValue.STONE), GameResult.PLAYER2_WON);
    }

}
