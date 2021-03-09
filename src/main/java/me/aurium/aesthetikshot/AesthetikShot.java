package me.aurium.aesthetikshot;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.aurium.aesthetikshot.config.ConfigHolder;
import me.aurium.aesthetikshot.config.ReloadCommand;
import me.aurium.aesthetikshot.listeners.BulletHitBlockListener;
import me.aurium.aesthetikshot.listeners.PotionListener;
import me.aurium.aesthetikshot.listeners.ShootHideBulletListener;
import me.aurium.aesthetikshot.listeners.WeaponSwapListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import space.arim.dazzleconf.ConfigurationOptions;
import space.arim.dazzleconf.ext.snakeyaml.SnakeYamlConfigurationFactory;
import space.arim.dazzleconf.helper.ConfigurationHelper;

/**
 * Sloppy shitcode because i literally do not care. Basically just decompiled the old thing
 * and pasted the shit back here
 */
public class AesthetikShot extends JavaPlugin {

    private ProtocolManager protocolManager;
    private ConfigHolder holder;

    @Override
    public void onEnable() {
        this.protocolManager = ProtocolLibrary.getProtocolManager();

        this.holder = new ConfigHolder( new ConfigurationHelper<>(this.getDataFolder().toPath(), "config.yml",
                new SnakeYamlConfigurationFactory<>(Config.class, ConfigurationOptions.defaults())) );

        holder.getOrLoad();

        PluginManager manager = this.getServer().getPluginManager();

        manager.registerEvents(new BulletHitBlockListener(this,protocolManager),this);
        manager.registerEvents(new PotionListener(holder.getOrLoad()),this);
        manager.registerEvents(new ShootHideBulletListener(this,protocolManager),this);
        manager.registerEvents(new WeaponSwapListener(this, holder.getOrLoad()),this);

        this.getCommand("reloadAS").setExecutor(new ReloadCommand(holder));
    }



}
