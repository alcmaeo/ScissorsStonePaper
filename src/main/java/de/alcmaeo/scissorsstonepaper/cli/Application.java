package de.alcmaeo.scissorsstonepaper.cli;

import de.alcmaeo.scissorsstonepaper.game.Game;
import de.alcmaeo.scissorsstonepaper.game.GameImpl;
import de.alcmaeo.scissorsstonepaper.game.GameResult;
import de.alcmaeo.scissorsstonepaper.game.GameValue;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.OptionHandlerFilter;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main Application for the Game
 */
public class Application {

    @Option( name = "-r", usage = "How many round do you want to play? Default: 3 (1..10)" )
    private int rounds = 3;

    @Option( name = "-n", usage = "Your nickname. Default: Player 1" )
    private String playerName = "Player 1";

    private SecureRandom random;

    private Game game;

    public Application(Game game) {
        this.random = new SecureRandom();
        this.game = game;
    }

    public static void main(String[] args) {
        new Application(new GameImpl()).initialize(args).play();
    }

    /**
     * Initilizes the Application for the Game.
     *
     * @param args - arguments for initialization
     * @return the initialized Application
     */
    private Application initialize(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java ScissorStonePaper.jar [options...]");

            parser.printUsage(System.err);
            System.err.println();
            System.err.println("  Example: java ScissorStonePaper " + parser.printExample(OptionHandlerFilter.ALL));
            System.exit(1);
        }

        if (rounds <= 0 || rounds > 10) {
            System.err.println("Please define a round value between 1 and 10 ");
        }

        return this;
    }

    /**
     * Plays the game.
     */
    private void play() {
        List<GameResult> results = new ArrayList<>();
        GameValue user, computer;
        GameResult result;
        for (int i = 0; i < rounds; i++) {
            user = readUserInput();
            computer = generateComputerValue();
            result = game.play(user, computer);
            results.add(result);

            printRound(user, computer, result);
        }

        final long won = results.stream().filter(r -> r == GameResult.PLAYER1_WON).count();
        final long lost = results.stream().filter(r -> r == GameResult.PLAYER2_WON).count();
        System.out.println(playerName + " won " + won + " times");
        System.out.println(playerName + " lost " + lost + " times");
    }

    private void printRound(GameValue playerOne, GameValue computer, GameResult result) {
        System.out.println("");
        System.out.println(playerName + " choosed: " + playerOne.name());
        System.out.println("Computer choosed: " + computer.name());
        switch (result) {
            case PLAYER1_WON:
                System.out.println("You won");
                break;
            case PLAYER2_WON:
                System.out.println("You lost");
                break;
            default:
                System.out.println("This round was a draw");
        }
        System.out.println("");

    }

    /**
     * Generated a random int in range and returns the GameValue on that index.
     *
     * @return GameValue for computer
     */
    private GameValue generateComputerValue() {
        return GameValue.values()[random.nextInt(GameValue.values().length)];
    }

    /**
     * This Method print the options based on @see {@link GameValue} to the user and reads the selection as number.
     *
     * @return GameValue based on user selection
     */
    private GameValue readUserInput() {
        GameValue[] values = GameValue.values();
        Scanner reader = new Scanner(System.in);

        // print options for the user with a +1 on index.
        System.out.println("Please choose ");
        for (GameValue gv : values) {
            System.out.println((gv.ordinal() + 1) + " -> " + gv.name());
        }

        // read the number and correct it to index back.
        int n = -1;
        while (n < 0 || n >= values.length) {
            System.out.print("Your choice: ");
            n = reader.nextInt() - 1;
        }

        return values[n];
    }

}
