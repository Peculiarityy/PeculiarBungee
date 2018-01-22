package plugin.sparterra.peculiarbungee.Commands.Punish;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Listener;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import plugin.sparterra.peculiarbungee.Extra.TimeUtil;
import plugin.sparterra.peculiarbungee.Main;
import plugin.sparterra.peculiarbungee.Rank.Rank;

import java.io.IOException;
import java.util.ArrayList;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/27/2017
 * All work belongs to ShadyCarpet
 */
public class TempMuteCommand extends Command implements Listener {

    public static ArrayList<String> mutedPlayerList = new ArrayList<String>();

    public TempMuteCommand() {
        super("tempmute", "", new String[]{"mute"});
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        if (Main.isRankHighEnough(player, Rank.MOD)) {
            if (args.length < 2) {
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 Incorrect Usage:§b /tempmute <player> <reason>");
                return;
            }
            else {
                ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
                if (target == null) {
                    commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 Can't find player.");
                    return;
                }
                if (Main.isRankHighEnough(target, Rank.ADMIN)) {
                    commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§c You cannot punish this player.");
                    return;
                }
                if (mutedPlayerList.contains(target.getDisplayName())) {
                    mutedPlayerList.remove(target.getDisplayName());
                    commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§b " + target.getDisplayName() + "§7 is has been unmuted!");
                    target.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You were un-muted by§b " + player.getDisplayName());
                    return;
                }
                String reason = "";
                for (int i = 1; i < args.length; i++) {
                    reason = reason + args[i] + " ";
                }


                reason = reason.trim();
                reason = reason.replaceAll("'", "");
                target.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You were muted§7 with the reason:§c " + reason + "§7.");
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§b " + target.getDisplayName() + "§7 is has been muted!");

                mutedPlayerList.add(target.getDisplayName());
                for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                    if (Main.isRankHighEnough(p, Rank.MOD)) {
                        commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§b " + ((ProxiedPlayer) commandSender).getDisplayName() + " §7muted " + target + "§7 with the reason§8:§c " + reason + "§7.");
                        return;
                    }
                    return;
                }
            }
        } else {
            commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You do not have the rank " + Rank.MOD.getPrefix() + "§7 to access this command!");
        }

    }
}











