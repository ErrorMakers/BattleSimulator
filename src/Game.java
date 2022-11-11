import java.util.Random;

public class Game {
    private int currentRound = 0;
    private static Random rand = new Random();
    private Character duelistOne;
    private Character duelistTwo;
    private static String log;
    private static String[] names = {"Dumbledore", "Snape", "Thanos el rompe anos", "Thor", "Shrek", "Juan Soto", "Jeff Bezos", "Justiniano", "Snow"};


    private static int getRandomNumberBetween(int min, int max) {
        return rand.nextInt(max - min) + min;
    }
    private static String getRandomName () {
        return names[rand.nextInt(names.length)];
    }



    public void nextRound() {
        //TODO
    }
}
