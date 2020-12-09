import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiDetector__02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        List<String> emoji = new ArrayList<>();
        List<String> toPrint = new ArrayList<>();
        long currSumEmoji = 0;

        long calc = 0;
        long sum = 0;

        String numOnly = input.replaceAll("[^0-9]", "");
        String[] numTrans = numOnly.split("");

        for (int i = 0; i < numTrans.length; i++) {
            if (i < 1) {
                int currNumOne = Integer.parseInt(numTrans[i]);
                sum += currNumOne;
            } else {
                int currNumOne = Integer.parseInt(numTrans[i]);
                sum *= currNumOne;
            }

        }


        Pattern pattern = Pattern.compile("::[A-Z][a-z][a-z]+::|\\*\\*[A-Z][a-z][a-z]+\\*\\*");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            emoji.add(matcher.group());

        }


        for (int i = 0; i < emoji.size(); i++) {
            String first = emoji.get(i);
            for (int j = 2; j < first.length() - 2; j++) {
                char toChange = first.charAt(j);
                int value = (int) toChange;
                currSumEmoji += value;
                if (currSumEmoji > sum) {
                    toPrint.add(first);
                    currSumEmoji = 0;
                    break;
                }
            }

            if (currSumEmoji < sum) {
                currSumEmoji = 0;
            }


        }

        System.out.printf("Cool threshold: %d\n", sum);
        System.out.printf("%d emojis found in the text. The cool ones are:\n", emoji.size());
        for (int i = 0; i < toPrint.size(); i++) {
            System.out.println(toPrint.get(i));
        }


    }
}
