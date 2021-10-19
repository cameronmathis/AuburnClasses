package sample;

public class Spectator extends Account {

    Spectator() {
        super(AccountType.SPECTATOR);
        construct();
    }

    @Override
    protected void construct() {
        System.out.println("Building spectator");
        // add accessories
    }

    public static void displayMatch(int matchID) {
        //streams the match to the spectator requesting the match.
    }

}
