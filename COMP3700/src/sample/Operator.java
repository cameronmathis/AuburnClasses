package sample;

public class Operator extends Account {

    Operator() {
        super(AccountType.OPERATOR);
        construct();
    }

    @Override
    protected void construct() {
        System.out.println("Building operator");
        // add accessories
    }

    public static boolean verifyAccount(String username) {
        return true;
    }

    public static boolean approveAndChange(Account account, AccountType newType) {
        if (!account.getType().equals(AccountType.OPERATOR) && !account.getType().equals(newType)) {
            account.setType(newType);
            return true;
        }

        return false;
    }
}
