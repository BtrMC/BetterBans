package cc.nexh.bans;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class Main extends JavaPlugin {
    public Main() {
    }
    public void onEnable() {
        loadConfig();
        // this.getCommand("wh").setExecutor(new Reload());
        // Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }
    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
