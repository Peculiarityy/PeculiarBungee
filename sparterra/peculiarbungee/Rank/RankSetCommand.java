package plugin.sparterra.peculiarbungee.Rank;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import plugin.sparterra.peculiarbungee.Main;

import java.io.IOException;
import java.util.HashMap;

import static plugin.sparterra.peculiarbungee.Main.config;
import static plugin.sparterra.peculiarbungee.Main.configfile;
import static plugin.sparterra.peculiarbungee.Main.configp;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/16/2017
 * All work belongs to ShadyCarpet
 */
public class RankSetCommand extends Command {


    public static HashMap<String, Rank> ranks = new HashMap();

    public RankSetCommand() {
        super("rank", "", new String[0]);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer) commandSender;
        if (Main.isRankHighEnough(p, Rank.ADMIN)) {

            if (args.length != 2) {
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 Incorrect Usage: §b/rank <player> <rank>");
                return;
            }
            if (ProxyServer.getInstance().getPlayer(args[0]) == null) {
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 Can't find player.");
                return;
            }
            //Player target = ProxyServer.getInstance().getPlayer(args[0]);
            // Player t = Bukkit.getPlayer(args[0]);
            if (Main.isRankHighEnough(ProxyServer.getInstance().getPlayer(args[0]), Rank.ADMIN)) {
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§c This players rank can not be changed.");
                return;
            }

            String rankRaw = args[1];
            Rank rank;
            try {
                rank = Rank.valueOf(rankRaw);
            } catch (Exception e) {
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 Can't find rank.");
                return;
            }
            try {
                config.set(ProxyServer.getInstance().getPlayer(args[0]).getUniqueId() + ".Rank", rank.toString());
               // ProxyServer.getInstance().getLogger().info("Set rank for" + ProxyServer.getInstance().getPlayer(args[0]).getUniqueId() + " to " + rank);
                try {
                    configp.save(config, configfile);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 No connection to database!");
                return;
            }

            Main.setupRank(ProxyServer.getInstance().getPlayer(args[0]));
            commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You set " + ProxyServer.getInstance().getPlayer(args[0]).getDisplayName() + "'s §7rank to " + rank.getPrefix() + "§7.");
            ProxyServer.getInstance().getPlayer(args[0]).sendMessage("§b§lPeculiar§3§l Realm §8┃§7 " + commandSender.getName() + " §7set your rank to " + rank.getPrefix() + "§7.");
           // ProxyServer.getInstance().getPlayer(args[0]).disconnect(new TextComponent("§8 ┃ §b§lPeculiar§3§l Realms§8 ┃\n§c§lYou were kicked from Peculiar Realm.\n§7Punisher:§c " + commandSender.getName() + "\n§7 Reason:§c Rank Updated to " + rank.getPrefix() + "\n§7Expires:§a N/A\n§7Please relog into the server!"));
           // for (ProxiedPlayer player : ProxyServer.getInstance().getPlayers()) {
             //   if (Main.isRankHighEnough(player, Rank.DESIGNER)) {
              //      player.sendMessage("§b§lPeculiar§3§l Realm §8┃§b " + p.getName() + "§7 kicked for§b§l RANK UPDATE!");
              //  }
            //}
        } else {
        commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You do not have the rank " + Rank.ADMIN.getPrefix() + "§7 to access this command!");
        }
    }
}
//        ranks.put(commandSender.getName(), rank);
  //      commandSender.sendMessage("§8[§bAlert§8]§7 You have placed" + t.getDisplayName() + "in the rank " + rank);
    //    t.sendMessage("§8[§bAlert§8]§7 You have been placed in the group " + rank + " by " + commandSender.getName());
