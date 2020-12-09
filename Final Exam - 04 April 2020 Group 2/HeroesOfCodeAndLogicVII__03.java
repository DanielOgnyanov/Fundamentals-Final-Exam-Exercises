import java.util.*;

public class HeroesOfCodeAndLogicVII__03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numOfHeroes = Integer.parseInt(scanner.nextLine());
        Map<String, Integer> heroes = new TreeMap<>();
        List<Integer> manaPints = new ArrayList<>();
        String command = scanner.nextLine();
        List<String> keyIndex = new ArrayList<>();

        for (int i = 0; i < numOfHeroes; i++) {
            String[] operationsToAdd = command.split("\\s+");
            String name = operationsToAdd[0];
            int hp = Integer.parseInt(operationsToAdd[1]);
            int mp = Integer.parseInt(operationsToAdd[2]);
            heroes.put(name, hp);
            keyIndex.add(name);
            manaPints.add(keyIndex.indexOf(name), mp);
            command = scanner.nextLine();
        }

        while (!command.equals("End")) {
            String[] commandToDo = command.split(" - ");
            String operation = commandToDo[0];

            switch (operation) {

                case "CastSpell":
                    String nameHeroes = commandToDo[1];
                    int mpNeed = Integer.parseInt(commandToDo[2]);
                    String castSpellName = commandToDo[3];

                    if (manaPints.get(keyIndex.indexOf(nameHeroes)) >= mpNeed) {
                        manaPints.set(keyIndex.indexOf(nameHeroes), manaPints.get(keyIndex.indexOf(nameHeroes)) - mpNeed);
                        System.out.printf("%s has successfully cast %s and now has %d MP!\n",
                                nameHeroes, castSpellName, manaPints.get(keyIndex.indexOf(nameHeroes)));
                    } else {
                        System.out.printf("%s does not have enough MP to cast %s!\n", nameHeroes, castSpellName);
                    }

                    break;

                case "TakeDamage":
                    nameHeroes = commandToDo[1];
                    int damage = Integer.parseInt(commandToDo[2]);
                    String nameAttacker = commandToDo[3];

                    if (heroes.get(nameHeroes) > damage) {
                        heroes.put(nameHeroes, heroes.get(nameHeroes) - damage);
                        if (heroes.get(nameHeroes) > 0) {
                            System.out.printf("%s was hit for %d HP by %s and now has %d HP left!\n", nameHeroes,
                                    damage, nameAttacker, heroes.get(nameHeroes));
                            break;
                        } else {
                            heroes.remove(nameHeroes);
                            keyIndex.remove(nameHeroes);
                            manaPints.remove(keyIndex.indexOf(nameHeroes));
                            System.out.printf("%s has been killed by %s!\n", nameHeroes, nameAttacker);
                            break;
                        }
                    }

                    if (heroes.get(nameHeroes) <= damage) {
                        heroes.remove(nameHeroes);
                        manaPints.remove(keyIndex.indexOf(nameHeroes));
                        keyIndex.remove(nameHeroes);
                        System.out.printf("%s has been killed by %s!\n", nameHeroes, nameAttacker);
                    }
                    break;

                case "Recharge":
                    nameHeroes = commandToDo[1];
                    int mpToAdd = Integer.parseInt(commandToDo[2]);
                    int diff = 200 - manaPints.get(keyIndex.indexOf(nameHeroes));


                    if (mpToAdd >= 200) {
                        manaPints.set(keyIndex.indexOf(nameHeroes), 200);
                        System.out.printf("%s recharged for %d MP!\n", nameHeroes, diff);
                    } else {
                        manaPints.set(keyIndex.indexOf(nameHeroes), manaPints.get(keyIndex.indexOf(nameHeroes)) + mpToAdd);
                        if (manaPints.get(keyIndex.indexOf(nameHeroes)) >= 200) {
                            manaPints.set(keyIndex.indexOf(nameHeroes), 200);
                            System.out.printf("%s recharged for %d MP!\n", nameHeroes, diff);
                        } else {
                            System.out.printf("%s recharged for %d MP!\n", nameHeroes, mpToAdd);
                        }

                    }

                    break;
                case "Heal":
                    nameHeroes = commandToDo[1];
                    int hpToAdd = Integer.parseInt(commandToDo[2]);
                    diff = 100 - heroes.get(nameHeroes);
                    if (hpToAdd >= 100) {
                        heroes.put(nameHeroes, 100);
                        System.out.printf("%s healed for %d HP!\n", nameHeroes, diff);
                    } else {
                        heroes.put(nameHeroes, heroes.get(nameHeroes) + hpToAdd);
                        if (heroes.get(nameHeroes) >= 100) {
                            heroes.put(nameHeroes, 100);
                            System.out.printf("%s healed for %d HP!\n", nameHeroes, diff);
                        } else {
                            System.out.printf("%s healed for %d HP!\n", nameHeroes, hpToAdd);
                        }

                    }
                    break;
            }
            command = scanner.nextLine();
        }

        Map<String, Integer> reverse = new LinkedHashMap<>();

        heroes.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverse.put(x.getKey(), x.getValue()));

        for (Map.Entry<String, Integer> toPrint : reverse.entrySet()) {
            System.out.printf("%s\n", toPrint.getKey());
            System.out.printf("  HP: %d\n", toPrint.getValue());
            System.out.printf("  MP: %d\n", manaPints.get(keyIndex.indexOf(toPrint.getKey())));
        }

    }
}
