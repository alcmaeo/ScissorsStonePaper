package de.alcmaeo.scissorsstonepaper.game;

/**
 * Default implementation of the Game Interface.
 */
public class GameImpl implements Game {

    public GameResult play(GameValue playerOne, GameValue playerTwo) {
        if (playerOne == null || playerTwo == null) {
            throw new IllegalArgumentException("This Game doesn't support null values");
        }

        if (playerOne == playerTwo) {
            return GameResult.DRAW;
        }
        switch (playerOne) {
            case PAPER:
                return playerTwo == GameValue.SCISSORS ? GameResult.PLAYER2_WON : GameResult.PLAYER1_WON;
            case STONE:
                return playerTwo == GameValue.PAPER ? GameResult.PLAYER2_WON : GameResult.PLAYER1_WON;
            case SCISSORS:
                return playerTwo == GameValue.STONE ? GameResult.PLAYER2_WON : GameResult.PLAYER1_WON;
            default:
                throw new IllegalStateException("Provided value is not supported");
        }
    }

}
