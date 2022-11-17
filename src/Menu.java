import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {

    public Scanner scanner = new Scanner(System.in);
    private boolean isChoosingGameMode = true;


    public int letPlayerChooseGameMode() {
        return inputValidation("Choose a game mode... \n1-Create your Characters\n2-Create Random Characters\n3-Import from CSV", scanner);
    }

    public String createName() {
        scanner.nextLine();
        System.out.println("Insert Character name: ");
        String name = scanner.nextLine();
        return name;
    }

    public int[] createCharacterByInput() {
        int characterChoice = 0;
            characterChoice = inputValidation("Choose between Warrior or Wizard...\n1-Warrior\n2-Wizard", scanner);
            if (characterChoice == 1) {
                int hp = inputValidation("Set your hp: ", scanner);
                int strength = inputValidation("Set your strength", scanner);
                int stamina = inputValidation("Set your stamina: ", scanner);
                return new int[]{characterChoice, hp, stamina, strength};
            } else if (characterChoice == 2) {
                int hp = inputValidation("Set your hp: ", scanner);
                int intelligence = inputValidation("Set your intelligence: ", scanner);
                int mana = inputValidation("Set your mana: ", scanner);
                return new int[]{characterChoice, hp, mana, intelligence};
            } else {
                System.out.println("Please, enter a valid character");

            }
        return null;
        }



    public String[] createCharactersFromCSV(List<String> data) {
        String[] attributes;
        int counter = 1;
        for (String line : data) {
            attributes = line.split(",");
            if (attributes[0].equals("Warrior")) {
                System.out.println(counter + "- Type: " + attributes[0] + " | Name: " + attributes[1] + " | HP: " + attributes[2] + " | STA: " + attributes[3] + " | STR: " + attributes[4]);
                counter++;
            }else {
                System.out.println(counter + "- Type: " + attributes[0] + " | Name: " + attributes[1] + " | HP: " + attributes[2] + " | MANA: " + attributes[3] + " | INT: " + attributes[4]);
                counter++;
            }
        }
        System.out.println("Type, separated by spaces, the two numbers from the list you wish to import to the game...");
        scanner.nextLine();
        String choices = scanner.nextLine();
        String[] selectedCharactersData = new String[]{data.get(Integer.parseInt(choices.split(" ")[0]) - 1), data.get(Integer.parseInt(choices.split(" ")[1]) - 1)};
        return selectedCharactersData;
    }

    public boolean isChoosingGameMode() {
        return isChoosingGameMode;
    }

    public void setChoosingGameMode(boolean isChoosingGameMode) {
        this.isChoosingGameMode = isChoosingGameMode;
    }

    public int inputValidation(String message, Scanner scanner){
        System.out.println(message);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("You didn't enter a Integer");
            scanner.next();
            return inputValidation(message, scanner);
        }
    }

}

