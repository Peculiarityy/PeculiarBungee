package plugin.sparterra.peculiarbungee.Commands;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import plugin.sparterra.peculiarbungee.Main;
import plugin.sparterra.peculiarbungee.Rank.Rank;

import java.lang.reflect.Proxy;
import java.util.Arrays;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/28/2017
 * All work belongs to ShadyCarpet
 */
public class OverwriteCommand implements Listener {


    @EventHandler
    public void TabEvent(PlayerChatTabCompleteEvent event) {
        ProxiedPlayer p = (ProxiedPlayer) event.getPlayer();
        if(Main.isRankHighEnough(p, Rank.MANAGER_DEV)) {
            event.getTabCompletions();

        } else {
            event.getTabCompletions().clear();
            event.getTabCompletions().addAll(Arrays.asList("hello", "world", "#hacked"));
        }
    }
}
