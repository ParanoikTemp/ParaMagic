package me.parax.paramagic.commands;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.meta.FireworkMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class testCMD implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            World world = player.getWorld();
            Firework fw = (Firework) world.spawnEntity(player.getLocation(), EntityType.FIREWORK);
            FireworkMeta fm = fw.getFireworkMeta();
            FireworkEffect fe = FireworkEffect.builder().trail(true).flicker(true).withColor(List.of(Color.fromRGB(255, 131, 0), Color.fromRGB(197, 0, 23))).build();
            fm.addEffect(fe);
            fw.setFireworkMeta(fm);
            fw.detonate();
            return true;
        }
        return false;
    }
}
