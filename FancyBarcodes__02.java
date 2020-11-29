import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FancyBarcodes__02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        String regex = "@#+([A-Z][A-Za-z0-9]{4,}[A-Z])@#+";

        for (int i = 1; i <= count; i++) {
            String barcode = scanner.nextLine();
            String currBarcode = "";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(barcode);
            while (matcher.find()) {
                currBarcode = matcher.group();
            }
            if (!currBarcode.isEmpty()) {
                String product = currBarcode.replaceAll("[^0-9]", "");
                if (product.isEmpty()) {
                    System.out.println("Product group: 00");
                } else {
                    System.out.printf("Product group: %s\n", product);

                }
            }
            if (currBarcode.isEmpty()) {
                System.out.println("Invalid barcode");
            }
        }
    }
}
