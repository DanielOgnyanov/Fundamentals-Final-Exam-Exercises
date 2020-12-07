import java.util.*;

public class ThePianist__03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberOfSong = Integer.parseInt(scanner.nextLine());
        String command = scanner.nextLine();
        Map<String, String> pieceComposer = new TreeMap<>();
        Map<String, String> pieceKey = new TreeMap<>();

        for (int i = 0; i < numberOfSong; i++) {
            String[] operations = command.split("\\|");
            String piece = operations[0];
            String composer = operations[1];
            String key = operations[2];
            pieceComposer.put(piece, composer);
            pieceKey.put(piece, key);   
            command = scanner.nextLine();
        }

        while (!command.equals("Stop")) {
            String[] nameCommand = command.split("\\|");
            String name = nameCommand[0];

            switch (name) {


                case "Add":
                    String piece = nameCommand[1];
                    String composer = nameCommand[2];
                    String key = nameCommand[3];
                    if (pieceComposer.toString().contains(piece)) {
                        System.out.printf("%s is already in the collection!\n", piece);
                    } else {
                        pieceComposer.put(piece, composer);
                        pieceKey.put(piece, key);
                        System.out.printf("%s by %s in %s added to the collection!\n", piece, composer, key);
                    }

                    break;

                case "Remove":
                    piece = nameCommand[1];
                    if (pieceComposer.toString().contains(piece)) {
                        pieceComposer.remove(piece);
                        pieceKey.remove(piece);
                        System.out.printf("Successfully removed %s!\n", piece);
                    } else {
                        System.out.printf("Invalid operation! %s does not exist in the collection.\n", piece);
                    }
                    break;

                case "ChangeKey":
                    piece = nameCommand[1];
                    String keyToChange = nameCommand[2];

                    if (pieceComposer.toString().contains(piece)) {
                        pieceKey.put(piece, keyToChange);
                        System.out.printf("Changed the key of %s to %s!\n", piece, keyToChange);
                    } else {
                        System.out.printf("Invalid operation! %s does not exist in the collection.\n", piece);
                    }

                    break;
            }

            command = scanner.nextLine();
        }

        List<String> keyToPrint = new ArrayList<>(pieceKey.values());
        int i = 0;

        for (Map.Entry<String, String> print : pieceComposer.entrySet()) {

            System.out.printf("%s -> Composer: %s, Key: %s\n", print.getKey(), print.getValue(), keyToPrint.get(i));
            i++;
        }


    }
}
