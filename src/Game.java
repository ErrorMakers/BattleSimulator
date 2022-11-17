import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.Thread.sleep;

public class Game {
    private int currentRound = 0;
    public static Random rand = new Random();
    private Character duelistOne;
    private Character duelistTwo;
    private ArrayList<Character> duelists = new ArrayList<>();
    private String[] charactersData;
    private Menu menu;
    //private static String log;
    private static final String[] warriorNames = {"Hercules", "Genghis", "Rompeanos", "Thor", "Shrek", "JuanSoto", "JeffBezos", "Justiniano", "Snow", "ElBicho"};
    private static final String[] wizardNames = {"Snape", "Dumbledore", "Harry", "Hermione", "Isabella", "Elizabeth", "Rachel", "Michelle", "McGonagall"};
    private Map<String, Object> gameSetup = new HashMap<>();
    private boolean isBattling = true;
    private boolean isRestart = false;


    /*
    Creamos el método getRandomNumberBetween que permitirá generar los numeros aleatorios que
    son necesarios para definir las estadísticas de los jugadores y será usados en los métodos
    posteriores
     */
    private static int getRandomNumberBetween(int min, int max) {
        return rand.nextInt(max - min) + min;
    }
    private static String getRandomWarriorName () {
        return warriorNames[rand.nextInt(warriorNames.length)];
    }
    private static String getRandomWizardName () {
        return wizardNames[rand.nextInt(wizardNames.length)];
    }

    /*
    En este método siguiente se llamará al método getRandomNumberBetween para generar números aleatorios
    y añadirlos a su correspondiente propiedad.
     */

    private Character generateRandomCharacter() {
        int race = rand.nextInt(3 - 1) + 1;
        if(race == 1) {
            return new Warrior(getRandomWarriorName(), getRandomNumberBetween(100, 200), getRandomNumberBetween(10, 50), getRandomNumberBetween(1, 10));  // Warrior
        }
        return new Wizard(getRandomWizardName(), getRandomNumberBetween(50, 100), getRandomNumberBetween(10, 50), getRandomNumberBetween(1, 50));  // Wizard

    }

    private void createRandomDuelists() {
        duelists.add(generateRandomCharacter());
        duelists.add(generateRandomCharacter());
    }

