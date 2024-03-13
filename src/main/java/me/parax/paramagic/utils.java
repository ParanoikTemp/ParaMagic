package me.parax.paramagic;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class utils {
    public static @NotNull String ct(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static void PlaySound(Location location, Sound sound, int radius) {
        for (Player player: location.getNearbyPlayers(radius)) {
            player.playSound(player.getLocation(), sound, (float) ((radius - player.getLocation().distance(location)) / radius), 1.0f);
        }
    }

    public static Item getItem(Material material, Location location) {
        World world = location.getWorld();
        List<Entity> entities = (List<Entity>) world.getNearbyEntities(location, 0.5, 0.5, 0.5, entity -> entity instanceof Item);
        Item item;
        for (Entity entity : entities) {
            if (((Item) entity).getItemStack().getType() == material) {
                item = (Item) entity;
                return item;
            }
        }
        return null;
    }
}
