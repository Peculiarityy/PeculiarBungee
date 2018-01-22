package plugin.sparterra.peculiarbungee.Commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import plugin.sparterra.peculiarbungee.Main;
import plugin.sparterra.peculiarbungee.Rank.Rank;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/15/2017
 * All work belongs to ShadyCarpet
 */
public class AlertCommand extends Command {

    public AlertCommand(){
        super("announcement", "", new String[] {"announce"});
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        if (Main.isRankHighEnough(player, Rank.ADMIN)) {
            if (args.length == 0) {
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃ §7 You must supply a message to broadcast!");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    sb.append(args[i]).append(" ");
                }
                String message = sb.toString().trim();

                ProxyServer.getInstance().broadcast("§8[§bAnnouncement§8]§f " + ChatColor.translateAlternateColorCodes('&', message));
            }

        } else {
            commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃ §7You do not have " + Rank.ADMIN.getPrefix() + "§7 to access this command!");
        }
    }
}
