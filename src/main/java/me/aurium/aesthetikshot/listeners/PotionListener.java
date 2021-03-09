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

        if (effectType.isPresent()) {
            if (player.hasPotionEffect(effectType.get())) {
                event.setCancelled(true);
            }
        } else {
            throw new WeirdPotionTypeException();
        }

    }

    @EventHandler
    public void onReloadWithSpeed(final WeaponReloadEvent event) {
        final Player player = event.getPlayer();

        Optional<PotionEffectType> effectType = Optional.ofNullable(PotionEffectType.getByName(config.restrictShooting()));

        if (effectType.isPresent()) {
            if (player.hasPotionEffect(effectType.get())) {
                event.setReloadSpeed(config.reloadBoost());
            }
        } else {
            throw new WeirdPotionTypeException();
        }

    }

}
