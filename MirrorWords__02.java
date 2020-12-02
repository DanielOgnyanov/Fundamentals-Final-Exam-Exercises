import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MirrorWords__02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String regex = "@([a-zA-Z]+){3,}@@([a-zA-Z]+){3,}@|#([a-zA-Z]+){3,}##([a-zA-Z]+){3,}#";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        List<String> words = new ArrayList<>();
        List<String> mirrors = new ArrayList<>();
        while (matcher.find()) {
            words.add(matcher.group());
        }
        for (int i = 0; i < words.size(); i++) {
            List<String> splitWords = new ArrayList<>();
            Pattern pattern1 = Pattern.compile("[a-zA-Z]+");
            Matcher matcher1 = pattern1.matcher(words.get(i));
            while (matcher1.find()) {
                splitWords.add(matcher1.group());
            }
            String firstWord = splitWords.get(0);
            String secondWord = splitWords.get(1);
            StringBuilder reverse = new StringBuilder(secondWord).reverse();
            String reverseSecondWord = reverse.toString();
            if (firstWord.equals(reverseSecondWord)) {
                mirrors.add(String.format("%s <=> %s", firstWord, secondWord));
            }

        }
        if (words.size() != 0) {
            System.out.printf("%d word pairs found!\n", words.size());
        } else {
            System.out.println("No word pairs found!");
            System.out.println("No mirror words!");
            return;
        }
        if (mirrors.size() != 0) {
            System.out.println("The mirror words are:");
            for (int i = 0; i < mirrors.size(); i++) {
                if (mirrors.size() - 1 == i) {
                    System.out.print(mirrors.get(i));
                    break;
                }
                System.out.print(mirrors.get(i) + ", ");
            }
        } else {
            System.out.println("No mirror words!");
        }
    }
}
