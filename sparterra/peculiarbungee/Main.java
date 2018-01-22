package plugin.sparterra.peculiarbungee;


import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;


import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import net.md_5.bungee.event.EventHandler;


import plugin.sparterra.peculiarbungee.Commands.*;

import plugin.sparterra.peculiarbungee.Commands.Punish.KickCommand;
import plugin.sparterra.peculiarbungee.Commands.Punish.TempMuteCommand;
import plugin.sparterra.peculiarbungee.Commands.Punish.WarnCommand;

import plugin.sparterra.peculiarbungee.Events.TabEvent;
import plugin.sparterra.peculiarbungee.Rank.Rank;
import plugin.sparterra.peculiarbungee.Rank.RankListCommand;
import plugin.sparterra.peculiarbungee.Rank.RankSetCommand;

import java.io.File;

import java.io.IOException;


import java.util.ArrayList;
import java.util.HashMap;

/*
 * Created by ShadyCarpet
 * PeculiarBungee created in 7/15/2017
 * All work belongs to ShadyCarpet
 */
public class Main extends Plugin implements Listener {



    private static HashMap<String, Rank> ranks = new HashMap<>();
    ArrayList<String> staff = new ArrayList<String>();
    public static Configuration config;
    public static Configuration punishments;
    public static ConfigurationProvider configp;
    public static ConfigurationProvider configpun;
    public static File configfile;
    public static File punishfile;

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerCommand(this, new AlertCommand());
        getProxy().getPluginManager().registerCommand(this, new RankSetCommand());
        getProxy().getPluginManager().registerCommand(this, new ShoutCommand());
        getProxy().getPluginManager().registerCommand(this, new StaffChatCommand());
        getProxy().getPluginManager().registerCommand(this, new RankListCommand());

        getProxy().getPluginManager().registerCommand(this, new SilenceChatCommand());
        getProxy().getPluginManager().registerCommand(this, new MuteMyChatCommand());
        getProxy().getPluginManager().registerCommand(this, new LeadershipChatCommand());
        getProxy().getPluginManager().registerCommand(this, new ClearChatCommand());


        getProxy().getPluginManager().registerCommand(this, new WarnCommand());
        getProxy().getPluginManager().registerCommand(this, new TempMuteCommand());
        getProxy().getPluginManager().registerCommand(this, new KickCommand());

        getProxy().getPluginManager().registerListener(this, new TabEvent());
        getProxy().getPluginManager().registerListener(this, new TempMuteCommand());
        //getProxy().getPluginManager().registerListener(this, new OverwriteCommand());
        getProxy().getPluginManager().registerListener(this, this);
        new HashMap<>();
        getLogger().info("Peculiar Bungeecord has successfully started.");

