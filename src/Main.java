import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Ultimate Battle IronBattle Simulator!");
        Game game = new Game();
        game.init();

        System.out.println("Character initialization finished, enter any input to continue to setup");
        scanner.nextLine();
        game.setup();

    }
}