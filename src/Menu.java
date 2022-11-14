public class Menu extends Game {

    private final Scanner scanner = new Scanner(System.in);
    private final boolean exit = false;

    private int option;

    public Menu(int option) {
        this.option = option;
    }

    public static void menu() {

        int option;
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        GameData gameData = new GameData();
        Land land = new Land();


        while (true) {


            option = ConsoleQuery.queryToConsole(scanner, ConsoleColors.BLACK_BACKGROUND + ConsoleColors.YELLOW_BOLD_BRIGHT + "WELLCOME TO THE GAME: HOLLIGANS OF JAVA: ",
                    new String[]{"Play new custom party", "Play new random party", "Play last party", "Check the graveyard", "Read the readme file", "Exit"}, 1, 6);

            switch (option) {
                case 1:
                    game.startCustomParty(gameData);
                    FileReadAndWrite.writeFile(gameData);
                    game.startGame(land, gameData);
                    break;
                case 2:
                    game.randomParty(gameData);
                    FileReadAndWrite.writeFile(gameData);
                    game.startGame(land, gameData);
                    break;
                case 3:
                    gameData = game.playLastParty();
                    game.startGame(land, gameData);
                    break;
                case 4:
                    land.printGraveyard();
                    break;
                case 5:
                    ReadMe.showReadMe();
                    break;
                case 6:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Choose the correct option.");
            }
        }
    }
}

