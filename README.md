This is a small command line game of Scissors, Stone, Paper.

# Build
Run on command line: `mvn clean package`
Requirements: Maven, Java 1.8+

# Play the Game
If you build it with maven you can run `java -jar ./target/scissorstonepaper-0.0.1-SNAPSHOT-jar-with-dependencies.jar -r 5 -n "Player Nickname"`

# Example
```
user@host:~/ScissorsStonePaper$ java -jar ./target/scissorstonepaper-0.0.1-SNAPSHOT-jar-with-dependencies.jar -r 1 -n "Hero"
Please choose 
1 -> SCISSORS
2 -> STONE
3 -> PAPER
Your choice: 1

Hero choosed: SCISSORS
Computer choosed: PAPER
You won

Hero won 1 times
Hero lost 0 times
user@host:~/ScissorsStonePaper$ _                                                                                                                                  [master|*]

```
