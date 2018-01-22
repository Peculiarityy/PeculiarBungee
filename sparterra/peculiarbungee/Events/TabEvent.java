package plugin.sparterra.peculiarbungee.Events;

import net.md_5.bungee.api.event.TabCompleteEvent;
import net.md_5.bungee.api.plugin.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/29/2017
 * All work belongs to ShadyCarpet
 */
public class TabEvent implements Listener {


    public void TabEvent(TabCompleteEvent e) {
        e.setCancelled(true);
    }
}
