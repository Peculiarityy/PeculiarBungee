package plugin.sparterra.peculiarbungee.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import plugin.sparterra.peculiarbungee.Main;
import plugin.sparterra.peculiarbungee.Rank.Rank;

import java.lang.reflect.Executable;
import java.util.ArrayList;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/27/2017
 * All work belongs to ShadyCarpet
 */
public class SilenceChatCommand extends Command {

    public SilenceChatCommand() {
        super("silencechat", "", new String[0]);
    }
    public static ArrayList<String> mutedPlayerList = new ArrayList<String>();
    public static boolean isSilenced = false;
    public static Rank silencedRank = Rank.MOD;
    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        if(Main.isRankHighEnough(player, Rank.MOD)) {
           if(!isSilenced) {
                ProxyServer.getInstance().broadcast("§b§lPeculiar§3§l Realm §8┃ §7Chat was§b silenced §7by " + ((ProxiedPlayer) commandSender).getDisplayName() + "§7.");
                ProxyServer.getInstance().broadcast("§b§lPeculiar§3§l Realm §8┃ §7Rank " + Rank.MOD.getPrefix() + "§7 and up ONLY can chat now.");
                isSilenced = true;

                if(Main.isRankHighEnough(player, Rank.MOD)) {
                    ((ProxiedPlayer) commandSender).sendMessage("§b§lPeculiar§3§l Realm §8┃ MOD AND UP CAN ONLY SPEAK NOW!");

                } else {
                    mutedPlayerList.add(player.getDisplayName());

                }

            }

            ProxyServer.getInstance().broadcast("§b§lPeculiar§3§l Realm §8┃ §7Chat was§b un-silenced §7by " + player.getDisplayName() + "§7.");
            isSilenced = false;
            mutedPlayerList.remove(player.getDisplayName());
            return;

        }
        return;

    }
}
