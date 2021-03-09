package me.aurium.aesthetikshot.config;

import me.aurium.aesthetikshot.Config;
import space.arim.dazzleconf.error.InvalidConfigException;
import space.arim.dazzleconf.helper.ConfigurationHelper;

import java.io.IOException;

public class ConfigHolder {

    private final ConfigurationHelper<Config> helper;
    private Config config;

    public ConfigHolder(ConfigurationHelper<Config> helper) {
        this.helper = helper;
    }

    //shitty singleton style design because i don't care
    public Config getOrLoad() {
        if (config == null) {
            return reloadChecked();
        } else {
            return config;
        }
    }

    Config reloadChecked() {
        try {
            return helper.reloadConfigData();
        } catch (IOException | InvalidConfigException e) {
            throw new UncheckedIOException(e);
        }
    }

}
