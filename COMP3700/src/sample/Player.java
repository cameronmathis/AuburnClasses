package sample;

public class Player extends Account {

    Player() {
        super(AccountType.PLAYER);
        construct();
    }

    @Override
    protected void construct() {
        System.out.println("Building player");
        // add accessories
    }

    private int ratingPoints = 0;

    public int getRatingPoints() {
        return ratingPoints;
    }

    public void setRatingPoints(int ratingPoints) {
        this.ratingPoints = ratingPoints;
    }
}
