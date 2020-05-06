package cc.nexh.bans.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import cc.nexh.bans.Main;
import org.json.simple.JSONObject;
import org.bukkit.ChatColor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import org.json.simple.parser.JSONParser;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import org.json.simple.parser.ParseException;

public class banListener implements Listener {
    private Main plugin = Main.getPlugin(Main.class);
    String API_URL = plugin.getConfig().getString("api");
    @EventHandler
    public void onPlayerPreLogin(AsyncPlayerPreLoginEvent evt) throws IOException, ParseException {
        UUID uuid = evt.getUniqueId();
        URL url = new URL(API_URL);
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
        if(json.get("banned_uuid").toString().contains(uuid.toString())) {
            evt.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, "§cYou are perm-banned!\n§bReason " + ChatColor.translateAlternateColorCodes('&', json.get("ban_reason").toString()));
        }
    }
}
