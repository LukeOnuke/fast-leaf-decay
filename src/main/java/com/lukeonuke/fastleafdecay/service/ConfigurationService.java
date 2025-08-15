package com.lukeonuke.fastleafdecay.service;


import com.lukeonuke.fastleafdecay.FastLeafDecay;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigurationService {
    private static ConfigurationService instance = null;

    @Getter
    private final boolean exploitPrevention;

    private ConfigurationService() {
        FileConfiguration config = FastLeafDecay.plugin.getConfig();

        exploitPrevention = config.getBoolean("exploitPrevention");
    }

    public static ConfigurationService getInstance() {
        if (instance == null) instance = new ConfigurationService();
        return instance;
    }
}
