import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class CurrencyConverter {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Currency Converter");

            System.out.print("Enter Base Currency (Example USD): ");
            String base = sc.next().toUpperCase();

            System.out.print("Enter Target Currency (Example INR): ");
            String target = sc.next().toUpperCase();

            System.out.print("Enter Amount: ");
            double amount = sc.nextDouble();

            String apiKey = "https://api.exchangerate-api.com/v4/latest/" + base;

            URL url = new URL(apiKey);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            JSONObject json = new JSONObject(response.toString());
            JSONObject rates = json.getJSONObject("rates");

            double exchangeRate = rates.getDouble(target);
            double convertedAmount = amount * exchangeRate;

            System.out.println("\nConversion Result");
            System.out.println(amount + " " + base + " = " 
                    + convertedAmount + " " + target);

        } catch (Exception e) {
            System.out.println("Error fetching exchange rate");
        }

        sc.close();
    }
}