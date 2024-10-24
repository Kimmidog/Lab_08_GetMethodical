import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SafeInput {

    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String input;
        do {
            System.out.print(prompt + ": ");
            input = pipe.nextLine();
        } while (input.trim().length() == 0);

        return input;
    }

    public static int getInt(Scanner pipe, String prompt) {
        int result = 0;
        boolean valid = false;
        do {
            System.out.print(prompt + ": ");
            if (pipe.hasNextInt()) {
                result = pipe.nextInt();
                pipe.nextLine(); // clear buffer
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                pipe.nextLine(); // clear invalid input
            }
        } while (!valid);

        return result;
    }

    public static double getDouble(Scanner pipe, String prompt) {
        double result = 0.0;
        boolean valid = false;
        do {
            System.out.print(prompt + ": ");
            if (pipe.hasNextDouble()) {
                result = pipe.nextDouble();
                pipe.nextLine(); // clear buffer
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter a double.");
                pipe.nextLine(); // clear invalid input
            }
        } while (!valid);

        return result;
    }

    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int result = 0;
        boolean valid = false;
        do {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                result = pipe.nextInt();
                pipe.nextLine(); // clear buffer
                if (result >= low && result <= high) {
                    valid = true;
                } else {
                    System.out.println("Input out of range. Please enter an integer between " + low + " and " + high + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                pipe.nextLine(); // clear invalid input
            }
        } while (!valid);

        return result;
    }

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double result = 0.0;
        boolean valid = false;
        do {
            System.out.print(prompt + " [" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                result = pipe.nextDouble();
                pipe.nextLine(); // clear buffer
                if (result >= low && result <= high) {
                    valid = true;
                } else {
                    System.out.println("Input out of range. Please enter a double between " + low + " and " + high + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a double.");
                pipe.nextLine(); // clear invalid input
            }
        } while (!valid);

        return result;
    }

    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        String input;
        boolean valid = false;
        boolean result = false;
        do {
            System.out.print(prompt + " [Y/N]: ");
            input = pipe.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                result = true;
                valid = true;
            } else if (input.equalsIgnoreCase("N")) {
                valid = true;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        } while (!valid);

        return result;
    }

    public static String getRegExString(Scanner pipe, String prompt, String regEx) {
        String input;
        boolean valid = false;
        do {
            System.out.print(prompt + ": ");
            input = pipe.nextLine();
            Pattern pattern = Pattern.compile(regEx);
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                valid = true;
            } else {
                System.out.println("Invalid input. Please match the pattern: " + regEx);
            }
        } while (!valid);

        return input;
    }

    public static void prettyHeader(String msg) {
        final int WIDTH = 60;
        final int STAR_COUNT = 3;

        // Calculate padding for centering the message
        int padding = (WIDTH - msg.length() - (STAR_COUNT * 2)) / 2;

        // Print top row of asterisks
        for (int i = 0; i < WIDTH; i++) {
            System.out.print("*");
        }
        System.out.println();

        // Print middle row with the message
        System.out.print("***");
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        System.out.print(msg);
        for (int i = 0; i < padding; i++) {
            System.out.print(" ");
        }
        // Adjust if the message length + stars count is odd
        if ((msg.length() + (STAR_COUNT * 2)) % 2 != 0) {
            System.out.print(" ");
        }
        System.out.print("***");
        System.out.println();

        // Print bottom row of asterisks
        for (int i = 0; i < WIDTH; i++) {
            System.out.print("*");
        }
        System.out.println();
    }

    public static void testSafeInputMethods() {
        Scanner in = new Scanner(System.in);

        // Test getNonZeroLenString method
        String firstName = getNonZeroLenString(in, "Enter your first name");
        System.out.println("First Name: " + firstName);

        // Test getInt method
        int age = getInt(in, "Enter your age");
        System.out.println("Age: " + age);

        // Test getDouble method
        double salary = getDouble(in, "Enter your salary");
        System.out.println("Salary: " + salary);

        // Test getRangedInt method
        int rangedInt = getRangedInt(in, "Enter an integer between 1 and 10", 1, 10);
        System.out.println("Ranged Int: " + rangedInt);

        // Test getRangedDouble method
        double rangedDouble = getRangedDouble(in, "Enter a double between 0.5 and 5.5", 0.5, 5.5);
        System.out.println("Ranged Double: " + rangedDouble);

        // Test getYNConfirm method
        boolean ynConfirm = getYNConfirm(in, "Do you want to continue?");
        System.out.println("YN Confirm: " + (ynConfirm ? "Yes" : "No"));

        // Test getRegExString method
        String regExString = getRegExString(in, "Enter a string that matches the pattern [A-Za-z]+", "[A-Za-z]+");
        System.out.println("RegEx String: " + regExString);

        // GetUserName functionality
        String lastName = getNonZeroLenString(in, "Enter your last name");
        System.out.println("\nYour full name is: " + firstName + " " + lastName);

        // FavNumbers functionality
        int favoriteInt = getInt(in, "Enter your favorite integer");
        double favoriteDouble = getDouble(in, "Enter your favorite double");
        System.out.println("Your favorite numbers are: " + favoriteInt + " (integer) and " + favoriteDouble + " (double).");

        String ssn = getRegExString(in, "Enter your SSN (XXX-XX-XXXX)", "^\\d{3}-\\d{2}-\\d{4}$");
        System.out.println("SSN: " + ssn);

        String studentMNumber = getRegExString(in, "Enter your UC Student M Number", "^(M|m)\\d{5}$");
        System.out.println("UC Student M Number: " + studentMNumber);

        String menuChoice = getRegExString(in, "Enter your menu choice (Open, Save, View, Quit)", "^[OoSsVvQq]$");
        System.out.println("Menu Choice: " + menuChoice);
        prettyHeader("Message Centered Here");
    }
}
//i give up