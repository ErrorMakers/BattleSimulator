import java.util.Scanner;

public class Menu {

    public Scanner scanner = new Scanner(System.in);
    private boolean isChoosingGameMode = true;


    public int letPlayerChooseGameMode() {
        System.out.println("Choose a game mode... \n1-Create your Characters\n2-Create Random Characters");
        //TODO: Check for correct input values
        return scanner.nextInt();
    }

    public String createName() {
        scanner.nextLine();
        System.out.println("Insert Character name: ");
        String name = scanner.nextLine();
        return name;
    }

    public int[] createCharacterByInput() {
        System.out.println("Choose between Warrior or Wizard...\n1-Warrior\n2-Wizard");
        int characterChoice = scanner.nextInt();

        if (characterChoice == 1) {
            System.out.println("Set your hp: ");
            int hp = scanner.nextInt();
            System.out.println("Set your strength: ");
            int strength = scanner.nextInt();
            System.out.println("Set your stamina: ");
            int stamina = scanner.nextInt();
            return new int[]{characterChoice, hp, stamina, strength};
        }
        else if (characterChoice == 2) {
            System.out.println("Set your hp: ");
            int hp = scanner.nextInt();
            System.out.println("Set your intelligence: ");
            int intelligence = scanner.nextInt();
            System.out.println("Set your mana: ");
            int mana = scanner.nextInt();
            return new int[]{characterChoice, hp, mana, intelligence};
        }
        //TODO: check for valid inputs
        return null;
    }

    public boolean isChoosingGameMode() {
        return isChoosingGameMode;
    }

    public void setChoosingGameMode(boolean isChoosingGameMode) {
        this.isChoosingGameMode = isChoosingGameMode;
    }
}

