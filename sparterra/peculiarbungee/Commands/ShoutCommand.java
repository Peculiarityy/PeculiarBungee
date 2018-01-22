package plugin.sparterra.peculiarbungee.Commands;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import plugin.sparterra.peculiarbungee.Main;
import plugin.sparterra.peculiarbungee.Rank.Rank;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/25/2017
 * All work belongs to ShadyCarpet
 */
public class ShoutCommand extends Command {

    public ShoutCommand(){
        super("shout", "", new String[0] );
    }
//{"staffchat", "schat", "sc", "staffc"}
    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer p = (ProxiedPlayer) commandSender;
        if(Main.isRankHighEnough(p, Rank.ADMIN)) {

            if(args.length < 1){
                commandSender.sendMessage("§8[§bSHOUT§8]§c You must supply a message to SHOUT!");
            } else {
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i<args.length; i++) {
                    sb.append(args[i]).append(" ");
                }
                String message = sb.toString().trim();
                ProxyServer.getInstance().broadcast("§8[§b§lStaff Shout§8] §7" + p.getName() + "§8:§f " + ChatColor.translateAlternateColorCodes('&', message));
            }

        } else {
            commandSender.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You do not have " + Rank.ADMIN.getPrefix() + "§7 to access this command!!");
        }

    }
}

