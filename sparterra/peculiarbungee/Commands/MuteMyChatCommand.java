package plugin.sparterra.peculiarbungee.Commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import plugin.sparterra.peculiarbungee.Main;
import plugin.sparterra.peculiarbungee.Rank.Rank;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/27/2017
 * All work belongs to ShadyCarpet
 */
public class MuteMyChatCommand extends Command {

    public static List<String> muted = new ArrayList<>();

    public MuteMyChatCommand() {
        super("mutemychat", "", new String[0]);
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        if(Main.isRankHighEnough(player, Rank.MOD)) {
            if(muted.contains(player.getDisplayName())) {
                muted.remove(player.getDisplayName());
                player.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You chat has been§b unmuted§7. To enable repeat the command.");
                return;
            }
            muted.add(player.getDisplayName());
            player.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You chat has been§b muted§7. To enable repeat the command.");
            return;
        } else {
            commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You do not have the rank " + Rank.ADMIN.getPrefix() + "§7 to access this command!");
        }
    }
}
