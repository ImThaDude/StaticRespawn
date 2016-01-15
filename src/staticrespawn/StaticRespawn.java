/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staticrespawn;

import java.util.ArrayList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

/*
This plugin will take dead players who do not have a bed spawn in DreamWorld to DreamWorlds spawn.
*/
public class StaticRespawn extends JavaPlugin implements Listener {

    ArrayList<String> StaticSpawningWorlds; 
    
    public void onEnable() {
        
        //Create config YML folder and stuff.
        getConfig().options().copyDefaults(true);
        saveConfig();
        
        StaticSpawningWorlds = (ArrayList<String>) this.getConfig().getList("StaticSpawningWorlds");
        getServer().getPluginManager().registerEvents(this, this);
        
    }
    
    public void onDisable() {
        
    }
    
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        if (p.getBedSpawnLocation() == null) {
            if (StaticSpawningWorlds.contains(p.getWorld().getName())) {
                event.setRespawnLocation(p.getWorld().getSpawnLocation());
            }
        }
    }
}
