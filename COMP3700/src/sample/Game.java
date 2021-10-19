package sample;

public class Game {
    //All final attributes
    private final String gameName; // required
    private final int numPlayers; // required
    private final int requiredPoints; // optional

    Game(GameDefiner definer) {
        this.gameName = definer.gameName;
        this.numPlayers = definer.numPlayers;
        this.requiredPoints = definer.requiredPoints;
    }

    //All getter, and NO setter to provide immutability
    public String getGameName() {
        return gameName;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    public int getRequiredPoints() {
        return requiredPoints;
    }

    @Override
    public String toString() {
        return "Game: " + this.gameName + ", " + this.numPlayers + ", " + this.requiredPoints;
    }

    public static class GameDefiner {
        public final String gameName;
        public final int numPlayers;
        public int requiredPoints;

        public GameDefiner(String gameName, int numPlayers) {
            this.gameName = gameName;
            this.numPlayers = numPlayers;
        }

        public GameDefiner requiredPoints(int requiredPoints) {
            this.requiredPoints = requiredPoints;
            return this;
        }

        //Return the finally constructed Game object
        public Game define() {
            Game game = new Game(this);
            validateGameObject(game);
            return game;
        }

        private void validateGameObject(Game game) {
            //Do some basic validations to check
            //if game object does not break any assumption of system
        }

    }
}
