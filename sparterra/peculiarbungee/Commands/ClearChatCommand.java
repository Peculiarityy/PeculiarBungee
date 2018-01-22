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
public class ClearChatCommand extends Command {

    public ClearChatCommand() {
        super("clearchat", "", new String[0]);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        if (Main.isRankHighEnough(player, Rank.MOD)) {
            for (int i = 0; i < 100; i++) {
                ProxyServer.getInstance().broadcast(" ");
            }
            ProxyServer.getInstance().broadcast("§b§lPeculiar§3§l Realm §8┃§7 Chat was§a cleared§7 by " + player.getDisplayName() + "§7.");
        } else {
            commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You do not have the rank " + Rank.MOD.getPrefix() + "§7 to access this command!");
        }
    }
}
