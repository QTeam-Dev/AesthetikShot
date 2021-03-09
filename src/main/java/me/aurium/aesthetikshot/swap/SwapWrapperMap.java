package me.aurium.aesthetikshot.swap;

import me.aurium.aesthetikshot.AesthetikShot;
import me.aurium.aesthetikshot.Config;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SwapWrapperMap {

    private final Map<Player, SwapScheduler> schedulers = new HashMap<>();
    private final AesthetikShot shot;
    private final Config config;
    private final BeautifulMessageSender beautifulMessageSender = new BeautifulMessageSender();

    public SwapWrapperMap(AesthetikShot shot, Config config) {
        this.shot = shot;
        this.config = config;
    }

    public boolean isLocked(Player player) {
        return schedulers.containsKey(player);
    }

    public void relock(Player player) {
        if (schedulers.containsKey(player)) {
            unlock(player);
        }

        SwapScheduler scheduler = new SwapScheduler(this,beautifulMessageSender,config,player);

        schedulers.put(player,scheduler);

        scheduler.trigger(shot);
    }

    public void unlock(Player player) {
        if (schedulers.containsKey(player)) {
            schedulers.get(player).cancel();
            schedulers.remove(player);
        }
    }

    public void remove(Player player) {
        this.schedulers.remove(player);
    }

}
