package com.lukeonuke.fastleafdecay;

import com.lukeonuke.fastleafdecay.event.BlockBreakEventListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class FastLeafDecay extends JavaPlugin {

    public static FastLeafDecay plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getPluginManager().registerEvents(new BlockBreakEventListener(), this);
    }

    @Override
    public void onDisable() {
        
    }
}
