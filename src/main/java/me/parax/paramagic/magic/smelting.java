package me.parax.paramagic.magic;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.EnchantingTable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.Predicate;

public class smelting implements Listener {

    private void func(Player player, Item item, PlayerInteractEvent event) {
        event.setCancelled(true);
        ItemStack it = item.getItemStack().subtract();;
        player.giveExp(-3);
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0f, 1.0f);
    }

    @EventHandler
    public void clickOnTable(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock() != null && event.getClickedBlock().getState() instanceof EnchantingTable) {
            Player player = event.getPlayer();
            Location blockLocation = event.getClickedBlock().getLocation();
            World world = event.getClickedBlock().getWorld();
            if (player.getTotalExperience() >= 3) {
                double x = blockLocation.getBlockX() + 0.5;
                double y = blockLocation.getBlockY() + 0.8; // Учитываем блоки над данным блоком
                double z = blockLocation.getBlockZ() + 0.5;
                Predicate<Entity> isItem = entity -> entity instanceof Item;
                List<Entity> entities = (List<Entity>) world.getNearbyEntities(new Location(world, x, y, z), 0.5, 0.5, 0.5, isItem);
                for (Entity entity : entities) {
                    Item item = (Item) entity;
                    Location loc = item.getLocation();
                    if (item.getItemStack().getType() == Material.RAW_IRON) {
                        world.dropItem(loc, new ItemStack(Material.IRON_INGOT));
                        func(player, item, event);
                        break;
                    }
                    if (item.getItemStack().getType() == Material.RAW_COPPER) {
                        world.dropItem(loc, new ItemStack(Material.COPPER_INGOT));
                        func(player, item, event);
                        break;
                    }
                    if (item.getItemStack().getType() == Material.RAW_GOLD) {
                        world.dropItem(loc, new ItemStack(Material.GOLD_INGOT));
                        func(player, item, event);
                        break;
                    }
                }
            }
        }
    }
}
