package me.parax.paramagic.spells;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class HarvestDaySpell implements Listener {
    private final Server server;
    private final Plugin plugin;

    public HarvestDaySpell(Server server, Plugin plugin) {
        this.server = server;
        this.plugin = plugin;
    }

    private static Component makeText(String text) {
        return Component.text("[", TextColor.color(255, 168, 0)).append(Component.text(text, TextColor.color(255, 241, 0))).append(Component.text("]", TextColor.color(255, 168, 0)));
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        if (PlainTextComponentSerializer.plainText().serialize(event.message()).equalsIgnoreCase("О боги земли, да наступит день жатвы, да соберется урожай. Примите мою душу, да будет счастье земле сей")) {
            event.setCancelled(true);
            Player player = event.getPlayer();
            server.getScheduler().runTask(plugin, () -> {
                Location loc = player.getLocation();
                World world = player.getWorld();
                Block block = world.getBlockAt(loc);
                player.sendMessage(Component.text("Вы использовали заклинание", TextColor.color(152, 152, 152)));
                if (player.getTotalExperience() < 2) {
                    player.sendMessage(makeText("Боги отказали вам, так как у вас недостаточно души."));
                    return;
                }
                if (block.getType() == Material.FARMLAND) {
                    List<Block> blocks = new ArrayList<>();
                    List<Block> replant = new ArrayList<>();
                    List<Block> destroy = new ArrayList<>();
                    List<ItemStack> items = new ArrayList<>();
                    double x = loc.getX();
                    double y = loc.getY() + 0.2;
                    double z = loc.getZ();
                    for (double i = -5; i < 6; ++i)
                        for (double j = -5; j < 6; ++j)
                            blocks.add(world.getBlockAt(new Location(world, x + i, y, z + j)));
                    int xp = player.getTotalExperience();
                    int finalXp = xp;
                    server.getScheduler().runTaskAsynchronously(plugin, () -> {
                        int exp = finalXp;
                        for (Block b : blocks) {
                            if (exp < 2) break;
                            if (((b.getType() == Material.WHEAT || b.getType() == Material.CARROTS || b.getType() == Material.POTATOES || b.getType() == Material.BEETROOT) && ((Ageable) b.getBlockData()).getAge() == 7) || (b.getType() == Material.BEETROOTS && ((Ageable) b.getBlockData()).getAge() == 3)) {
                                replant.add(b);
                                for (ItemStack drop : b.getDrops()) {
                                    for (ItemStack item : items) {
                                        if (item.getType() == drop.getType()) {
                                            if ((drop.getType() == Material.WHEAT_SEEDS || drop.getType() == Material.CARROT || drop.getType() == Material.POTATO || drop.getType() == Material.BEETROOT_SEEDS) && drop.getAmount() > 1)
                                                    drop.subtract();
                                            item.add(drop.getAmount());
                                            break;
                                        }
                                    }
                                    items.add(drop);
                                }
                                exp -= 2;
                            } else if ((b.getType() == Material.ATTACHED_MELON_STEM || b.getType() == Material.ATTACHED_PUMPKIN_STEM) && b.getBlockData() instanceof Directional) {
                                destroy.add(b);
                                exp -= 2;
                            }
                        }
                        doSpell(player, replant, destroy, items);
                    });
                } else {
                    event.getPlayer().sendMessage(makeText("Боги земли вас не услышали. Вы должны стоять на пашне для использования этого заклинани"));
                }
            });
        }
    }

    private void doSpell(Player player, List<Block> replant, List<Block> destroy, List<ItemStack> items) {
        server.getScheduler().runTask(plugin, () -> {
            int count = 0;
            int xp = player.getTotalExperience();
            World world = player.getWorld();
            for (Block b : replant) {
                ++count;
                b.setType(b.getBlockData().getMaterial());
                xp -= 2;
            }
            for (Block block : destroy) {
                Directional directional = (Directional) block.getBlockData();
                xp -= 2;
                ++count;
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
                    for (ItemStack drop : block2.getDrops()) {
                        for (ItemStack item : items) {
                            if (item.getType() == drop.getType()) {
                                item.add(drop.getAmount());
                                break;
                            }
                        }
                        items.add(drop);
                    }
            }
            if (count > 0) {
                player.sendMessage(makeText("Боги земли вас услышали."));
                for (ItemStack item : items)
                    world.dropItem(player.getLocation(), item);
                player.setTotalExperience(xp);
            } else {
                player.sendMessage(makeText("Боги земли пришли на ваш зов, но оказались обмануты. В отместку они забрали у вас часть души"));
                if (player.getTotalExperience() < 50)
                    player.setTotalExperience(0);
                else
                    player.giveExp(-50);
            }
        });
    }
}
