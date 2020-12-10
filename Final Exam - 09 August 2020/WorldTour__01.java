import java.util.Scanner;

public class WorldTour__01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        StringBuilder countries = new StringBuilder(input);
        String command = scanner.nextLine();


        while (!command.equals("Travel")) {
            String[] operations = command.split(":");
            String nameCommand = operations[0];

            switch (nameCommand) {

                case "Add Stop":
                    int indexToInsert = Integer.parseInt(operations[1]);
                    String toInsert = operations[2];

                    if (indexToInsert < countries.length() && indexToInsert >= 0) {
                        countries.insert(indexToInsert, toInsert);
                        System.out.println(countries);
                    } else {
                        System.out.println(countries);
                    }
                    break;

                case "Remove Stop":
                    int start = Integer.parseInt(operations[1]);
                    int end = Integer.parseInt(operations[2]);

                    if (start < countries.length() && start >= 0 && end < countries.length() && end >= 0) {
                        countries.replace(start, end + 1, "");
                        System.out.println(countries);
                    } else {
                        System.out.println(countries);
                    }

                    break;

                case "Switch":
                    String toCheck = operations[1];
                    String toChange = operations[2];

                    if (countries.toString().contains(toCheck)) {
                        String replaced = countries.toString().replaceAll(toCheck, toChange);
                        countries = new StringBuilder(replaced);
                        System.out.println(countries);
                    } else {
                        System.out.println(countries);
                    }
                    break;


            }
            command = scanner.nextLine();


        }
        System.out.printf("Ready for world tour! Planned stops: %s", countries);
    }
}
