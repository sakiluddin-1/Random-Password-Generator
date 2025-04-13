import java.security.SecureRandom;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Password Generator!");

        
        int length = getUserInput("Enter the length of the password: ");
        boolean useUppercase = getYesNoInput("Include uppercase letters? (Y/N): ");
        boolean useNumbers = getYesNoInput("Include numbers? (Y/N): ");
        boolean useSpecialCharacters = getYesNoInput("Include special characters? (Y/N): ");

        
        String generatedPassword = generatePassword(length, useUppercase, useNumbers, useSpecialCharacters);
        System.out.println("\nGenerated Password: " + generatedPassword);

        
    }

    private static int getUserInput(String prompt) {
        Scanner sc = new Scanner(System.in);
        int userInput;

        while (true) {
            try {
                System.out.print(prompt);
                userInput = Integer.parseInt(sc.nextLine());
                if (userInput > 0) {
                    break;
                } else {
                    System.out.println("Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return userInput;
    }

    private static boolean getYesNoInput(String prompt) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(prompt);
            String userInput = sc.nextLine().trim().toLowerCase();

            if (userInput.equals("y") || userInput.equals("yes")) {
                return true;
            } else if (userInput.equals("n") || userInput.equals("no")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'Y' or 'N'.");
            }
        }
    }

    private static String generatePassword(int length, boolean useUppercase, boolean useNumbers, boolean useSpecialCharacters) {
        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numberChars = "0123456789";
        String specialChars = "!@#$%^&*()-_=+[{]};:'\",<.>/?";

        StringBuilder validChars = new StringBuilder(lowercaseChars);

        if (useUppercase) {
            validChars.append(uppercaseChars);
        }

        if (useNumbers) {
            validChars.append(numberChars);
        }

        if (useSpecialCharacters) {
            validChars.append(specialChars);
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validChars.length());
            password.append(validChars.charAt(randomIndex));
        }

        return password.toString();
    }
}