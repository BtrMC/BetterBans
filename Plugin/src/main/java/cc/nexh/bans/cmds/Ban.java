package cc.nexh.bans.cmds;

import cc.nexh.bans.BanUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;


public class Ban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("bans.ban"))  {
            BanUtil.sendPl(player, BanUtil.cColor("&cYou don't have permission!"));
            return true;
        } else {
            try {
                StringBuilder st = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    st.append(args[i]).append(" ");
                }
                BanUtil.postBan(Bukkit.getPlayer(args[0]).getName(), st.toString(), player.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
