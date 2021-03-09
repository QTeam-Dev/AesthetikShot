package gg.qteam.aesthetikshot.listeners;

public class WeirdPotionTypeException extends RuntimeException {

    WeirdPotionTypeException() {
        super("A string in the config that was to be converted to a potion effect had no corresponding potion type! Please check your configuration!");
    }

}
