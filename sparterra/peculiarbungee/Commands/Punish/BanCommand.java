package plugin.sparterra.peculiarbungee.Commands.Punish;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Command;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import plugin.sparterra.peculiarbungee.Main;
import plugin.sparterra.peculiarbungee.Rank.Rank;

import java.util.Set;
import java.util.UUID;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/28/2017
 * All work belongs to ShadyCarpet
 */
public class BanCommand extends Command {

    public BanCommand() {
        super("ban", "", new String[0]);
    }
    private Set<UUID> banned;


    public void ban(UUID uuid) {
        banned.add(uuid);
    }

    public void unban(UUID uuid) {
        banned.remove(uuid);
    }

    public boolean isBanned(UUID uuid) {
        return banned.contains(uuid);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPostLogin(PostLoginEvent event) {
        ProxiedPlayer player = event.getPlayer();
        if (isBanned(player.getUniqueId())) {
            player.disconnect(new TextComponent("You are banned!"));
        }
    }
    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        if (Main.isRankHighEnough(player, Rank.MOD)) {
            if(args.length < 2) {
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 Incorrect Usage: §b/kick <player> <reason>");
                return;
            }
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
            if (target == null) {
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 Can't find player.");
                return;
            }
            if (Main.isRankHighEnough(ProxyServer.getInstance().getPlayer(args[0]), Rank.MOD)) {
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§c This player cannot be punished.");
                return;
            }
            String reason = "";
            for (int i = 1; i < args.length; i++) {
                reason = reason + args[i] + " ";
            }
            reason = reason.trim();

            int id = Punisher.generateRandomID();
            target.disconnect("§8┃ §b§lPeculiar§3§l Realm §8┃\n§c§l You were banned from Peculiar Realm.\n§7Punishment ID§8§l:§3 #" + id + "\n§7Ban ID§8§l:§3 #" + id + "\n§7Reason§8§l: §c" + reason + "\n§7Distributor§8§l: §b" + player.getDisplayName() + "\n§7Expires§8§l:§3 NEVER\n§7 This is an NON-APPEALABLE ban.\n You have been removed from the Peulicar Realm.");
            banned.add(target.getUniqueId());
            for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                if (Main.isRankHighEnough(p, Rank.MOD)) {
                    p.sendMessage("§b§lPeculiar§3§l Realm §8┃§b " + p.getDisplayName() + " §7banned " + target + "§7 with reason§8:§c " + reason + "§7.");
                }
            }
        } else {
            commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You do not have the rank " + Rank.MOD.getPrefix() + "§7 to access this command!");
        }
    }
}
