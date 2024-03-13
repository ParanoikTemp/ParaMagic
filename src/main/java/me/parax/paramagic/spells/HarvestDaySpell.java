package me.parax.paramagic.spells;

import me.parax.paramagic.utils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.checkerframework.checker.units.qual.A;

import java.util.List;

public class HarvestDaySpell implements Listener {
    private final Server server;
    private final Plugin plugin;

    public HarvestDaySpell(Server server, Plugin plugin) {
        this.server = server;
        this.plugin = plugin;
    }

//    @EventHandler  Тут код на случай если забуду как менять рост блока
//    public void test(PlayerInteractEvent event) {
//        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.GRASS_BLOCK) {
//            Block b = event.getClickedBlock();
//            Block block = b.getWorld().getBlockAt(new Location(b.getWorld(), b.getX(), b.getY() + 1, b.getZ()));
//            block.setType(Material.WHEAT);
//            event.getPlayer().sendMessage(block.getType().name());
//            BlockData bd = block.getBlockData();
//            Ageable age = (Ageable) bd;
//            age.setAge(3);
//            block.setBlockData(age);
//        }
//    }

    @EventHandler
    public void harvestDay(AsyncPlayerChatEvent event) {
        if (event.getMessage().equalsIgnoreCase("О боги земли, да наступит день жатвы, да соберется урожай. Примите мою душу, да будет счастье земле сей")) {
            server.getScheduler().runTask(plugin, () -> {
                event.setCancelled(true);
                Player player = event.getPlayer();
                player.sendMessage(utils.ct("&7Вы использовали заклинание"));
                Location loc = player.getLocation();
                World world = player.getWorld();
                Block block = world.getBlockAt(loc);
                if (player.getTotalExperience() < 2) {
                    player.sendMessage(utils.ct("&6[&eБоги отказали вам, так как у вас недостаточно души.&6]"));
                    return;
                }
                if (block.getType() == Material.FARMLAND) {
                    double x = loc.getX();
                    double y = loc.getY() + 0.2;
                    double z = loc.getZ();
                    int count = 0;
                    for (double i = -5; i < 6; ++i) {
                        for (double j = -5; j < 6; ++j) {
                            if (player.getTotalExperience() < 2)
                                return;
                            block = world.getBlockAt(new Location(world, x + i, y, z + j));
                            if ((block.getType() == Material.WHEAT || block.getType() == Material.CARROTS || block.getType() == Material.POTATOES) && ((Ageable) block.getBlockData()).getAge() == 7 && player.getTotalExperience() >= 2) {
                                player.giveExp(-2);
                                List<ItemStack> drops = (List<ItemStack>) block.getDrops();
                                for (ItemStack drop : drops) {
                                    if (drop.getType() == Material.WHEAT_SEEDS || drop.getType() == Material.CARROT || drop.getType() == Material.POTATO) {
                                        if (drop.getAmount() > 1)
                                            drop.subtract();
                                        else
                                            continue;
                                    }
                                    world.dropItem(new Location(world, x + i + 0.5, y, z + j + 0.5), drop);
                                }
                                ++count;
                                block.setType(block.getBlockData().getMaterial());
                            } else if (block.getType() == Material.BEETROOTS && ((Ageable) block.getBlockData()).getAge() == 3 && player.getTotalExperience() >= 2) {
                                player.giveExp(-2);
                                List<ItemStack> drops = (List<ItemStack>) block.getDrops();
                                for (ItemStack drop : drops) {
                                    if (drop.getType() == Material.BEETROOT_SEEDS) {
                                        if (drop.getAmount() > 1)
                                            drop.subtract();
                                        else
                                            continue;
                                    }
                                    world.dropItem(new Location(world, x + i + 0.5, y, z + j + 0.5), drop);
                                }
                                ++count;
                                block.setType(block.getBlockData().getMaterial());
                            } else if ((block.getType() == Material.ATTACHED_MELON_STEM || block.getType() == Material.ATTACHED_PUMPKIN_STEM) && block.getBlockData() instanceof Directional && player.getTotalExperience() >= 2) {
                                Directional directional = (Directional) block.getBlockData();
                                player.giveExp(-2);
                                Block block2;
                                if (directional.getFacing() == BlockFace.EAST)
                                     block2 = world.getBlockAt(new Location(world, block.getX() + 1, block.getY(), block.getZ()));
                                else if (directional.getFacing() == BlockFace.WEST)
                                     block2 = world.getBlockAt(new Location(world, block.getX() - 1, block.getY(), block.getZ()));
                                else if (directional.getFacing() == BlockFace.NORTH)
                                    block2 = world.getBlockAt(new Location(world, block.getX(), block.getY(), block.getZ() - 1));
                                else
                                    block2 = world.getBlockAt(new Location(world, block.getX(), block.getY(), block.getZ() + 1));
                                if (block2.getBlockData().getMaterial() == Material.PUMPKIN || block2.getBlockData().getMaterial() == Material.MELON)
                                    block2.breakNaturally();
                            }
                        }
                    }
                    if (count > 0)
                        player.sendMessage(utils.ct("&6[&eБоги земли вас услышали.&6]"));
                    else {
                        player.sendMessage(utils.ct("&6[&eБоги земли пришли на ваш зов, но оказались обмануты. В отместку они забрали у вас часть души&6]"));
                        if (player.getTotalExperience() < 50)
                            player.setExp(0);
                        else
                            player.giveExp(-50);
                    }
                } else {
                    player.sendMessage(utils.ct("&6[&eБоги земли вас не услышали. Вы должны стоять на пашне для использования этого заклинания&6]"));
                }
            });
        }
    }
}
