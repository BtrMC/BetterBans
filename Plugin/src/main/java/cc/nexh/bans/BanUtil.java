package cc.nexh.bans;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
    }
    public static String cColor(String textToTranslate) { return ChatColor.translateAlternateColorCodes('&', textToTranslate); }
    public static void sendPl(Player player, String msg) {
        player.sendMessage(msg);
    }
    public static void bcMsg(String msg) { Bukkit.broadcastMessage(msg); }
}
