package Logic;

import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        World testWorld = new World(24, 8);
        //testWorld.showMap = true;

        OrganismFactory factory = new OrganismFactory();

        testWorld.spawn(factory.getOrg("owca", 1, 1, testWorld));
        testWorld.spawn(factory.getOrg("trawa", 12, 6, testWorld));
        testWorld.spawn(factory.getOrg("wilk", 1, 6, testWorld));
        testWorld.spawn(factory.getOrg("wilk", 12, 1, testWorld));
        testWorld.spawn(factory.getOrg("mlecz", 5, 8, testWorld));
        testWorld.spawn(factory.getOrg("muchomor", 8, 3, testWorld));
        testWorld.spawn(factory.getOrg("muchomor", 9, 8, testWorld));
        testWorld.spawn(factory.getOrg("owca", 16, 5, testWorld));
        testWorld.spawn(factory.getOrg("kosmita", 4, 3, testWorld));
        testWorld.nextTurn();

        Scanner userInput = new Scanner(System.in);
        String s;

        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Tura: " + testWorld.getTurn());
        System.out.println(testWorld);
        s = userInput.nextLine();
        int i = 100;
        while (s.length() == 0 && i > 0) {
            System.out.print("\033[H\033[2J");
            System.out.flush();
            System.out.println("Tura: " + (1 + testWorld.getTurn()));
            testWorld.nextTurn();
            System.out.println(testWorld);
            i--;
            s = userInput.nextLine();
        }
    }
}