        configfile = new File(ProxyServer.getInstance().getPluginsFolder() + "/PeculiarBungee/config.yml");
        punishfile = new File(ProxyServer.getInstance().getPluginsFolder() + "/PeculiarBungee/punishmental.yml");
        if (configfile.exists()) {

        } else {
            try {
                configfile.createNewFile();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
             try {
                 punishfile.createNewFile();
             } catch (IOException e) {
                e.printStackTrace();
             }
        }
        configp = ConfigurationProvider.getProvider(YamlConfiguration.class);
        try {
            config = configp.load(configfile);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        configpun = ConfigurationProvider.getProvider(YamlConfiguration.class);
        try {
            punishments = configpun.load(punishfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDisable() {

        try {
            configp.save(config, configfile);
            configpun.save(punishments, punishfile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onChat(ChatEvent e) {

        String msg = e.getMessage();

        ProxiedPlayer p = (ProxiedPlayer) e.getSender();
        String server = p.getServer().getInfo().getName();
        String uuid = p.getUniqueId().toString();
        //String rank = config.getString(uuid + ".Rank");
        //String rank = getRank(p).getColor();
        if (msg.startsWith("/" )) {
            e.setCancelled(false);
            return;
        } else if (isRankHighEnough(p, Rank.DESIGNER)) {
            ProxyServer.getInstance().broadcast("§b" + server + " §8┃ §7" + getRank(p).getPrefix() + " §7" + p.getDisplayName() + "§8: §f" + msg.replaceAll("&", "§"));
            e.setCancelled(true);
            return;
        } else if (TempMuteCommand.mutedPlayerList.contains(p.getDisplayName())) {
            e.setCancelled(true);
            p.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 You have been muted and cannot speak!");

        } else if (SilenceChatCommand.mutedPlayerList.contains(p.getDisplayName())) {
            e.setCancelled(true);
            p.sendMessage("§b§lPeculiar§3§l Realm §8┃§7 Chat has been silenced and you cannot speak!");
        }
         else
        {
            ProxyServer.getInstance().broadcast("§b" + server + " §8┃ §7" + getRank(p).getPrefix() + " §7" + p.getDisplayName() + "§8: §f" + msg);
            e.setCancelled(true);
            return;
        }

        }





    @EventHandler
    public void PlayerJoined(PostLoginEvent event) {
        // e.setJoinMessage(null);

        String uuid = event.getPlayer().getUniqueId().toString();
        if (!config.contains(uuid + ".Rank")) {
            config.set(uuid + ".Rank", "DEFAULT");
            config.set(uuid + ".Banned", false);
            //config.set(uuid + ".Time", "0");
            punishments.set(uuid + ".Warnings", 0);
            getLogger().info("Added user " + uuid);
            try {
                configp.save(config, configfile);
                configpun.save(punishments, punishfile);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            ProxyServer.getInstance().broadcast("§b" + event.getPlayer().getName() + "§7 has joined §b§lPeculiar Realm§7 for the first time");
            getLogger().info("First Time Joining!");
            setupRank(event.getPlayer());
        } else {
            ProxyServer.getInstance().broadcast("§b" + event.getPlayer().getName() + "§7 has joined §b§lPeculiar Realm§7");
            setupRank(event.getPlayer());
        }
        if(event.getPlayer().hasPermission("peculiarbungee.staffchat")) {
            staff.add(event.getPlayer().getDisplayName());
        return;
        }


    }


    public static void setupRank(ProxiedPlayer p) {
        String ladderName = "troll";
        String uuid = p.getUniqueId().toString();
        //uuid = uuid.replaceAll("-", "");
        if (!config.contains(uuid + ".Rank")) {

            p.sendMessage("§4§lSEVERE: §cFirst time joining. Unable to find UUID");
            p.sendMessage("§4§lSEVERE: §cSetting rank to §lDEFAULT §cautomatically.");
            ladderName = "DEFAULT";


        } else if (config.contains(uuid + ".Rank")) {
            String rankname = config.getString(uuid + ".Rank").toUpperCase();
            ProxyServer.getInstance().getLogger().info("Rank for " + uuid + " was found! Their rank is " + rankname);
            ladderName = rankname;


            //String ladderName = config.getString(uuid + ".Rank");

            Rank rank = null;
            for (Rank r : Rank.values()) {
                if (r.toString().equals(ladderName)) {
                    rank = r;
                }
            }
            ProxyServer.getInstance().getLogger().info("Rank for " + uuid + " was found! Adding  " + rank + " to the hashmap");
            ranks.put(uuid, rank);
            return;
            // p.setDisplayName(getRank(p).getColor() + p.getName());
        }
    }



    public static Rank getRank(ProxiedPlayer p)
    {
        String uuid = p.getUniqueId().toString();

        if (ranks.containsKey(uuid)) {
          //  ProxyServer.getInstance().getLogger().info("User " + p.getDisplayName() + "has been found in the database!");
            return (Rank)ranks.get(uuid);
        }
        //ProxyServer.getInstance().getLogger().info("User " + p.getDisplayName() + " was not found!!");
        return Rank.DEFAULT;
    }

    public static boolean isRankHighEnough(Rank pr, Rank needed)
    {
        if (pr.getLadder() <= needed.getLadder()) {
            return true;
        }
        return false;
    }

    public static boolean isRankHighEnough(ProxiedPlayer p, Rank needed)
    {
        Rank pr = getRank(p);
        if (pr.getLadder() <= needed.getLadder()) {
            return true;
        }
        return false;
    }


}