    private void createDuelistsFromCSV() {
        for (String line : charactersData) {
            String[] data = line.split(",");
            if (data[0].equals("Warrior")){
                duelists.add(new Warrior(data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4])));
            }else if(data[0].equals("Wizard")){
                duelists.add(new Wizard(data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4])));
            }
        }
        ImportCharacter.clean();
    }

    /*
    El método setup se encarga, cuando el jugador ha elegidos los métodos random o csv, de asignar a los duelistas
    sus stats según corresponda en cada caso.
    Por último, al terminar, llama al método beginBattle para iniciar el duelo.j
     */
    public void setup() {

        if (gameSetup.get("mode").equals("random")) createRandomDuelists();
        else if (gameSetup.get("mode").equals("csv")) createDuelistsFromCSV();
        duelistOne = duelists.get(0);
        duelistTwo = duelists.get(1);
        System.out.println("The clash between " + duelistOne.toString() + " vs " + duelistTwo.toString() + " is about to start!");

        if (isRestart) {
            if (duelistOne.race == Race.WIZARD) {
                Wizard wizard = (Wizard)duelistOne;
                wizard.setHp(wizard.getInitialState()[0]);
                wizard.setMana(wizard.getInitialState()[1]);
                duelistOne = wizard;
            }else if (duelistOne.race == Race.WARRIOR) {
                Warrior warrior = (Warrior)duelistOne;
                warrior.setHp(warrior.getInitialState()[0]);
                warrior.setStamina(warrior.getInitialState()[1]);
                duelistOne = warrior;
            }
            if (duelistTwo.race == Race.WIZARD) {
                Wizard wizard = (Wizard)duelistTwo;
                wizard.setHp(wizard.getInitialState()[0]);
                wizard.setMana(wizard.getInitialState()[1]);
                duelistTwo = wizard;
            }else if (duelistTwo.race == Race.WARRIOR) {
                Warrior warrior = (Warrior)duelistTwo;
                warrior.setHp(warrior.getInitialState()[0]);
                warrior.setStamina(warrior.getInitialState()[1]);
                duelistTwo = warrior;
            }
            beginBattle();
        }
    }

    /*
    El método init, como indica, incia el juego, la elección de Characters, su método de elección
    y en él mismo se definen las stats en el método manual.
    Al final llama al método setup para continuar en la elaboración del juego en los casos random y CSV.
     */

    public void init() {
        Character duelist;
        menu = new Menu();
        while(menu.isChoosingGameMode()){
            int playerMenuChoice = menu.letPlayerChooseGameMode();
            if (playerMenuChoice == 2) { //Random creation
                gameSetup.put("mode", "random");
                menu.setChoosingGameMode(false);
                setup();
            }else if (playerMenuChoice == 1) { //Manual creation
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
                    System.out.println("Created Character: " + duelist);
                }
                menu.setChoosingGameMode(false);
            }else if (playerMenuChoice == 3) {  //CSV Import
                gameSetup.put("mode", "csv");
                List<String> dataCSV;
                try {
                    dataCSV = ImportCharacter.getCharacterDataFromCSV();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                charactersData = menu.createCharactersFromCSV(dataCSV);
                menu.setChoosingGameMode(false);
                setup();
            }
        }
    }
    public void sleepFor(int millis) {
        try {
            sleep(millis);
        }catch (InterruptedException e) {
            System.out.println("hola");
        }
    }

    /*
    El método beginBattle utiliza un bucle While para determinar si continuá el juego a través de la propiedad
    isBattling y mientras sea true llanará a nextRound con el método sleepFor para el tiempo de cada ronda
     */
    public void beginBattle() {
        while (isBattling) {
            nextRound();
            sleepFor(2000);
        }
    }

    /*
    nextRound usa la propiedad isAlive que tiene cada duelist (Character) para determinar si ambos duelistas
    están vivos y así seguir la batalla llamando a attack que tiene cada Character de su interface attack.
    Llama al método setup en caso de empate para reiniciar y endMatch cuando uno de los dos duelistas ha ganado.
     */

    public void nextRound() {
        if (duelistOne.isAlive() && duelistTwo.isAlive()) {  //Los dos vivos
            ++currentRound;
            System.out.println("ROUND: " + currentRound);
            System.out.println(duelistOne.toString() + "                           " + duelistTwo.toString());
            duelistOne.attack(duelistTwo);
            duelistTwo.attack(duelistOne);
            System.out.println("========================================================================================================================================");
        }else if (!duelistOne.isAlive() && !duelistTwo.isAlive()) {  // Empate
            System.out.println(duelistOne.toString() + "                           " + duelistTwo.toString());
            System.out.println("Battle ended in a tie!");
            System.out.println("Restarting. . .");
            isRestart = true;
            currentRound = 0;
            System.out.println("Input any key to continue");
            menu.scanner.nextLine();
            setup();
        }
        else { // Uno vivo uno muerto
            String winner = duelistOne.isAlive() ? duelistOne.getName() : duelistTwo.getName();
            String loser =! duelistOne.isAlive() ? duelistOne.getName() : duelistTwo.getName();
            endMatch(winner, loser);
        }
    }

    /*
    El método endMatch imprime cuando se acaba el duelo: las estadísticas, ganador y perdedor.
    Despues, según el input del jugador, sale del juego o reincia la batalla llamando los métodos init, cleanUp, setup
     */

    public void endMatch(String winner, String loser) {
        System.out.println(duelistOne.toString() + "                           " + duelistTwo.toString());
        System.out.println("Battle ended!");
        System.out.println(winner + " has won the battle!");
        System.out.println("Press f to pay respects to " + loser);
        System.out.println("Do you want to restart the game?\n1- Yes\n2- No");
        int decision = menu.scanner.nextInt();

        if (decision == 2) System.exit(0);
        cleanUp();
        init();
        setup();
    }

    /*
    El método cleanUp se llama al finalizar la batalla y ser ésta reiniciada.
    Tomas las stats de cada Character y las reincia a sus valores inciales.
     */

    private void cleanUp() {
        isBattling = true;
        isRestart = false;
        currentRound = 0;
        gameSetup = new HashMap<>();
        duelistOne = null;
        duelistTwo = null;
        duelists = new ArrayList<>();
        menu = null;
    }
}
