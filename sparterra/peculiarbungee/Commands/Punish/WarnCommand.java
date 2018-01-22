package plugin.sparterra.peculiarbungee.Commands.Punish;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.bukkit.entity.Player;
import plugin.sparterra.peculiarbungee.Extra.DefaultFontInfo;
import plugin.sparterra.peculiarbungee.Main;
import plugin.sparterra.peculiarbungee.Rank.Rank;
import plugin.sparterra.peculiarbungee.Extra.CenteredText;

import java.io.IOException;

import static plugin.sparterra.peculiarbungee.Main.punishfile;
import static plugin.sparterra.peculiarbungee.Main.punishments;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/27/2017
 * All work belongs to ShadyCarpet
 */
public class WarnCommand extends Command {


    public WarnCommand() {
        super("warn", "", new String[]{"w", "warnings"});
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        if (Main.isRankHighEnough(player, Rank.MOD)) {
            if ((args == null) || (args.length < 2)) {
                player.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 Incorrect Usage:§b /warn <player> <reason>");
                return;
            }
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
            Integer warnings = punishments.getInt(target.getUniqueId() + ".Warnings");
            if (target == null) {
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 Can't find player.");
                return;
            }
            if (Main.isRankHighEnough(ProxyServer.getInstance().getPlayer(args[0]), Rank.ADMIN)) {
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§c This player cannot be punished.");
                return;
            }
            String reason = "";
            for (int i = 1; i < args.length; i++) {
                reason = reason + args[i] + " ";
            }
            reason = reason.trim();

          //  message.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Teestt").create() ) );

            int id = Punisher.generateRandomID();
            target.sendMessage("§b➸ §8▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
            target.sendMessage("§3➸             §c§l✖ WARNING!§c Distributed by " + player + "§c§l ✖");
            target.sendMessage("§3➸                             §cPunishment ID§8:§c " + id);
            target.sendMessage("§3➸ §cReason§8§l:");
            target.sendMessage("§3➸ §b" + reason);
            target.sendMessage("§b➸§8 ▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄");
            for (ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
                if (Main.isRankHighEnough(p, Rank.MOD)) {
                    commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§b " + player + " §7warned " + target + "§7 with reason§8:§c " + reason + "§7.");
                }
            }

            punishments.set(target.getUniqueId() + ".Warnings", warnings + 1);
            punishments.set(target.getUniqueId() + ".Reasons" + ".Punishment ID:" + id  + ".Reason", reason);
            punishments.set(target.getUniqueId() + ".Reasons" + ".Punishment ID:" + id  + ".Reason" + reason + ".Punisher", player.getDisplayName());
            try {

                Main.configpun.save(punishments, punishfile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            } else{
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You do not have the rank " + Rank.MOD.getPrefix() + "§7 to access this command!");
        }

        }


    }

