package me.parax.paramagic.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class manaCMD implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            commandSender.sendMessage("На данный момент у вас " + player.getTotalExperience() + " маны.");
        } else {
            commandSender.sendMessage("This command only for players!");
        }
        return true;
    }
}
