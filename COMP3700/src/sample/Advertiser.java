package sample;

public class Advertiser extends Account {

    Advertiser() {
        super(AccountType.ADVERTISER);
        construct();
    }

    @Override
    protected void construct() {
        System.out.println("Building advertiser");
        // add accessories
    }
}
