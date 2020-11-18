import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActivationKeys__01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String rawKey = scanner.nextLine();

        StringBuilder result = new StringBuilder(rawKey);

        String toChange = null;

        String command = scanner.nextLine();


        while (!command.equals("Generate")) {
            String[] operations = command.split(">>>");
            String commandName = operations[0];


            switch (commandName) {

                case "Contains":

                    String subString = operations[1];

                    if (result.toString().contains(subString)) {
                        System.out.printf("%s contains %s\n", result, subString);
                    } else {
                        System.out.println("Substring not found!");
                    }
                    break;


                case "Flip":
                    int startIndex = Integer.parseInt(operations[2]);
                    int endIndex = Integer.parseInt(operations[3]);
                    if (operations[1].equals("Upper")) {
                        toChange = result.substring(startIndex, endIndex).toUpperCase();
                        result.replace(startIndex, endIndex, toChange);
                        System.out.println(result);
                        toChange = null;

                    }
                    if (operations[1].equals("Lower")) {
                        toChange = result.substring(startIndex, endIndex).toLowerCase();
                        result.replace(startIndex, endIndex, toChange);
                        System.out.println(result);
                        toChange = null;
                    }

                    break;

                case "Slice":

                    int startRemoveIndex = Integer.parseInt(operations[1]);
                    int endRemoveIndex = Integer.parseInt(operations[2]);

                    result.delete(startRemoveIndex, endRemoveIndex);

                    System.out.println(result);
                    break;

            }


            command = scanner.nextLine();
        }

        System.out.printf("Your activation key is: %s", result);
    }
}
