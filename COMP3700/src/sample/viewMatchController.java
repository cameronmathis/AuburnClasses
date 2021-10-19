package sample;

public class viewMatchController {


    public void spectateTournamentMatch(int matchID, Tournament tournament, Spectator spectator) {

tournament.canViewMatch(matchID);
spectator.displayMatch(matchID);
    }

}
