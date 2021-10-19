package sample;

/**
 * tournamentController facilitates the creation of tournaments and stores all actively running tournaments.
 */
public class tournamentController {
   private static Tournament[] tournaments = new Tournament[100];
   private static int tournamentAmount = 0;

   // Creates a tournament object and stores it in array for easy finding later.
   public Tournament createTournament() {
      Tournament tempTournament = new Tournament();
      tournamentAmount++;
      tournaments[tournamentAmount] = tempTournament;
      return tempTournament;
   }

   // Returns tournament amount.
   public int getTournamentAmount() {
      return tournamentAmount;
   }
}
