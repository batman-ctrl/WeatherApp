import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        // 1. Create an HTTP Client
        HttpClient client = HttpClient.newHttpClient();

        // 2. Build the API request
        String apiKey = "cca0c4235977fbdb063e1ecfe9d8b19b";
        String city = "Cape Town";
        String search = city.replace(" ","+");
        String endpoint = "https://api.openweathermap.org/data/2.5/weather?q="+search+"&appid="+apiKey;


        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(endpoint))
                .GET()
                .build();


        // 3. Send the request and receive the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // 4. Parse the JSON response
        JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();

        // 5. Get information from JSON object
        String cityName = jsonObject.get("name").getAsString();
        JsonObject main = jsonObject.get("main").getAsJsonObject();
        double temperature = main.get("temp").getAsDouble();

        // 6. Show output
        System.out.println("City :"+cityName);
        System.out.println("Temperature :"+temperature);
    }
}