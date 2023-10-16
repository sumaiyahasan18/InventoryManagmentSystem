import java.util.Scanner;

public class ThreeLineInputs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter three lines:");

        System.out.print("Line 1: ");
        String line1 = scanner.nextLine();

        System.out.print("Line 2: ");
        String line2 = scanner.nextLine();

        System.out.print("Line 3: ");
        String line3 = scanner.nextLine();

        System.out.println("You entered the following lines:");
        System.out.println("Line 1: " + line1);
        System.out.println("Line 2: " + line2);
        System.out.println("Line 3: " + line3);

        scanner.close(); // Close the scanner when you're done
    }
}
