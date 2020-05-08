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
    public static void getBan(String name, String reason, String banner) throws IOException, ParseException {
        URL url = new URL("http://localhost:4000/ban?name=" + name + "&reason=" + reason.replace('&', '§').toString() + "&banner=" + banner);
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
