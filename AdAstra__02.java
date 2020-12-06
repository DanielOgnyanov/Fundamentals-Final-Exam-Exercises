import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdAstra__02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String regex =
                "([#|])(?<nameProduct>[A-Z][A-Za-z ]+)\\1(?<date>[0-9]{2}([\\\\/])[0-9]{2}\\4[0-9]{2})([#|])(?<calories>[0-9]+)\\5";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> product = new ArrayList<>();
        List<String> data = new ArrayList<>();
        List<String> calories = new ArrayList<>();
        int sum = 0;
        while (matcher.find()) {
            int numCalories = Integer.parseInt(matcher.group("calories"));
            if (numCalories > 10000 || numCalories < 0) {
                continue;
            }
            sum += numCalories;
            product.add(matcher.group("nameProduct"));
            data.add(matcher.group("date"));
            calories.add(matcher.group("calories"));
        }
        int daysCalc = sum / 2000;

        System.out.printf("You have food to last you for: %d days!\n", daysCalc);
        for (int i = 0; i < product.size(); i++) {
            System.out.printf("Item: %s, Best before: %s, Nutrition: %s\n", product.get(i),
                    data.get(i), calories.get(i));
        }
    }
}
