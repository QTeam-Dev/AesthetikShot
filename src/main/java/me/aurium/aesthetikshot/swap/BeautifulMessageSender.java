package me.aurium.aesthetikshot.swap;

import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BeautifulMessageSender {

    public void sendActionbar(Player player, String message) {
        String translated = ChatColor.translateAlternateColorCodes('&',message);
        player.spigot().sendMessage(TextComponent.fromLegacyText(translated));
    }

}
