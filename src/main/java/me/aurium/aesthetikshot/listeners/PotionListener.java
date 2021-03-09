package me.aurium.aesthetikshot.listeners;

import com.shampaggon.crackshot.events.WeaponPrepareShootEvent;
import com.shampaggon.crackshot.events.WeaponReloadEvent;
import me.aurium.aesthetikshot.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;

import java.util.Optional;

public class PotionListener implements Listener {

    private final Config config;

    public PotionListener(Config config) {
        this.config = config;
    }

    @EventHandler
    public void onShootWithWeaknessEvent(final WeaponPrepareShootEvent event) {
        final Player player = event.getPlayer();

        Optional<PotionEffectType> effectType = Optional.ofNullable(PotionEffectType.getByName(config.restrictShooting()));

        effectType.ifPresentOrElse(type -> {
            if (player.hasPotionEffect(type)) {
                event.setCancelled(true);
            }
        },() -> {
            throw new WeirdPotionTypeException();
        });

    }

    @EventHandler
    public void onReloadWithSpeed(final WeaponReloadEvent event) {
        final Player player = event.getPlayer();

        Optional<PotionEffectType> effectType = Optional.ofNullable(PotionEffectType.getByName(config.restrictShooting()));

        effectType.ifPresentOrElse(type -> {
            if (player.hasPotionEffect(type)) {
                event.setReloadSpeed(config.reloadBoost());
            }
        },() -> {
            throw new WeirdPotionTypeException();
        });
    }

}
