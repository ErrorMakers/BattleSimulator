import java.util.Scanner;

public class Menu {

    public static Scanner scanner = new Scanner(System.in);




    public static int letPlayerChooseGameMode() {
        System.out.println("Choose a game mode... \n1-Create your Characters\n2-Create Random Characters");
        switch (scanner.nextInt()) {
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return 0;
        }
    }
    public static int[] createCharacterByInput() {
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
        return null;
    }

    //Warrior o Wizard / Name / Hp (100,200 o 50,100) / Stamina - Intelligence - Mana - Strength
    public static void menu() {

        int option;
        Scanner scanner = new Scanner(System.in);

    }
}

