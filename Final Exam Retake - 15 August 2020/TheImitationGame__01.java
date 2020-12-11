import java.util.Scanner;
import java.util.regex.Pattern;

public class TheImitationGame__01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        StringBuilder key = new StringBuilder(input);
        String command = scanner.nextLine();


        while (!command.equals("Decode")) {
            String[] operations = command.split("\\|");
            String nameCommand = operations[0];


            switch (nameCommand) {
                case "Move":
                    int numberOfLetter = Integer.parseInt(operations[1]);
                    String toMove = key.toString().substring(0, numberOfLetter);
                    key.append(toMove);
                    String remove = key.toString().replaceFirst(Pattern.quote(toMove), "");
                    key = new StringBuilder(remove);
                    break;

                case "Insert":
                    int indexToInsert = Integer.parseInt(operations[1]);
                    String toInsert = operations[2];
                    key.insert(indexToInsert, toInsert);
                    break;

                case "ChangeAll":
                    String sub = operations[1];
                    String newSub = operations[2];

                    String replace = key.toString().replaceAll(Pattern.quote(sub), newSub);
                    key = new StringBuilder(replace);
                    break;
            }
            command = scanner.nextLine();
        }

        System.out.printf("The decrypted message is: %s", key);
    }
}
