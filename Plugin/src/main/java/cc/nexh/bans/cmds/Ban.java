package cc.nexh.bans.cmds;

import cc.nexh.bans.BanUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.parser.ParseException;

import java.io.IOException;


public class Ban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("bans.ban"))  {
            BanUtil.sendPl(player, BanUtil.cColor("&cYou don't have permission!"));
            return true;
        } else {
            if(args.length < 1) {
                BanUtil.sendPl(player, BanUtil.cColor("&cUsage: /ban (player) (reason)"));
                return true;
            }
            try {
                StringBuilder st = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    if(i < args.length - 1) {
                        st.append(args[i]).append("%20");
                    } else {
                        st.append(args[i]);
                    }
                }
                StringBuilder st2 = new StringBuilder();
                for (int i2 = 1; i2 < args.length; i2++) {
                    if(i2 < args.length - 1) {
                        st2.append(args[i2]).append(" ");
                    } else {
                        st2.append(args[i2]);
                    }
                }
                if((st + "").equals("")) {
                    BanUtil.sendPl(player, BanUtil.cColor("&cUsage: /ban (player) (reason)"));
                    return true;
                }
                BanUtil.getBan(Bukkit.getPlayer(args[0]).getName(), st.toString(), player.getName());
                Bukkit.getPlayer(args[0]).kickPlayer("§cYou are perm-banned!\n§bReason: " + BanUtil.cColor(st2.toString()));
                BanUtil.sendPl(player, BanUtil.cColor("&cYou have perm-banned &b" + Bukkit.getPlayer(args[0]).getName()));
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
