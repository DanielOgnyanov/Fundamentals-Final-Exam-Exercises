import java.util.Scanner;

public class PasswordReset__01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String raw = scanner.nextLine();
        StringBuilder key = new StringBuilder(raw);
        int count = 0;
        String command = scanner.nextLine();

        while (!command.equals("Done")) {
            String[] split = command.split(" ");
            String operations = split[0];

            switch (operations) {

                case "TakeOdd":
                    StringBuilder refreshDate = new StringBuilder(key);
                    for (int i = 0; i < refreshDate.length(); i++) {
                        char curr = refreshDate.charAt(i);
                        if (i % 2 == 1) {
                            if (count == 0) {
                                key.setLength(0);
                                count++;
                            }
                            key.append(curr);
                        }
                    }
                    System.out.println(key);

                    break;

                case "Cut":
                    int start = Integer.parseInt(split[1]);
                    int len = Integer.parseInt(split[2]);
                    int end = start + len;
                    key.replace(start, end, "");
                    System.out.println(key);
                    break;

                case "Substitute":
                    String subToCheck = split[1];
                    String toChange = split[2];
                    if (!key.toString().contains(subToCheck)) {
                        System.out.println("Nothing to replace!");

                    } else if (key.toString().contains(subToCheck)) {
                        String replace = key.toString().replaceAll(subToCheck, toChange);
                        key.setLength(0);
                        for (int i = 0; i < replace.length(); i++) {
                            char curr = replace.charAt(i);
                            key.append(curr);
                        }
                        System.out.println(key);
                    }
                    break;
            }
            command = scanner.nextLine();
        }
        System.out.printf("Your password is: %s\n", key);
    }
}
