package Bebidas;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class Api {
    private final String API_KEY = "06eada13556c346d9bde6b58dbb5c2c6";
    private final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=Arequipa,PE&units=metric&appid=";

    public double obtenerTemperatura() throws Exception {
        String urlString = BASE_URL + API_KEY;
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        
        JSONObject json = new JSONObject(content.toString());
        return json.getJSONObject("main").getDouble("temp");
    }
}

