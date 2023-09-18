package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Aufgabe 1 - array split:");

        String[] fileList = getFileList(args);

        for (String fileName : fileList) {
            System.out.println(fileName);
            arraySplitChallenge(fileName);
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

    public static void arraySplitChallenge(String fileName) {
        try {
            String jsonInput = MyFileReader.readFile(fileName);
            Gson gson = new Gson();
            String[] input = gson.fromJson(jsonInput, String[].class);

            // JsonArray jsonArray = JsonParser.parseString(jsonInput).getAsJsonArray();
            // String[] input = new String[jsonArray.size()];

            //for (int i = 0; i < jsonArray.size(); i++) {
            //    input[i] = jsonArray.get(i).getAsString();
            //}

            int chunkSize = 3;
            String[][] output = splitArray(input, chunkSize);

            System.out.println(Arrays.toString(input));
            System.out.println(Arrays.deepToString(output));

        } catch (Exception e) {
            System.err.println("Fehler beim Lesen oder Verarbeiten der Datei: " + e.getMessage());
        }
    }

    public static String[][] splitArray(String[] inputArray, int chunkSize) {
        int numRows = (int) Math.ceil((double) inputArray.length / chunkSize);
        String[][] outputArray = new String[numRows][];

        for (int i = 0; i < numRows; i++) {
            int startIndex = i * chunkSize;
            int endIndex = Math.min(startIndex + chunkSize, inputArray.length);
            outputArray[i] = Arrays.copyOfRange(inputArray, startIndex, endIndex);
        }

        return outputArray;
    }
}