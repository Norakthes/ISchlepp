package xyz.norakthes.ischlepp;

import com.destroystokyo.paper.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class ISchlepp extends JavaPlugin implements Listener {

    static int playersAsleep = 0;

    static int percentageNeededToProceed = 30;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
    }

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event){
        if (event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            playersAsleep++;
            float playersOnline = getServer().getWorld("world").getPlayerCount();
            float onlinePercentage = Math.round((playersAsleep/playersOnline)*100);
            Bukkit.broadcastMessage(ChatColor.GOLD + event.getPlayer().getName() + " has entered their bed! " + "(" + playersAsleep + " / " + (int) playersOnline + ")" + " [" + (int) onlinePercentage + "%]");

            if (onlinePercentage > percentageNeededToProceed){
                getServer().getWorld("world").setTime(0);
                Bukkit.broadcastMessage(ChatColor.GOLD + "Rise and shine!");
            }
        }
    }

    @EventHandler
    public void onPlayerBedLeave(PlayerBedLeaveEvent event){
        playersAsleep--;
    }
}
