package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class SynonymAPI {
    public static JSONObject getSynonyms(String wordForm) throws JSONException {
        try {
            URL url = new URL("https://languagetools.p.rapidapi.com/synonyms/" + URLEncoder.encode(wordForm, String.valueOf(StandardCharsets.UTF_8)).replace("+", "%20"));
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.setRequestProperty("x-rapidapi-host", "languagetools.p.rapidapi.com");
            request.setRequestProperty("x-rapidapi-key", "aca2c0c9a3mshdae9b0fd091fb0dp1923ffjsn84863605816e");
            request.setRequestMethod("GET");
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = inputStream.readLine()) != null) {
                response.append(inputLine);
            }
            inputStream.close();
            return new JSONObject(StringEscapeUtils.unescapeHtml4(response.toString()));
        } catch (IOException e) {
            return new JSONObject("{\"synonyms\":[]}");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws IOException, JSONException {
        System.out.println(getSynonyms("go"));
    }
}




