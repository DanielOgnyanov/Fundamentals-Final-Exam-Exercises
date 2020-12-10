import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecretChat__01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String message = scanner.nextLine();
        StringBuilder keyToChange = new StringBuilder(message);
        String command = scanner.nextLine();

        while (!command.equals("Reveal")) {
            String[] operations = command.split(":\\|:");
            String nameOperations = operations[0];

            switch (nameOperations) {
                case "InsertSpace":
                    int indexToInsert = Integer.parseInt(operations[1]);
                    keyToChange.insert(indexToInsert, " ");
                    System.out.println(keyToChange);
                    break;

                case "Reverse":
                    String sub = operations[1];
                    if (keyToChange.toString().contains(sub)) {
                        String replace = keyToChange.toString().replaceFirst(Pattern.quote(sub), "");
                        keyToChange = new StringBuilder(replace);
                        StringBuilder reverse = new StringBuilder(sub).reverse();
                        keyToChange.append(reverse);
                        System.out.println(keyToChange);

                    } else {
                        System.out.println("error");
                    }
                    break;

                case "ChangeAll":
                    String subToChange = operations[1];
                    String replaceSub = operations[2];
                    String changes = keyToChange.toString().replaceAll(subToChange, replaceSub);
                    keyToChange = new StringBuilder(changes);
                    System.out.println(keyToChange);
                    break;
            }

            command = scanner.nextLine();
        }

        System.out.printf("You have a new text message: %s", keyToChange);
    }
}
