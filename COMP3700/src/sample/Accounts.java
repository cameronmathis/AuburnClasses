package sample;

import javafx.scene.control.TextField;

public class Accounts {
    public static Account[] accounts = new Account[100];
    public static int accountArrayLength = 5;

    public static boolean checkEmail(javafx.scene.control.TextField email) {
        for (int i = 0; i < accountArrayLength; i++) {
            if (Accounts.accounts[i].getEmail().equals(email.getText())) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkUsername(javafx.scene.control.TextField username) {
        for (int i = 0; i < accountArrayLength; i++) {
            if (Accounts.accounts[i].getUsername().equals(username.getText())) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkPassword(javafx.scene.control.TextField password) {
        TextField empty = new TextField();
        if (empty.equals(password.getText().trim())) {
            return false;
        }
        return true;
    }

    public static Account find(String username) {
        for (int i = 0; i < accountArrayLength; i++) {
            if (accounts[i].getUsername().equals(username)) {
                return accounts[i];
            }
        }
        return null;
    }

    public static boolean canJoinMatch(String username) {
        Account foundAccount = find(username);
        if (foundAccount == null) {
            return false;
        }
        AccountType usernameType = foundAccount.getType();
        if (usernameType.toString().equals("Player")) {
            return true;
        }
        return false;
    }
}
