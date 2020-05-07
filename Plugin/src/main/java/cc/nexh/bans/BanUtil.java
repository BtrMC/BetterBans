package cc.nexh.bans;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class BanUtil {
    /*
     * Post a ban to the API.
     */
    public static void postBan(String username, String reason, String banner) throws IOException {
        URL url = new URL("http://localhost:4000/add_user/");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String jsonInputString = "{\"name\": \""+ username + "\", \"reason\": \"" + reason + "\", \"banned_by\": \"" + banner + "\"}";
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }
    }
    public static void getBan(String name, String reason, String banner) throws IOException, ParseException {
        URL url = new URL("http://localhost:4000/ban?name=" + name + "&reason=" + reason + "&banner=" + banner);
        URLConnection connection = url.openConnection();
        HttpURLConnection httpConn = (HttpURLConnection) connection;
        BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(response.toString());
        System.out.println(json);
    }
    public static String cColor(String textToTranslate) { return ChatColor.translateAlternateColorCodes('&', textToTranslate); }
    public static void sendPl(Player player, String msg) {
        player.sendMessage(msg);
    }
    public static void bcMsg(String msg) { Bukkit.broadcastMessage(msg); }
}
