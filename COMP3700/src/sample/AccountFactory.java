package sample;

public class AccountFactory {
    public static Account buildAccount(AccountType type) {
        Account account = null;
        switch (type) {
            case OPERATOR:
                account = new Operator();
                break;
            case LEAGUEOWNER:
                account = new LeagueOwner();
                break;
            case PLAYER:
                account = new Player();
                break;
            case SPECTATOR:
                account = new Spectator();
                break;
            case ADVERTISER:
                account = new Advertiser();
                break;
            default:
                // throw some exception
                break;
        }
        return account;
    }
}
