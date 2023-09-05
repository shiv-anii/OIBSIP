package oasis_infobyte;
import java.util.Scanner;
public class ATM_UserInput {

    private static Scanner scanner = new Scanner(System.in);

    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        int input = scanner.nextInt();
        scanner.nextLine(); 
        return input;
    }

    public static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        return input;
    }
}
