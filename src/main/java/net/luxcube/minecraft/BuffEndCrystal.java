package net.luxcube.minecraft;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class BuffEndCrystal extends JavaPlugin implements Listener {

    private double buffPercentage;


    @Override
    public void onLoad() {
        saveDefaultConfig();
        YamlConfiguration config = YamlConfiguration.loadConfiguration(
                new File(this.getDataFolder(), "config.yml")
        );

        buffPercentage = config.getDouble("end-crystal-buff-percentage");
    }

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll((Plugin) this);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerDamage(EntityDamageByEntityEvent event) {

        Entity damager = event.getDamager();
        if (!damager.getType().equals(EntityType.ENDER_CRYSTAL))
            return;

        event.setDamage(event.getDamage() - event.getDamage() * buffPercentage / 100.0D);
    }
}
