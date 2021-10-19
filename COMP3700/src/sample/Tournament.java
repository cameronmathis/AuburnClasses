package sample;

import sample.Advertisement;
import sample.Match;

// NOTE: tournamentController will construct tournaments.
public class Tournament extends tournamentController{

    public static Match[] matches = new Match[100];
    public static int matchesArrayLength = 10;
    public static int matchCounter = 0;
    public static Advertisement ad;
    public static boolean canViewMatch(int matchID) {
        Match match1 = new Match();
        match1 = findMatch(matchID);
        if (match1 == null) //returns false if match does not exist
            return false;
        return match1.matchStatus;
    }

    public static Match findMatch(int matchID) {
        for(int i = 0; i < matchesArrayLength; i++) {
            if(matchID == matches[i].matchID)
                return matches[i];
        }
return null;
    }

    public static Advertisement getAdvertisment() {
        return ad;
    }
}
