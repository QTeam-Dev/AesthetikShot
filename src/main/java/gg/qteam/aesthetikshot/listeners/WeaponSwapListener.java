package gg.qteam.aesthetikshot.listeners;

import com.shampaggon.crackshot.CSUtility;
import com.shampaggon.crackshot.events.WeaponPrepareShootEvent;
import gg.qteam.aesthetikshot.AesthetikShot;
import gg.qteam.aesthetikshot.Config;
import gg.qteam.aesthetikshot.swap.SwapWrapperMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Optional;

//
// Decompiled by Procyon v0.5.36
//

/**
 * Literally i have no idea what kind of shitcode this is this is straight from the decompiled plugin
 */
public class WeaponSwapListener implements Listener {

    private final AesthetikShot shot;
    private final SwapWrapperMap wrapperMap;
    private final CSUtility csUtility = new CSUtility();

    public WeaponSwapListener(AesthetikShot shot, Config config) {
        this.shot = shot;
        this.wrapperMap = new SwapWrapperMap(shot, config);
    }

    @EventHandler
    public void onShootWhenLocked(WeaponPrepareShootEvent event) {
        if (wrapperMap.isLocked(event.getPlayer())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onSwitchToMap(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        Inventory inv = event.getPlayer().getInventory();
        int slotId = event.getNewSlot();


        if (slotId >= 0 && slotId < inv.getSize()) {
            final ItemStack stack = inv.getItem(slotId);
            final CSUtility util = new CSUtility();
            final String weaponTitle = util.getWeaponTitle(stack);


            if (stack != null) {

                if (weaponTitle != null) {
                    wrapperMap.relock(player);

                    Optional<ItemMeta> itemMeta = Optional.ofNullable(stack.getItemMeta());

                    itemMeta.ifPresent(meta -> {
                        ItemMeta clone = meta.clone();
                        clone.setUnbreakable(true);
                        clone.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                        stack.setItemMeta(clone);
                    });
                }

            }
            else {

                if (wrapperMap.isLocked(player)) {
                    wrapperMap.unlock(player);
                }

            }
        }
    }


}
