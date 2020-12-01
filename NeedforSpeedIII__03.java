import java.util.*;

public class NeedforSpeedIII__03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numOfCars = Integer.parseInt(scanner.nextLine());
        String command = scanner.nextLine();
        Map<String, Integer> carsMap = new TreeMap<>();
        List<Integer> fuelList = new ArrayList<>();
        List<String> keyIndex = new ArrayList<>();

        for (int i = 0; i < numOfCars; i++) {
            String[] operations = command.split("\\|");
            String nameCar = operations[0];
            int mileage = Integer.parseInt(operations[1]);
            int fuel = Integer.parseInt(operations[2]);
            carsMap.put(nameCar, mileage);
            keyIndex.add(nameCar);
            fuelList.add(keyIndex.indexOf(nameCar), fuel);
            command = scanner.nextLine();

        }

        while (!command.equals("Stop")) {
            String[] differentCommand = command.split(" : ");
            String nameCommand = differentCommand[0];

            switch (nameCommand) {

                case "Drive":
                    String carNameRefresh = differentCommand[1];
                    int currDistance = Integer.parseInt(differentCommand[2]);
                    int currFuel = Integer.parseInt(differentCommand[3]);

                    if (currFuel <= fuelList.get(keyIndex.indexOf(carNameRefresh))) {

                        carsMap.put(carNameRefresh, carsMap.get(carNameRefresh) + currDistance);
                        fuelList.set(keyIndex.indexOf(carNameRefresh),
                                fuelList.get(keyIndex.indexOf(carNameRefresh)) - currFuel);
                        System.out.printf("%s driven for %d kilometers. %d liters of fuel consumed.\n", carNameRefresh,
                                currDistance, currFuel);

                        if (carsMap.get(carNameRefresh) >= 100000) {

                            carsMap.remove(carNameRefresh);
                            fuelList.remove(keyIndex.indexOf(carNameRefresh));
                            keyIndex.remove(carNameRefresh);
                            System.out.printf("Time to sell the %s!\n", carNameRefresh);
                        }
                    } else {
                        System.out.println("Not enough fuel to make that ride");
                    }
                    break;

                case "Refuel":
                    carNameRefresh = differentCommand[1];
                    currFuel = Integer.parseInt(differentCommand[2]);
                    int sum = currFuel + fuelList.get(keyIndex.indexOf(carNameRefresh));
                    int toSave = fuelList.get(keyIndex.indexOf(carNameRefresh));

                    if (sum > 75) {
                        fuelList.set(keyIndex.indexOf(carNameRefresh), 75);
                        int diff = 75 - toSave;
                        System.out.printf("%s refueled with %d liters\n", carNameRefresh, diff);
                    } else {
                        fuelList.set(keyIndex.indexOf(carNameRefresh), sum);
                        if (fuelList.get(keyIndex.indexOf(carNameRefresh)) > 75) {
                            fuelList.set(keyIndex.indexOf(carNameRefresh), 75);
                            int diff = 75 - toSave;
                            System.out.printf("%s refueled with %d liters\n", carNameRefresh, diff);
                        }

                        System.out.printf("%s refueled with %d liters\n", carNameRefresh, currFuel);
                    }

                    break;
                case "Revert":
                    carNameRefresh = differentCommand[1];
                    int currKm = Integer.parseInt(differentCommand[2]);
                    carsMap.put(carNameRefresh, carsMap.get(carNameRefresh) - currKm);

                    if (carsMap.get(carNameRefresh) <= 10000) {
                        carsMap.put(carNameRefresh, 10000);
                    } else {
                        System.out.printf("%s mileage decreased by %d kilometers\n", carNameRefresh, currKm);
                    }
                    break;
            }
            command = scanner.nextLine();
        }

        Map<String, Integer> reverse = new LinkedHashMap<>();
        carsMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverse.put(x.getKey(), x.getValue()));

        for (Map.Entry<String, Integer> toPrint : reverse.entrySet()) {
            System.out.printf("%s -> Mileage: %d kms, Fuel in the tank: %d lt.\n", toPrint.getKey(),
                    toPrint.getValue(), fuelList.get(keyIndex.indexOf(toPrint.getKey())));
        }
    }
}
