package gg.qteam.aesthetikshot;

import space.arim.dazzleconf.annote.ConfComments;
import space.arim.dazzleconf.annote.ConfDefault;
import space.arim.dazzleconf.annote.ConfKey;

public interface Config {

    @ConfKey("swap_countdown")
    @ConfComments("The amount of ticks it takes to count down the swap timer")
    @ConfDefault.DefaultInteger(20)
    int swapTimer();

    @ConfKey("effects.boost_reload_multiplier")
    @ConfComments("The amount to boost reload speed by when they have the reload effect")
    @ConfDefault.DefaultDouble(1.2)
    double reloadBoost();

    @ConfKey("messages.swap_message")
    @ConfComments("The message sent every tick while counting down")
    @ConfDefault.DefaultString("&7Swapping (&c%s.%s&7)")
    String swapMessage();

    @ConfKey("effects.restrict_shooting_effect")
    @ConfDefault.DefaultString("WEAKNESS")
    @ConfComments("Enum name of potion effect that will cause player to not shoot at all")
    String restrictShooting();

    @ConfKey("effects.boost_reload_effect")
    @ConfDefault.DefaultString("SPEED")
    @ConfComments("Enum name of potion effect that will cause player to reload faster")
    String boostShooting();

}
