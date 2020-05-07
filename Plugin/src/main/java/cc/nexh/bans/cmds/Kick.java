package cc.nexh.bans.cmds;

import cc.nexh.bans.BanUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;
        if (!player.hasPermission("bans.kick"))  {
            BanUtil.sendPl(player, BanUtil.cColor("&cYou don't have permission!"));
            return true;
        } else {
            if(args.length < 1) {
                BanUtil.sendPl(player, BanUtil.cColor("&cUsage: /kick (player) (reason)"));
                return true;
            }

                StringBuilder st = new StringBuilder();
                for (int i = 1; i < args.length; i++) {
                    st.append(args[i]).append(" ");
                }
            if((st + "").equals("")) {
                BanUtil.sendPl(player, BanUtil.cColor("&cUsage: /ban (player) (reason)"));
                return true;
            }
                Bukkit.getPlayer(args[0]).kickPlayer("§cYou have been kicked!\n§bReason: " + BanUtil.cColor(st.toString()));
        }
        return false;
    }
}
