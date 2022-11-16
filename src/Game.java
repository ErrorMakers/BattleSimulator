import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Game {
    private int currentRound = 0;
    private static Random rand = new Random();
    private Character duelistOne;
    private Character duelistTwo;
    private ArrayList<Character> duelists = new ArrayList<>();
    private Menu menu;
    private static String log;
    private static String[] names = {"Dumbledore", "Snape", "Thanos el rompe anos", "Thor", "Shrek", "Juan Soto", "Jeff Bezos", "Justiniano", "Snow"};
    private Map<String, Object> gameSetup = new HashMap<>();


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

    private void createRandomDuelists() {
        duelists.add(generateRandomCharacter());
        duelists.add(generateRandomCharacter());
    }

    public void setup() {
        if (gameSetup.get("mode").equals("random")) createRandomDuelists();
        duelistOne = duelists.get(0);
        duelistTwo = duelists.get(1);
        System.out.println("The clash between " + duelistOne.toString() + " vs " + duelistTwo.toString() + " is about to start!");
    }

    public void init() {
        Character duelist;
        menu = new Menu();
        while(menu.isChoosingGameMode()){
            int playerMenuChoice = menu.letPlayerChooseGameMode();
            if (playerMenuChoice == 2) {
                gameSetup.put("mode", "random");
                setup();
            }else if (playerMenuChoice == 1) {
                gameSetup.put("mode", "manual");
                while(duelists.size() < 2) {
                    System.out.println("Fill the following inputs for the character number " + (duelists.size() + 1));
                    String playerName = menu.createName();
                    int[] playerCharacterStatsChoice = menu.createCharacterByInput();
                    if (playerCharacterStatsChoice[0] == 1) {
                        duelist = new Warrior(playerName, playerCharacterStatsChoice[1], playerCharacterStatsChoice[2], playerCharacterStatsChoice[3]);
                        duelists.add(duelist);
                    }
                    else {
                        duelist = new Wizard(playerName, playerCharacterStatsChoice[1], playerCharacterStatsChoice[2], playerCharacterStatsChoice[3]);
                        duelists.add(duelist);
                    }
                    System.out.println("Created Character: " + duelist.toString());
                }
                menu.setChoosingGameMode(false);
            }
        }


    }
}
