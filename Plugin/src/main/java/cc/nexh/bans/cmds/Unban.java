package cc.nexh.bans.cmds;

import cc.nexh.bans.BanUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Unban implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("bans.unban"))  {
            BanUtil.sendPl(player, BanUtil.cColor("&cYou don't have permission!"));
            return true;
        } else {
            try {
                if(args.length != 0) {
                    BanUtil.rmBan(args[0]);
                    BanUtil.sendPl(player, BanUtil.cColor("&cYou have unbanned &b" + Bukkit.getPlayer(args[0]).getName()));
                    return false;
                } else {
                    BanUtil.sendPl(player, BanUtil.cColor("&cUsage: /unban (player)"));
                    return true;
                }
            } catch(IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
