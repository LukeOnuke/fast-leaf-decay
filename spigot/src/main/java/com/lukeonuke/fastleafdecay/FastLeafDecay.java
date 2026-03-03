package com.lukeonuke.fastleafdecay;

import com.lukeonuke.fastleafdecay.event.BlockBreakEventListener;
import com.lukeonuke.fastleafdecay.service.ConfigurationService;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FastLeafDecay extends JavaPlugin {
    public static FastLeafDecay plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        ConfigurationService.getInstance();
        getServer().getPluginManager().registerEvents(new BlockBreakEventListener(), this);
    }

    @Override
    public void onDisable() {
        
    }
}
