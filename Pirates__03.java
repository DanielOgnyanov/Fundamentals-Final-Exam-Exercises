import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Pirates__03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        Map<String, Integer> cities = new TreeMap<>();
        List<Integer> people = new ArrayList<>();
        List<String> keyIndex = new ArrayList<>();

        while (!command.equals("Sail")) {
            String[] operationsToAdd = command.split("\\|+");
            String cityName = operationsToAdd[0];
            int population = Integer.parseInt(operationsToAdd[1]);
            int gold = Integer.parseInt(operationsToAdd[2]);

            if (cities.containsKey(cityName)) {
                people.set(keyIndex.indexOf(cityName), people.get(keyIndex.indexOf(cityName)) + population);
                cities.put(cityName, cities.get(cityName) + gold);

            } else {
                people.add(population);
                keyIndex.add(cityName);
                cities.put(cityName, gold);
            }

            command = scanner.nextLine();
        }

        while (!command.equals("End")) {
            String[] operations = command.split("=>+");
            String event = operations[0];
            switch (event) {
                case "Plunder":
                    String town = operations[1];
                    int killed = Integer.parseInt(operations[2]);
                    int goldSteal = Integer.parseInt(operations[3]);

                    if (people.get(keyIndex.indexOf(town)) > 0 || cities.get(town) > 0) {
                        cities.put(town, cities.get(town) - goldSteal);
                        people.set(keyIndex.indexOf(town), people.get(keyIndex.indexOf(town)) - killed);
                        System.out.printf("%s plundered! %d gold stolen, %d citizens killed.\n", town, goldSteal, killed);
                        if (people.get(keyIndex.indexOf(town)) <= 0
                                || cities.get(town) <= 0) {


                            System.out.printf("%s has been wiped off the map!\n", town);
                            people.remove(keyIndex.indexOf(town));
                            keyIndex.remove(town);
                            cities.remove(town);
                            break;
                        }

                    }
                    break;

                case "Prosper":
                    town = operations[1];
                    int addGold = Integer.parseInt(operations[2]);

                    if (addGold < 0) {
                        System.out.println("Gold added cannot be a negative number!");
                        break;
                    } else if (addGold >= 0 && cities.containsKey(town)) {
                        cities.put(town, cities.get(town) + addGold);
                        System.out.printf("%d gold added to the city treasury. %s now has %d gold.\n",
                                addGold, town, cities.get(town));
                    }

                    break;
            }
            command = scanner.nextLine();
        }

        if (!cities.isEmpty()) {
            System.out.printf("Ahoy, Captain! There are %d wealthy settlements to go to:\n", cities.size());

            Map<String, Integer> reverse = new LinkedHashMap<>();

            cities.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> reverse.put(x.getKey(), x.getValue()));


            for (Map.Entry<String, Integer> toPrint : reverse.entrySet()) {
                System.out.printf("%s -> Population: %s citizens, Gold: %s kg\n", toPrint.getKey(),
                        people.get(keyIndex.indexOf(toPrint.getKey())), toPrint.getValue());
            }
        } else {
            System.out.println("Ahoy, Captain! All targets have been plundered and destroyed!");

        }

    }
}
