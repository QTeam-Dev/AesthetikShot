package gg.qteam.aesthetikshot.config;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final ConfigHolder holder;

    public ReloadCommand(ConfigHolder holder) {
        this.holder = holder;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        holder.reloadChecked();
        commandSender.sendMessage(ChatColor.GRAY + "Reloaded AesthetikShot!");

        return true;
    }
}
