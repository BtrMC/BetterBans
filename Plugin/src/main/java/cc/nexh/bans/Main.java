package cc.nexh.bans;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import cc.nexh.bans.listeners.*;
import cc.nexh.bans.cmds.*;


public class Main extends JavaPlugin {
    public Main() {
    }
    public void onEnable() {
        loadConfig();
        this.getCommand("ban").setExecutor(new Ban());
        Bukkit.getServer().getPluginManager().registerEvents(new banListener(), this);
    }
    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
