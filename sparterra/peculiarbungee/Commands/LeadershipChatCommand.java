package plugin.sparterra.peculiarbungee.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import plugin.sparterra.peculiarbungee.Main;
import plugin.sparterra.peculiarbungee.Rank.Rank;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/27/2017
 * All work belongs to ShadyCarpet
 */
public class LeadershipChatCommand extends Command {


    public LeadershipChatCommand() {
        super("leaderchat", "", new String[] {"leaderchat", "lc", "lchat", "leadership"});
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;

        if (Main.isRankHighEnough(player, Rank.HOST)) {


            if (args.length < 1) {
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You must supply a message.");
                return;
            }
            String msg = "";
            for (int i = 0; i < args.length; i++) {
                msg = msg + args[i] + " ";
            }
            msg = msg.trim();
            for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                if (Main.isRankHighEnough(p, Rank.HOST)) {
                    p.sendMessage("§3§lLEADERSHIP CHAT §8┃§7 " + commandSender.getName() + "§8: §b" + msg);
                }
            }
        } else {
            commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You do not have " + Rank.HOST.getPrefix() + "§7 to access this channel!");
        }

    }
}
