package sample;

public class Leagues {

    public static League[] leagues = new League[100];
    public static int leagueArrayLength = 1;

    public static boolean checkLeagueName(javafx.scene.control.TextField leagueName) {
        for (int i = 0; i < leagueArrayLength; i++) {
            if (Leagues.leagues[i].getLeagueName().equals(leagueName.getText())) {
                return false;
            }
        }
        return true;
    }
}
