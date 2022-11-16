import java.util.ArrayList;
import java.util.Random;

public class Game {
    private boolean isMenuRunning = true;
    private boolean isGameRunning = false;
    private int currentRound = 0;
    private static Random rand = new Random();
    private Character duelistOne;
    private Character duelistTwo;
    private ArrayList<Character> duelists = new ArrayList<>();
    private static Menu menu;
    private static String log;
    private static String[] names = {"Dumbledore", "Snape", "Thanos el rompe anos", "Thor", "Shrek", "Juan Soto", "Jeff Bezos", "Justiniano", "Snow"};


    private static int getRandomNumberBetween(int min, int max) {
        return rand.nextInt(max - min) + min;
    }
    private static String getRandomName () {
        return names[rand.nextInt(names.length)];
    }

    private Character generateRandomCharacter() {
        int race = rand.nextInt(2 - 1) + 1;
        if(race == 1) {
            return new Warrior(getRandomName(), getRandomNumberBetween(100, 200), getRandomNumberBetween(10, 50), getRandomNumberBetween(1, 10));  // Warrior
        }
        return new Wizard(getRandomName(), getRandomNumberBetween(50, 100), getRandomNumberBetween(10, 50), getRandomNumberBetween(1, 50));  // Wizard

    }

    public void nextRound() {
        duelistOne.attack(duelistTwo);
        duelistTwo.attack(duelistOne);
    }

    private void setup() {
        duelists.add(generateRandomCharacter());
        duelists.add(generateRandomCharacter());
    }

    public void init() {
        while(isMenuRunning){
            int playerMenuChoice = Menu.letPlayerChooseGameMode();

            if (playerMenuChoice == 2) {
                isMenuRunning = false;
                setup();
            }else if (playerMenuChoice == 1) {
                int[] playerCharacterStatsChoice = Menu.createCharacterByInput();
                System.out.println("Insert Character name: ");
                String playerName = Menu.scanner.nextLine();
                if (playerCharacterStatsChoice[0] == 1) {
                    duelists.add(new Warrior(playerName, playerCharacterStatsChoice[1], playerCharacterStatsChoice[2], playerCharacterStatsChoice[3]));
                }
                else if (playerCharacterStatsChoice[0] == 2) {
                    duelists.add(new Wizard(playerName, playerCharacterStatsChoice[1], playerCharacterStatsChoice[2], playerCharacterStatsChoice[3]));
                }

            }
        }


    }
}
