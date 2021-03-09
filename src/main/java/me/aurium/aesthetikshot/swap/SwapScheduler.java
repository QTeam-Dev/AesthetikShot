package me.aurium.aesthetikshot.swap;

import me.aurium.aesthetikshot.AesthetikShot;
import me.aurium.aesthetikshot.Config;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Wrapper class for a scheduler
 */
public class SwapScheduler {

    private final BukkitRunnable runnable;
    private final SwapWrapperMap parent;
    private final BeautifulMessageSender sender;
    private final Config config;
    private final Player target;


    public SwapScheduler(SwapWrapperMap parent, BeautifulMessageSender sender, Config config, Player target) {
        this.parent = parent;
        this.sender = sender;
        this.config = config;
        this.target = target;

        //doesn't break code in constructor rules
        this.runnable = new BukkitRunnable() {
            private int configurable = config.swapTimer();

            @Override
            public void run() {
                int tens = configurable / 10;
                int ones = configurable - tens;

                sender.sendActionbar(target,String.format(config.swapMessage(),tens,ones));

                if (configurable == 0) {
                    //when counter is 0 unlock
                    parent.unlock(target);
                }

                configurable--;
            }
        };
    }

    public void cancel() {
        this.runnable.cancel();
    }

    public void trigger(AesthetikShot shot) {
        this.runnable.runTaskTimer(shot,0L,2L);
    }

}
