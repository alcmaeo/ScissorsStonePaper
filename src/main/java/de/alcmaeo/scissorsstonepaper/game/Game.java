package de.alcmaeo.scissorsstonepaper.game;

/**
 * A Simple Game Interface
 */
public interface Game {

    /**
     * Play the Game.
     *
     * @param playerOne value, which player one choosed
     * @param playerTwo value, which player two choosed
     * @return result, who won
     * @throws IllegalArgumentException if invalid values are provided
     */
    GameResult play(GameValue playerOne, GameValue playerTwo) throws IllegalArgumentException;

}
