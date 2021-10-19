package sample;
import java.util.ArrayList;

// NOTE: matchController will construct matches.
public class Match extends matchController {

    public boolean matchStatus;
    public int matchID;
    public String matchName;
    public static int matchCounter = 0;
    public ArrayList<Account> playerList;
    public ArrayList<Account> spectatorList;

    public Match() {
        matchID = matchCounter;
        matchStatus = true;
        matchCounter++;
    }

    public boolean checkMatchStatus() {
        return matchStatus;
    }

    // Goes through the process of actually adding a player to the match. First communicates with the matchController to see
    // if player is valid.
    public boolean acceptPlayer(Account player) {
        if (matchController.joinMatch(player)) {
            playerList.add(player);
            return true;
        }
        return false;
    }

    public void acceptSpectator(Account spectator) {
        playerList.add(spectator);
    }

    public int getMatchID() { return matchID; }

    public String getMatchName() { return matchName; }

    public void setMatchName(String name) { matchName = name; }
}
