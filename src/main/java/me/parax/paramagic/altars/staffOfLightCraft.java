package me.parax.paramagic.altars;

import me.parax.paramagic.commands.getStaff;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.EulerAngle;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static me.parax.paramagic.utils.PlaySound;
import static me.parax.paramagic.utils.getItem;

public class staffOfLightCraft implements Listener {

    private Plugin plugin;

    public staffOfLightCraft(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.ENCHANTING_TABLE && checkNetherAltar.checkAltar(event.getClickedBlock()) && event.getPlayer().getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
            Block block = event.getClickedBlock();
            World world = event.getPlayer().getWorld();
            Player player = event.getPlayer();
            if (player.getTotalExperience() < 500) {
                player.sendMessage(Component.text("У вас недостаточно опыта для создания этого посоха.").color(TextColor.color(153, 153, 153)));
                return;
            }
            player.giveExp(-500);
            String side;
            if (world.getBlockAt(new Location(world, block.getX() - 1, block.getY() - 1, block.getZ())).getType() == Material.GOLD_BLOCK)
                side = "EAST";
            else if (world.getBlockAt(new Location(world, block.getX() + 1, block.getY() - 1, block.getZ())).getType() == Material.GOLD_BLOCK)
                side = "WEST";
            else if (world.getBlockAt(new Location(world, block.getX(), block.getY() - 1, block.getZ() + 1)).getType() == Material.GOLD_BLOCK)
                side = "NORTH";
            else if (world.getBlockAt(new Location(world, block.getX(), block.getY() - 1, block.getZ() - 1)).getType() == Material.GOLD_BLOCK)
                side = "SOUTH";
            else return;

            Item item1;
            Item item2;
            Item item3;

            switch (side) {
                case "EAST":
                    item1 = getItem(Material.NETHER_BRICKS, new Location(world, block.getX() + 0.5, block.getY(), block.getZ() - 0.5));
                    if (item1 == null) return;
                    item2 = getItem(Material.GLOWSTONE, new Location(world, block.getX() + 1.5, block.getY(), block.getZ() + 0.5));
                    if (item2 == null) return;
                    item3 = getItem(Material.NETHERRACK, new Location(world, block.getX() + 0.5, block.getY(), block.getZ() + 1.5));
                    if (item3 == null) return;
                    break;
                case "WEST":
                    item1 = getItem(Material.NETHER_BRICKS, new Location(world, block.getX() + 0.5, block.getY(), block.getZ() + 1.5));
                    if (item1 == null) return;
                    item2 = getItem(Material.GLOWSTONE, new Location(world, block.getX() - 0.5, block.getY(), block.getZ() + 0.5));
                    if (item2 == null) return;
                    item3 = getItem(Material.NETHERRACK, new Location(world, block.getX() + 0.5, block.getY(), block.getZ() - 0.5));
                    if (item3 == null) return;
                    break;
                case "NORTH":
                    item1 = getItem(Material.NETHER_BRICKS, new Location(world, block.getX() - 0.5, block.getY(), block.getZ() + 0.5));
                    if (item1 == null) return;
                    item2 = getItem(Material.GLOWSTONE, new Location(world, block.getX() + 0.5, block.getY(), block.getZ() - 0.5));
                    if (item2 == null) return;
                    item3 = getItem(Material.NETHERRACK, new Location(world, block.getX() + 1.5, block.getY(), block.getZ() + 0.5));
                    if (item3 == null) return;
                    break;
                default:
                    item1 = getItem(Material.NETHER_BRICKS, new Location(world, block.getX() + 1.5, block.getY(), block.getZ() + 0.5));
                    if (item1 == null) return;
                    item2 = getItem(Material.GLOWSTONE, new Location(world, block.getX() + 0.5, block.getY(), block.getZ() + 1.5));
                    if (item2 == null) return;
                    item3 = getItem(Material.NETHERRACK, new Location(world, block.getX() - 0.5, block.getY(), block.getZ() + 0.5));
                    if (item3 == null) return;
                    break;
            }

            event.setCancelled(true);
            item1.getItemStack().subtract();
            item2.getItemStack().subtract();
            item3.getItemStack().subtract();
            player.getInventory().getItemInMainHand().subtract();
            ArmorStand as1;
            ArmorStand as2;
            ArmorStand as3;
            ArmorStand as4;

            switch (side) {
                case "EAST":
                    as1 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() + 0.5, block.getY() - 1.5, block.getZ() - 0.5), EntityType.ARMOR_STAND);
                    as2 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() + 1.5, block.getY() - 1.5, block.getZ() + 0.5), EntityType.ARMOR_STAND);
                    as3 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() + 0.5, block.getY() - 1.5, block.getZ() + 1.5), EntityType.ARMOR_STAND);
                    as4 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() + 0.2, block.getY() - 0.3, block.getZ() + 1.025), EntityType.ARMOR_STAND);
                    break;
                case "WEST":
                    as1 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() + 0.5, block.getY() - 1.5, block.getZ() + 1.5), EntityType.ARMOR_STAND);
                    as2 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() - 0.5, block.getY() - 1.5, block.getZ() + 0.5), EntityType.ARMOR_STAND);
                    as3 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() + 0.5, block.getY() - 1.5, block.getZ() - 0.5), EntityType.ARMOR_STAND);
                    as4 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() + 0.2, block.getY() - 0.3, block.getZ() + 1.025), EntityType.ARMOR_STAND);
                    break;
                case "NORTH":
                    as1 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() - 0.5, block.getY() - 1.5, block.getZ() + 0.5), EntityType.ARMOR_STAND);
                    as2 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() + 0.5, block.getY() - 1.5, block.getZ() - 0.5), EntityType.ARMOR_STAND);
                    as3 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() + 1.5, block.getY() - 1.5, block.getZ() + 0.5), EntityType.ARMOR_STAND);
                    as4 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() + 1.05, block.getY() - 0.3, block.getZ() + 0.725), EntityType.ARMOR_STAND);
                    break;
                default:
                    as1 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() + 1.5, block.getY() - 1.5, block.getZ() + 0.5), EntityType.ARMOR_STAND);
                    as2 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() + 0.5, block.getY() - 1.5, block.getZ() + 1.5), EntityType.ARMOR_STAND);
                    as3 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() - 0.5, block.getY() - 1.5, block.getZ() + 0.5), EntityType.ARMOR_STAND);
                    as4 = (ArmorStand) world.spawnEntity(new Location(world, block.getX() + 1.05, block.getY() - 0.3, block.getZ() + 0.725), EntityType.ARMOR_STAND);
                    break;
            }

            as1.setGravity(false);
            as2.setGravity(false);
            as3.setGravity(false);
            as4.setGravity(false);
            as1.setInvisible(true);
            as2.setInvisible(true);
            as3.setInvisible(true);
            as4.setInvisible(true);
            as1.getEquipment().setHelmet(new ItemStack(Material.NETHER_BRICKS));
            as2.getEquipment().setHelmet(new ItemStack(Material.GLOWSTONE));
            as3.getEquipment().setHelmet(new ItemStack(Material.NETHERRACK));
            as4.getEquipment().setHelmet(new ItemStack(Material.BLAZE_ROD));

            if (side.equals("EAST")) {
                as4.setHeadPose(new EulerAngle(0, 0, Math.toRadians(315)));
                as4.setRotation(90, 0);
            } else if (side.equals("WEST")) {
                as4.setHeadPose(new EulerAngle(0, 0, Math.toRadians(315)));
                as4.setRotation(90, 0);
            } else if (side.equals("NORTH")) {
                as4.setHeadPose(new EulerAngle(0, 0, Math.toRadians(315)));
            } else {
                as4.setHeadPose(new EulerAngle(0, 0, Math.toRadians(315)));
            }

            as1.setDisabledSlots(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.LEGS);
            as2.setDisabledSlots(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.LEGS);
            as3.setDisabledSlots(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.LEGS);
            as4.setDisabledSlots(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.LEGS);

            ItemStack staffOfLight = getStaff.staffOfLightItem(player);
            AtomicInteger count = new AtomicInteger(101);
            plugin.getServer().getScheduler().runTaskTimer(plugin, (task) -> {
                Location loc = as1.getLocation();
                int cnt = count.decrementAndGet();
                if (cnt == 90) PlaySound(as4.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 10);
                if (cnt > 60) {
                    as1.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY() + 1.5 / 40, loc.getZ()));
                    loc = as2.getLocation();
                    as2.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY() + 1.5 / 40, loc.getZ()));
                    loc = as3.getLocation();
                    as3.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY() + 1.5 / 40, loc.getZ()));
                } else if (cnt > 40) {
                    switch (side) {
                        case "EAST":
                            as1.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY() + 0.5 / 20, loc.getZ() + 0.05, loc.getYaw() + (float) 360 / 20, 0));
                            loc = as2.getLocation();
                            as2.teleport(new Location(loc.getWorld(), loc.getX() - 0.05, loc.getY() + 0.5 / 20, loc.getZ(), loc.getYaw() + (float) 270 / 20, 0));
                            loc = as3.getLocation();
                            as3.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY() + 0.5 / 20, loc.getZ() - 0.05, loc.getYaw() - (float) 360 / 20, 0));
                            loc = as4.getLocation();
                            as4.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY() + 0.3 / 20, loc.getZ(), 90, 0));
                            break;
                        case "WEST":
                            as1.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY() + 0.5 / 20, loc.getZ() - 0.05, loc.getYaw() + (float) 360 / 20, 0));
                            loc = as2.getLocation();
                            as2.teleport(new Location(loc.getWorld(), loc.getX() + 0.05, loc.getY() + 0.5 / 20, loc.getZ(), loc.getYaw() + (float) 270 / 20, 0));
                            loc = as3.getLocation();
                            as3.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY() + 0.5 / 20, loc.getZ() + 0.05, loc.getYaw() - (float) 360 / 20, 0));
                            loc = as4.getLocation();
                            as4.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY() + 0.3 / 20, loc.getZ(), 90, 0));
                            break;
                        case "NORTH":
                            as1.teleport(new Location(loc.getWorld(), loc.getX() + 0.05, loc.getY() + 0.5 / 20, loc.getZ(), loc.getYaw() + (float) 360 / 20, 0));
                            loc = as2.getLocation();
                            as2.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY() + 0.5 / 20, loc.getZ() + 0.05, loc.getYaw() + (float) 270 / 20, 0));
                            loc = as3.getLocation();
                            as3.teleport(new Location(loc.getWorld(), loc.getX() - 0.05, loc.getY() + 0.5 / 20, loc.getZ(), loc.getYaw() - (float) 360 / 20, 0));
                            loc = as4.getLocation();
                            as4.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY() + 0.3 / 20, loc.getZ()));
                            break;
                        default:
                            as1.teleport(new Location(loc.getWorld(), loc.getX() - 0.05, loc.getY() + 0.5 / 20, loc.getZ(), loc.getYaw() + (float) 360 / 20, 0));
                            loc = as2.getLocation();
                            as2.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY() + 0.5 / 20, loc.getZ() - 0.05, loc.getYaw() + (float) 270 / 20, 0));
                            loc = as3.getLocation();
                            as3.teleport(new Location(loc.getWorld(), loc.getX() + 0.05, loc.getY() + 0.5 / 20, loc.getZ(), loc.getYaw() - (float) 360 / 20, 0));
                            loc = as4.getLocation();
                            as4.teleport(new Location(loc.getWorld(), loc.getX(), loc.getY() + 0.3 / 20, loc.getZ()));
                            break;
                    }
                }
                if (cnt == 40) {
                    as1.remove();
                    as2.remove();
                    as3.remove();
                    as4.getEquipment().setHelmet(staffOfLight);
                    loc = as4.getLocation();
                    Firework fw;
                    if (side.equals("EAST") || side.equals("WEST"))
                        fw = (Firework) world.spawnEntity(new Location(world, loc.getX() + 0.2, loc.getY() + 2.4, loc.getZ() - 0.55), EntityType.FIREWORK);
                    else
                        fw = (Firework) world.spawnEntity(new Location(world, loc.getX() - 0.55, loc.getY() + 2.4, loc.getZ() - 0.55), EntityType.FIREWORK);
                    FireworkMeta fm = fw.getFireworkMeta();
                    FireworkEffect fe = FireworkEffect.builder().trail(true).flicker(true).withColor(List.of(Color.fromRGB(255, 131, 0), Color.fromRGB(197, 0, 23))).build();
                    fm.addEffect(fe);
                    fw.setFireworkMeta(fm);
                    for (Player player1: world.getNearbyPlayers(fw.getLocation(), 20)) {
                        player1.stopSound(Sound.ENTITY_FIREWORK_ROCKET_BLAST);
                    }
                    fw.setSilent(true);
                    fw.detonate();
                    PlaySound(as4.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 10);
                }
                if (cnt == 0) {
                    loc = as4.getLocation();
                    as4.remove();
                    world.dropItem(new Location(world, loc.getX(), loc.getY() + 1.5, loc.getZ()), staffOfLight);
                    task.cancel();
                }
            }, 0L, 1L);
        }
    }
}
