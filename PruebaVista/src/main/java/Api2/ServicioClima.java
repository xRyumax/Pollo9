package Api2;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ServicioClima {

    private final String API_KEY = "RSWJEBWXUKB5VUELTRECGXDE3";
    private final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public double obtenerTemperatura() throws Exception {
        String urlString = BASE_URL + "Arequipa?unitGroup=metric&key=" + API_KEY;
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
        JSONArray days = json.getJSONArray("days");
        return days.getJSONObject(0).getDouble("temp");
    }

    public List<Double> obtenerHistorialTemperaturas() throws Exception {
        String urlString = BASE_URL + "Arequipa?unitGroup=metric&key=" + API_KEY + "&include=history";
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
        JSONArray days = json.getJSONArray("days");
        List<Double> temperaturas = new ArrayList<>();
        for (int i = 0; i < days.length(); i++) {
            JSONObject day = days.getJSONObject(i);
            temperaturas.add(day.getDouble("temp"));
        }
        return temperaturas;
    }
}