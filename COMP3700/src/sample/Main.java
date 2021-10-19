package sample;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    //Variables
    private Stage primaryStage;
    private AnchorPane openingPane;
    private Scene openingScene;
    private Popup PopUp;
    private Account[] accounts = Accounts.accounts;
    private int accountArrayLength = Accounts.accountArrayLength; // Initialize to 5 for the 5 demo accounts created
    private Game[] games = new Game[100];
    private int gameArrayLength = 1; // Initialize to 1 for the 1 game created
    private Account accountLoggedIn;
    private TabPane tabPane;
    private Tab homeTab;
    private Tab leagueTab;
    private Tab followingTab;
    private Tab myAdvertisementsTab;
    private MenuButton optionsBtn;
    private MenuItem defineGameBtn;
    private MenuItem changeTypeBtn;
    private MenuItem createLeagueBtn;
    private MenuItem createMatchBtn;
    private MenuItem joinMatchBtn;
    private MenuItem viewMatchBtn;
    private League[] leagues = Leagues.leagues;
    private int leagueArrayLength = Leagues.leagueArrayLength;
    private Match[] matches = matchController.getMatches();
    private int matchArrayLength = matchController.getMatchAmount();

    // GUI Interface
    @Override
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage; //sets this primaryStage as 'the' primaryStage
        openingPane = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        tabPane = (TabPane) openingPane.lookup("#tabPane");
        homeTab = tabPane.getTabs().get(0);
        leagueTab = tabPane.getTabs().get(1);
        followingTab = tabPane.getTabs().get(2);
        myAdvertisementsTab = tabPane.getTabs().get(3);
        tabPane.getTabs().remove(myAdvertisementsTab);
        // used to chose which options to display
        optionsBtn = (MenuButton) openingPane.lookup("#optionsBtn");
        defineGameBtn = optionsBtn.getItems().get(0);
        createLeagueBtn = optionsBtn.getItems().get(1);
        changeTypeBtn = optionsBtn.getItems().get(2);
        createMatchBtn = optionsBtn.getItems().get(3);
        joinMatchBtn = optionsBtn.getItems().get(4);
        viewMatchBtn = optionsBtn.getItems().get(5);
        optionsBtn.getItems().remove(defineGameBtn);
        optionsBtn.getItems().remove(createLeagueBtn);
        optionsBtn.getItems().remove(changeTypeBtn);
        optionsBtn.getItems().remove(createMatchBtn);
        optionsBtn.getItems().remove(joinMatchBtn);

        /**
         * MAIN STAGE CREATED
         * Main Stage and Scene Created and shown
         */
        openingScene = new Scene(openingPane); //creates a new scene from 'MainPage.fxml'
        primaryStage.setScene(openingScene); //sets the scene on the stage
        primaryStage.setAlwaysOnTop(false); //set to false to show popups
        primaryStage.setResizable(false); //makes app able to be resized
        primaryStage.show(); //shows the primaryStage
        newOrReturningUserPopUp(); // ask user if they are a new or returning user

        /** Create 5 demo accounts */
        // Demo operator account
        accounts[0] = AccountFactory.buildAccount(AccountType.OPERATOR);
        accounts[0].setEmail("operator@email.com");
        accounts[0].setUsername("operator");
        accounts[0].setPassword("password");
        // Demo league owner account
        accounts[1] = AccountFactory.buildAccount(AccountType.LEAGUEOWNER);
        accounts[1].setEmail("leagueowner@email.com");
        accounts[1].setUsername("leagueowner");
        accounts[1].setPassword("password");
        // Demo league owner account
        accounts[2] = AccountFactory.buildAccount(AccountType.PLAYER);
        accounts[2].setEmail("player@email.com");
        accounts[2].setUsername("player");
        accounts[2].setPassword("password");
        // Demo league owner account
        accounts[3] = AccountFactory.buildAccount(AccountType.SPECTATOR);
        accounts[3].setEmail("spectator@email.com");
        accounts[3].setUsername("spectator");
        accounts[3].setPassword("password");
        // Demo league owner account
        accounts[4] = AccountFactory.buildAccount(AccountType.ADVERTISER);
        accounts[4].setEmail("advertiser@email.com");
        accounts[4].setUsername("advertiser");
        accounts[4].setPassword("password");

        /** Create 1 demo game */
        games[0] = new Game.GameDefiner("TicTacToe", 2).requiredPoints(0).define();

        /** Create 1 demo league */
        leagues[0] = new League();
        leagues[0].setLeagueName("League Name");
        leagues[0].setNumMembers("100");

        /**
         * TAB LISTENER
         * Listen for which tab is selected
         */
        leagueTab.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event t) {
                if (leagueTab.isSelected() && accountLoggedIn != null) {
                    if (accountLoggedIn.getType().equals(AccountType.LEAGUEOWNER)) {
                        System.out.println("league");
                    }
                }
            }
        });

        defineGameBtn.setOnAction(event -> {
            defineGamePopUp();
        });

        changeTypeBtn.setOnAction(event -> {
            accountTypePopUp(1);
        });

        createLeagueBtn.setOnAction(event -> {
            createLeaguePopUp();
        });

        createMatchBtn.setOnAction(event -> {
            createMatchPopUp();
        });

        joinMatchBtn.setOnAction(event -> {
            joinMatchPopUp();
        });

        viewMatchBtn.setOnAction(event -> {
            viewMatchPopUp();
        });
    }

    /**
     * HIDE POPUP METHOD
     * Resumes the game
     */
    private void hidePopUp() {
        try {
            PopUp.hide();
            PopUp = null;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * NEW OR RETURNING USER POPUP
     * PopUp to ask user if they are new or not
     */
    private void newOrReturningUserPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane newOrReturningUserPopUpPane = null; //calls popup menu created in 'newOrReturningUserPopUp.fxml' file

        try {
            newOrReturningUserPopUpPane = FXMLLoader.load(getClass().getResource("newOrReturningUserPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(newOrReturningUserPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        openingPane.requestFocus();

        Button createAccountBtn = (Button) newOrReturningUserPopUpPane.lookup("#createAccount");
        createAccountBtn.setOnAction(event -> {
            hidePopUp();
            accountTypePopUp(0);

        });

        Button loginBtn = (Button) newOrReturningUserPopUpPane.lookup("#login");
        loginBtn.setOnAction(event -> {
            hidePopUp();
            accountLoginPopUp();
        });
    }

    /**
     * ACCOUNT TYPE POPUP
     * PopUp to ask user to select an account type
     */
    private void accountTypePopUp(int x) {
        PopUp = new Popup(); //creates new popup

        TitledPane accountTypePopUpPane = null; //calls popup menu created in 'accountTypePopUp.fxml' file

        try {
            accountTypePopUpPane = FXMLLoader.load(getClass().getResource("accountTypePopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(accountTypePopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        openingPane.requestFocus();

        Button leagueOwnerBtn = (Button) accountTypePopUpPane.lookup("#leagueOwner");
        leagueOwnerBtn.setOnAction(event -> {
            hidePopUp();
            accounts[accountArrayLength] = AccountFactory.buildAccount(AccountType.LEAGUEOWNER);
            if (x == 0) {
                createAccountPopUp();
            } else if (x == 1) {
                if (Operator.approveAndChange(accountLoggedIn, AccountType.LEAGUEOWNER)) {
                    setOptionsBtn();
                    accountTypeChangedPopUp();
                } else {
                    accountTypeNotChangedPopUp();
                }
            }
        });

        Button playerBtn = (Button) accountTypePopUpPane.lookup("#player");
        playerBtn.setOnAction(event -> {
            hidePopUp();
            accounts[accountArrayLength] = AccountFactory.buildAccount(AccountType.PLAYER);
            if (x == 0) {
                createAccountPopUp();
            } else if (x == 1) {
                if (Operator.approveAndChange(accountLoggedIn, AccountType.PLAYER)) {
                    setOptionsBtn();
                    accountTypeChangedPopUp();
                } else {
                    accountTypeNotChangedPopUp();
                }
            }
        });

        Button spectatorBtn = (Button) accountTypePopUpPane.lookup("#spectator");
        spectatorBtn.setOnAction(event -> {
            hidePopUp();
            accounts[accountArrayLength] = AccountFactory.buildAccount(AccountType.SPECTATOR);
            if (x == 0) {
                createAccountPopUp();
            } else if (x == 1) {
                if (Operator.approveAndChange(accountLoggedIn, AccountType.SPECTATOR)) {
                    setOptionsBtn();
                    accountTypeChangedPopUp();
                } else {
                    accountTypeNotChangedPopUp();
                }
            }
        });

        Button advertiserBtn = (Button) accountTypePopUpPane.lookup("#advertiser");
        advertiserBtn.setOnAction(event -> {
            hidePopUp();
            accounts[accountArrayLength] = AccountFactory.buildAccount(AccountType.ADVERTISER);
            if (x == 0) {
                createAccountPopUp();
            } else if (x == 1) {
                if (Operator.approveAndChange(accountLoggedIn, AccountType.ADVERTISER)) {
                    setOptionsBtn();
                    accountTypeChangedPopUp();
                } else {
                    accountTypeNotChangedPopUp();
                }
            }
        });
    }

    /**
     * CREATE ACCOUNT POPUP
     * PopUp to create new account
     */
    public void createAccountPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane createAccountPopUpPane = null; //calls popup menu created in 'createAccountPopUp.fxml' file

        try {
            createAccountPopUpPane = FXMLLoader.load(getClass().getResource("createAccountPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(createAccountPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        TextField t1 = ((TextField) createAccountPopUpPane.lookup("#email"));
        t1.requestFocus();

        Button enterBtn = (Button) createAccountPopUpPane.lookup("#enter");
        TitledPane finalCreateAccountPopUpPane = createAccountPopUpPane;
        enterBtn.setOnAction(event -> {
            TextField email = new TextField();
            TextField username = new TextField();
            TextField password = new TextField();
            if (!(((TextField) finalCreateAccountPopUpPane.lookup("#email")).getText().equals(""))) {
                email = (TextField) finalCreateAccountPopUpPane.lookup("#email");
            } else {
                createAccountPopUp();
            }
            if (!(((TextField) finalCreateAccountPopUpPane.lookup("#username")).getText().equals(""))) {
                username = (TextField) finalCreateAccountPopUpPane.lookup("#username");
            }else {
                createAccountPopUp();
            }
            if (!(((TextField) finalCreateAccountPopUpPane.lookup("#password")).getText().equals(""))) {
                password = (TextField) finalCreateAccountPopUpPane.lookup("#password");
            }else {
                createAccountPopUp();
            }
            if (!Operator.verifyAccount(username.getText())) {
                hidePopUp();
                return;
            }

            if (!AccountController.checkCredentials(username, email, password)) {
                hidePopUp();
                invalidCredentialsPopUp();
                return;
            }

            accounts[accountArrayLength].setEmail(email.getText());
            accounts[accountArrayLength].setUsername(username.getText());
            accounts[accountArrayLength].setPassword(password.getText());
            setLoggedInAccount(accountArrayLength);
            hidePopUp();
            accountArrayLength++;
        });
    }

    /**
     * INVALID CREDENTIALS POPUP
     * PopUp for when a player tries to enter a username or email that already exist or is null
     */
    private void invalidCredentialsPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane invalidCredentialsPopUpPane = null; //calls popup menu created in 'invalidCredentialsPopUp.fxml' file

        try {
            invalidCredentialsPopUpPane = FXMLLoader.load(getClass().getResource("invalidCredentialsPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(invalidCredentialsPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) invalidCredentialsPopUpPane.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            hidePopUp();
            createAccountPopUp();
        });
    }

    /**
     * ACCOUNT LOGIN POPUP
     * PopUp to login to account
     */
    private void accountLoginPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane accountLoginPopUpPane = null; //calls popup menu created in 'accountLoginPopUp.fxml' file

        try {
            accountLoginPopUpPane = FXMLLoader.load(getClass().getResource("accountLoginPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(accountLoginPopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        TextField t1 = ((TextField) accountLoginPopUpPane.lookup("#username"));
        t1.requestFocus();

        Button enterBtn = (Button) accountLoginPopUpPane.lookup("#enter");
        TitledPane finalCreateAccountPopUpPane = accountLoginPopUpPane;
        enterBtn.setOnAction(event -> {
            TextField username = new TextField();
            TextField password = new TextField();
            ;
            if (!(((TextField) finalCreateAccountPopUpPane.lookup("#username")).getText().equals(""))) {
                username = (TextField) finalCreateAccountPopUpPane.lookup("#username");
            } else {
                accountLoginPopUp();
            }
            if (!(((TextField) finalCreateAccountPopUpPane.lookup("#password")).getText().equals(""))) {
                password = (TextField) finalCreateAccountPopUpPane.lookup("#password");
            } else {
                accountLoginPopUp();
            }

            for (int i = 0; i < accountArrayLength; i++) {
                if (accounts[i].getUsername().equals(username.getText()) && accounts[i].getPassword().equals(password.getText())) {
                    setLoggedInAccount(i);
                    hidePopUp();
                    return;
                }
            }
            hidePopUp();
            incorrectLoginPopUp();
        });
    }

    /**
     * NAME TOO LONG POPUP
     * PopUp for when a player tries to enter an invalid username
     */
    private void incorrectLoginPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane incorrectLoginPopUp = null; //calls popup menu created in 'incorrectLoginPopUp.fxml' file

        try {
            incorrectLoginPopUp = FXMLLoader.load(getClass().getResource("incorrectLoginPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(incorrectLoginPopUp); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) incorrectLoginPopUp.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            hidePopUp();
            accountLoginPopUp();
        });
    }

    /**
     * ACCOUNT TYPE CHANGED POPUP
     * PopUp for when a account change is approved
     */
    private void accountTypeChangedPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane accountTypeChangedPopUp = null; //calls popup menu created in 'accountTypeChangedPopUp.fxml' file

        try {
            accountTypeChangedPopUp = FXMLLoader.load(getClass().getResource("accountTypeChangedPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(accountTypeChangedPopUp); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) accountTypeChangedPopUp.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            hidePopUp();
        });
    }

    /**
     * ACCOUNT TYPE NOT CHANGED POPUP
     * PopUp for when a account change is not approved
     */
    private void accountTypeNotChangedPopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane accountTypeNotChangedPopUp = null; //calls popup menu created in 'accountTypeNotChangedPopUp.fxml' file

        try {
            accountTypeNotChangedPopUp = FXMLLoader.load(getClass().getResource("accountTypeNotChangedPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(accountTypeNotChangedPopUp); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) accountTypeNotChangedPopUp.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            hidePopUp();
        });
    }

    /**
     * DEFINE GAME POPUP
     * PopUp to define a new game
     */
    public void defineGamePopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane defineGamePopUp = null; //calls popup menu created in 'defineGamePopUp.fxml' file

        try {
            defineGamePopUp = FXMLLoader.load(getClass().getResource("defineGamePopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(defineGamePopUp); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        TextField t1 = ((TextField) defineGamePopUp.lookup("#name"));
        t1.requestFocus();

        Button enterBtn = (Button) defineGamePopUp.lookup("#enter");
        TitledPane finalDefineGamePopUp = defineGamePopUp;
        enterBtn.setOnAction(event -> {
            TextField name = new TextField();
            TextField numPlayers = new TextField();
            TextField requiredPoints = new TextField();
            if (!(((TextField) finalDefineGamePopUp.lookup("#name")).getText().equals(""))) {
                name = (TextField) finalDefineGamePopUp.lookup("#name");
            } else {
                defineGamePopUp();
            }
            if (!(((TextField) finalDefineGamePopUp.lookup("#numPlayers")).getText().equals(""))) {
                numPlayers = (TextField) finalDefineGamePopUp.lookup("#numPlayers");
            }else {
                defineGamePopUp();
            }
            if (!(((TextField) finalDefineGamePopUp.lookup("#requiredPoints")).getText().equals(""))) {
                requiredPoints = (TextField) finalDefineGamePopUp.lookup("#requiredPoints");
            }else {
                requiredPoints = null;
            }
            if (requiredPoints != null) {
                games[gameArrayLength] = new Game.GameDefiner(name.getText(), Integer.parseInt(numPlayers.getText())).requiredPoints(Integer.parseInt(requiredPoints.getText())).define();
            } else {
                games[gameArrayLength] = new Game.GameDefiner(name.getText(), Integer.parseInt(numPlayers.getText())).define();
            }
            hidePopUp();
            gameArrayLength++;
        });
    }

    /**
     * SET ACCOUNT LOGGED IN
     * Method to set the logged in account and display appropriate things
     */
    private void setLoggedInAccount(int i) {
        accountLoggedIn = accounts[i];

        if (accountLoggedIn.getType().equals(AccountType.OPERATOR)) {
            optionsBtn.getItems().add(defineGameBtn);
        } else {
            optionsBtn.getItems().add(changeTypeBtn);
        }
        if (accountLoggedIn.getType().equals(AccountType.LEAGUEOWNER)) {
            optionsBtn.getItems().add(createLeagueBtn);
        }
        if (accountLoggedIn.getType().equals(AccountType.LEAGUEOWNER)) {
            optionsBtn.getItems().add(createMatchBtn);
        }
        if (accountLoggedIn.getType().equals(AccountType.PLAYER)) {
            optionsBtn.getItems().add(joinMatchBtn);
        }
        if (accountLoggedIn.getType().equals(AccountType.ADVERTISER)) {
            tabPane.getTabs().add(myAdvertisementsTab);
        }
        if (accountLoggedIn.getType().equals(AccountType.SPECTATOR)) {
            optionsBtn.getItems().add(viewMatchBtn);
        }
    }

    /**
     * SET OPTIONS BUTTON
     * Method to set the options button
     */
    private void setOptionsBtn() {
        if (accountLoggedIn.getType().equals(AccountType.OPERATOR) && !optionsBtn.getItems().contains(defineGameBtn)) {
            optionsBtn.getItems().add(defineGameBtn);
        } else if (!optionsBtn.getItems().contains(changeTypeBtn)) {
            optionsBtn.getItems().add(changeTypeBtn);
        } else if (optionsBtn.getItems().contains(defineGameBtn)) {
            optionsBtn.getItems().remove(defineGameBtn);
        }
        if (accountLoggedIn.getType().equals(AccountType.LEAGUEOWNER) && !optionsBtn.getItems().contains(createLeagueBtn)) {
            optionsBtn.getItems().add(createLeagueBtn);
        } else if (optionsBtn.getItems().contains(createLeagueBtn)) {
            optionsBtn.getItems().remove(createLeagueBtn);
        }
        if (accountLoggedIn.getType().equals(AccountType.LEAGUEOWNER) && !optionsBtn.getItems().contains(createMatchBtn)) {
            optionsBtn.getItems().add(createMatchBtn);
        } else if (optionsBtn.getItems().contains(createMatchBtn)) {
            optionsBtn.getItems().remove(createMatchBtn);
        }
        if (accountLoggedIn.getType().equals(AccountType.PLAYER) && !optionsBtn.getItems().contains(createMatchBtn)) {
            optionsBtn.getItems().add(joinMatchBtn);
        } else if (optionsBtn.getItems().contains(joinMatchBtn)) {
            optionsBtn.getItems().remove(joinMatchBtn);
        }
        if (accountLoggedIn.getType().equals(AccountType.SPECTATOR) && !optionsBtn.getItems().contains(viewMatchBtn)) {
            optionsBtn.getItems().add(joinMatchBtn);
        } else if (optionsBtn.getItems().contains(viewMatchBtn)) {
            optionsBtn.getItems().remove(viewMatchBtn);
        }
        if (accountLoggedIn.getType().equals(AccountType.ADVERTISER) && !tabPane.getTabs().contains(myAdvertisementsTab)) {
            tabPane.getTabs().add(myAdvertisementsTab);
        } else if (tabPane.getTabs().contains(myAdvertisementsTab)) {
            tabPane.getTabs().remove(myAdvertisementsTab);
        }
    }

    public void createLeaguePopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane createLeaguePopUpPane = null; //calls popup menu created in 'createLeaguePopUp.fxml' file

        try {
            createLeaguePopUpPane = FXMLLoader.load(getClass().getResource("createLeaguePopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(createLeaguePopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        TextField t1 = ((TextField) createLeaguePopUpPane.lookup("#leagueName"));
        t1.requestFocus();

        Button enterBtn = (Button) createLeaguePopUpPane.lookup("#enter");
        TitledPane finalCreateLeaguePopUpPane = createLeaguePopUpPane;
        enterBtn.setOnAction(event -> {
            TextField leagueName = new TextField();
            TextField numMembers = new TextField();
            if (!(((TextField) finalCreateLeaguePopUpPane.lookup("#leagueName")).getText().equals(""))) {
                leagueName = (TextField) finalCreateLeaguePopUpPane.lookup("#leagueName");
            } else {
                createLeaguePopUp();
            }
            if (!(((TextField) finalCreateLeaguePopUpPane.lookup("#numMembers")).getText().equals(""))) {
                numMembers = (TextField) finalCreateLeaguePopUpPane.lookup("#numMembers");
            } else {
                createLeaguePopUp();
            }

            if (!Leagues.checkLeagueName(leagueName)) {
                hidePopUp();
                invalidLeagueNamePopUp();
                return;
            }

            leagues[leagueArrayLength] = new League();
            leagues[leagueArrayLength].setLeagueName(leagueName.getText());
            leagues[leagueArrayLength].setNumMembers(numMembers.getText());
            leagueArrayLength++;
            hidePopUp();
        });
    }

    private void createMatchPopUp() {
        PopUp = new Popup();

        TitledPane createMatchPopUpPane = null;

        try {
            createMatchPopUpPane = FXMLLoader.load(getClass().getResource("createMatchPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(createMatchPopUpPane);


        PopUp.show(primaryStage);

        assert createMatchPopUpPane != null;
        TextField t1 = ((TextField) createMatchPopUpPane.lookup("#MatchName"));

        Button enterBtn = (Button) createMatchPopUpPane.lookup("#enter");
        TitledPane finalCreateMatchPopUpPane = createMatchPopUpPane;
        enterBtn.setOnAction(event -> {
            TextField MatchName = new TextField();
            if (!(((TextField) finalCreateMatchPopUpPane.lookup("#MatchName")).getText().equals(""))) {
                MatchName = (TextField) finalCreateMatchPopUpPane.lookup("#MatchName");
            } else {
                createMatchPopUp();
            }

            matches[matchArrayLength] = new Match();
            matches[matchArrayLength].setMatchName(MatchName.getText());
            matchArrayLength++;
            hidePopUp();
        });
    }

    private void joinMatchPopUp() {
        PopUp = new Popup();

        TitledPane joinMatchPopUpPane = null;

        try {
            joinMatchPopUpPane = FXMLLoader.load(getClass().getResource("joinMatchPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(joinMatchPopUpPane);


        PopUp.show(primaryStage);

        assert joinMatchPopUpPane != null;
        TextField t1 = ((TextField) joinMatchPopUpPane.lookup("#MatchName"));

        Button enterBtn = (Button) joinMatchPopUpPane.lookup("#enter");
        TitledPane finaljoinMatchPopUpPane = joinMatchPopUpPane;
        enterBtn.setOnAction(event -> {
            TextField MatchName = new TextField();
            if (!(((TextField) finaljoinMatchPopUpPane.lookup("#MatchName")).getText().equals(""))) {
                MatchName = (TextField) finaljoinMatchPopUpPane.lookup("#MatchName");
            } else {
                joinMatchPopUp();
            }

            boolean found = false;
            for (int i = 0; i < matchArrayLength; i++) {
                if (MatchName.getText().equals(matches[i].getMatchName())) {
                    matches[i].acceptPlayer(accountLoggedIn);
                    found = true;
                }
            }
            if (!found) {
                invalidMatchNamePopUp();
            }
            hidePopUp();
        });
    }

    private void viewMatchPopUp() {
        PopUp = new Popup();

        TitledPane viewMatchPopUpPane = null;

        try {
            viewMatchPopUpPane = FXMLLoader.load(getClass().getResource("viewMatchPopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(viewMatchPopUpPane);


        PopUp.show(primaryStage);

        assert viewMatchPopUpPane != null;
        TextField t1 = ((TextField) viewMatchPopUpPane.lookup("#MatchName"));

        Button enterBtn = (Button) viewMatchPopUpPane.lookup("#enter");
        TitledPane finalviewMatchPopUpPane = viewMatchPopUpPane;
        enterBtn.setOnAction(event -> {
            TextField MatchName = new TextField();
            if (!(((TextField) finalviewMatchPopUpPane.lookup("#MatchName")).getText().equals(""))) {
                MatchName = (TextField) finalviewMatchPopUpPane.lookup("#MatchName");
            } else {
                viewMatchPopUp();
            }

            boolean found = false;
            for (int i = 0; i < matchArrayLength; i++) {
                if (MatchName.getText().equals(matches[i].getMatchName())) {
                    matches[i].acceptSpectator(accountLoggedIn);
                    found = true;
                }
            }
            if (!found) {
                invalidMatchNamePopUp();
            }
            hidePopUp();
        });
    }

    private void invalidLeagueNamePopUp() {
        PopUp = new Popup(); //creates new popup

        TitledPane invalidLeagueNamePopUpPane = null; //calls popup menu created in 'invalidLeagueNamePopUp.fxml' file

        try {
            invalidLeagueNamePopUpPane = FXMLLoader.load(getClass().getResource("invalidLeagueNamePopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(invalidLeagueNamePopUpPane); //adds the popup (child) created in fxml file to the popup (parent) created

        //show popup on primaryStage
        PopUp.show(primaryStage);

        Button dismissBtn = (Button) invalidLeagueNamePopUpPane.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            hidePopUp();
            createLeaguePopUp();
        });
    }

    private void invalidMatchNamePopUp() {
        PopUp = new Popup();
        System.out.println("Invalid name request");

        TitledPane invalidMatchNamePopUpPane = null;

        try {
            invalidMatchNamePopUpPane = FXMLLoader.load(getClass().getResource("invalidMatchNamePopUp.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        PopUp.getContent().add(invalidMatchNamePopUpPane);

        PopUp.show(primaryStage);

        Button dismissBtn = (Button) invalidMatchNamePopUpPane.lookup("#dismiss");

        dismissBtn.setOnAction(event -> {
            hidePopUp();
            invalidMatchNamePopUp();
        });
    }

    public static void main(String[] args) { Application.launch(args); }
}
