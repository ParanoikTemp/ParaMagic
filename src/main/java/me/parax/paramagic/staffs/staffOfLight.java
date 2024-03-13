package me.parax.paramagic.staffs;

import me.parax.paramagic.utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.logging.Logger;

public class staffOfLight implements Listener {

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = event.getPlayer();
            if (player.getTotalExperience() < 5) return;
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getItemMeta() == null) return;
            PersistentDataContainer pid = item.getItemMeta().getPersistentDataContainer();
            if (!(pid.has(NamespacedKey.fromString("paramagic")) && pid.has(NamespacedKey.fromString("staff")) && pid.get(NamespacedKey.fromString("staff"), PersistentDataType.STRING).compareToIgnoreCase("staffOfLight") == 0)) return;
            event.setCancelled(true);
            Block b = event.getClickedBlock();
            Block block = null;
            assert b != null;
            if (event.getBlockFace() == BlockFace.EAST)
                block = b.getWorld().getBlockAt(new Location(b.getWorld(), b.getX() + 1, b.getY(), b.getZ()));
            else if (event.getBlockFace() == BlockFace.WEST)
                block = b.getWorld().getBlockAt(new Location(b.getWorld(), b.getX() - 1, b.getY(), b.getZ()));
            else if (event.getBlockFace() == BlockFace.SOUTH)
                block = b.getWorld().getBlockAt(new Location(b.getWorld(), b.getX(), b.getY(), b.getZ() + 1));
            else if (event.getBlockFace() == BlockFace.NORTH)
                block = b.getWorld().getBlockAt(new Location(b.getWorld(), b.getX(), b.getY(), b.getZ() - 1));
            else if (event.getBlockFace() == BlockFace.UP)
                block = b.getWorld().getBlockAt(new Location(b.getWorld(), b.getX(), b.getY() + 1, b.getZ()));
            else if (event.getBlockFace() == BlockFace.DOWN)
                block = b.getWorld().getBlockAt(new Location(b.getWorld(), b.getX(), b.getY() - 1, b.getZ()));
            assert block != null;
            if (block.getType() == Material.CAVE_AIR || block.getType() == Material.AIR) {
                block.setType(Material.LIGHT);
                utils.PlaySound(block.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_HIT, 6);
                player.giveExp(-5);
            }
        }
    }
}
