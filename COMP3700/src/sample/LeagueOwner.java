package sample;

public class LeagueOwner extends Account {

    LeagueOwner() {
        super(AccountType.LEAGUEOWNER);
        construct();
    }

    @Override
    protected void construct() {
        System.out.println("Building league owner");
        // add accessories
    }
}
