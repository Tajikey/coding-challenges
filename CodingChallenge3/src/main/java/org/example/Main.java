package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        System.out.println("Aufgabe 3 - sorting objects:");

        String[] fileList = getFileList(args);

        for (String fileName : fileList) {
            System.out.println(fileName);
            sortingObjectsChallenge(fileName);
        }
    }

    private static String[] getFileList(String[] args) {
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

    private static void sortingObjectsChallenge(String fileName) {
        try {
            String jsonInput = MyFileReader.readFile(fileName);
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();

            Order[] orders = gson.fromJson(jsonInput, Order[].class);

            List<Order> sortedOrders = Arrays.stream(orders)
                    .sorted(Comparator.comparing(Order::customerNumber)
                        .thenComparing(order -> order.invoice().number(), Comparator.reverseOrder()))
                    .toList();

            //List<JsonObject> invoices = new ArrayList<>();
            //JsonArray jsonArray = gson.fromJson(jsonInput, JsonArray.class);
            //for (int i = 0; i < jsonArray.size(); i++) {
            //    invoices.add(jsonArray.get(i).getAsJsonObject());
            //}

            //invoices.sort(Comparator.comparing((JsonObject obj) -> obj.get("customer_number").getAsString())
            //        .thenComparing(obj -> obj.getAsJsonObject("invoice").get("number").getAsInt(), Comparator.reverseOrder()));

            System.out.println(jsonInput);
            System.out.println("===== Sorted =======");
            System.out.println(gson.toJson(sortedOrders));

        } catch (Exception e) {
            System.err.println("Fehler beim Lesen oder Verarbeiten der Datei: " + e.getMessage());
        }
    }
}