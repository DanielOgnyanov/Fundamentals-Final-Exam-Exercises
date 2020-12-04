import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DestinationMapper__02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String regex = "(([=\\/])(?<first>([A-Z][A-Za-z]{2,}))\\2)"; //Check
        List<String> location = new ArrayList<>();
        List<String> toPrint = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        int sumTravelPoints = 0;
        while (matcher.find()) {
            location.add(matcher.group("first"));
        }

        for (int i = 0; i < location.size(); i++) {
            sumTravelPoints += location.get(i).length();

        }
        System.out.print("Destinations: ");
        for (int i = 0; i < location.size(); i++) {
            if (location.size() - 1 == i) {
                System.out.print(location.get(i));
                break;
            }
            System.out.print(location.get(i) + ", ");
        }
        System.out.println();
        System.out.printf("Travel Points: %d", sumTravelPoints);
    }
}
