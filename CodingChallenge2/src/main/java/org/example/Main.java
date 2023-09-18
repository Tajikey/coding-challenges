package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Aufgabe 2 - missing elements:");

        String[] fileList = getFileList(args);

        for (String fileName : fileList) {
            System.out.println(fileName);
            missingElementsChallenge(fileName);
        }
    }

    public static String[] getFileList(String[] args) {
        String[] fileList;
        if (args.length == 0) {
            System.out.println("Bitte geben Sie die Dateinamen als Argument ein.");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            fileList = input.split("\\s+");
            return fileList;
        } else {
            return args;
        }
    }

    public static void missingElementsChallenge(String fileName) {
        try {
            String jsonInput = MyFileReader.readFile(fileName);
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            String[][] input = gson.fromJson(jsonInput, String[][].class);

            //JsonArray jsonArray = JsonParser.parseString(jsonInput).getAsJsonArray();
            //String[][] input = new String[jsonArray.size()][];

            //for (int i = 0; i < jsonArray.size(); i++) {
            //    JsonArray innerArray = jsonArray.get(i).getAsJsonArray();
            //    input[i] = new String[innerArray.size()];
            //    for (int j = 0; j < innerArray.size(); j++) {
            //        input[i][j] = innerArray.get(j).getAsString();
            //    }
            //}

            String[] output = findMissingElements(input);

            System.out.println(Arrays.deepToString(input));
            System.out.println(gson.toJson(output));

        } catch (Exception e){
            System.err.println("Fehler beim Lesen oder Verarbeiten der Datei: " + e.getMessage());
        }
    }

    public static String[] findMissingElements(String[][] inputArray) {
        Map<String, Integer> elementCount = new HashMap<>();
        int numArrays = inputArray.length;

        for (String[] array : inputArray) {
            for (String element : array) {
                elementCount.merge(element, 1, Integer::sum);
                //elementCount.put(element, elementCount.getOrDefault(element, 0) + 1);
            }
        }

        List<String> missingElements = new ArrayList<>();

        for (String element : elementCount.keySet()) {
            if (elementCount.get(element) != numArrays && !missingElements.contains(element)) {
                missingElements.add(element);
            }
        }

        // sorts output alphabetic
        Collections.sort(missingElements);

        return missingElements.toArray(new String[0]);
    }
}