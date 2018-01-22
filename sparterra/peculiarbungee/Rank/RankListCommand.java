package plugin.sparterra.peculiarbungee.Rank;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import plugin.sparterra.peculiarbungee.Main;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/26/2017
 * All work belongs to ShadyCarpet
 */
public class RankListCommand extends Command {

    public RankListCommand(){
        super("ranklist", "", new String[] {"ranks"});
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        if (Main.isRankHighEnough(player, Rank.ADMIN)) {
                commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃ §7Ranks\n§b§lHouse Ranks§8: "+ Rank.DEFAULT.getPrefix()  + "§7, " + Rank.MAGPIE.getPrefix() + "§7, " + Rank.CARDINAL.getPrefix() + "§7, " + Rank.GOLDEN_EAGLE.getPrefix() + "§7, " + Rank.PEREGRINE.getPrefix() + "\n\n§b§lDonor Ranks§8§l: " + Rank.INTERMEDIATE.getPrefix() + "§7, " + Rank.ADVANCED.getPrefix() + "§7, " + Rank.VIP.getPrefix() + "\n\n§b§lStaff Ranks§8: " + Rank.DESIGNER.getPrefix() + "§7, " +Rank.DIMEN_EDITOR.getPrefix() +  "§7, " + Rank.DIRECTOR.getPrefix() + "§7, " + Rank.ARCHITECT.getPrefix() + "§7, " + Rank.DEVELOPER.getPrefix() + "§7, " + Rank.MOD.getPrefix() + "§7, " + Rank.SR_MOD.getPrefix() + "\n\n§b§lLeadership Team§8§l: " + Rank.HOST.getPrefix() + ", " + Rank.ADMIN.getPrefix() + "§7, " + Rank.ASSISTANT_DIMEN.getPrefix() + "§7, " + Rank.ASSISTANT_ARCH.getPrefix() + "§7, " + Rank.ASSISTANT_DEV.getPrefix() + "§7, " + Rank.MANAGER_DIMEN.getPrefix() + "§7, " + Rank.MANAGER_ARCH.getPrefix() + "§7, " + Rank.MANAGER_DEV.getPrefix() + "§7, " + Rank.OWNER.getPrefix());

        } else {
            commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You do not have the rank " + Rank.ADMIN.getPrefix() + "§7 to access this command!");
        }
    }
}
