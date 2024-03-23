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

    private final List<Material> item1 = List.of(Material.RAW_IRON, Material.RAW_COPPER, Material.RAW_GOLD, Material.SAND, Material.IRON_ORE,
            Material.GOLD_ORE, Material.DIAMOND_ORE, Material.REDSTONE_ORE, Material.COAL_ORE, Material.COPPER_ORE);
    private final List<Material> item2 = List.of(Material.IRON_INGOT, Material.COPPER_INGOT, Material.GOLD_INGOT, Material.GLASS, Material.IRON_INGOT,
            Material.GOLD_INGOT, Material.DIAMOND, Material.REDSTONE, Material.COAL, Material.COPPER_INGOT);

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
                    for (int i = 0; i < item1.size(); ++i) {
                        if (item.getItemStack().getType() == item1.get(i)) {
                            world.dropItem(loc, new ItemStack(item2.get(i)));
                            func(player, item, event);
                            break;
                        }
                    }
                }
            }
        }
    }
}
