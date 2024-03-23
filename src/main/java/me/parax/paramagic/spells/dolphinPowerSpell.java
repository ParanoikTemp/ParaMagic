package me.parax.paramagic.spells;


import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class dolphinPowerSpell implements Listener {

    private final Server server;
    private final Plugin plugin;

    public dolphinPowerSpell(Server server, Plugin plugin) {
        this.server = server;
        this.plugin = plugin;
    }


    private static Component makeText(String text) {
        return Component.text("[", TextColor.color(0, 170, 170)).append(Component.text(text, TextColor.color(83, 255, 255))).append(Component.text("]", TextColor.color(0, 170, 170)));
    }

    @EventHandler
    public void getDolphinPower(AsyncChatEvent event) {
        if (PlainTextComponentSerializer.plainText().serialize(event.message()).equalsIgnoreCase("Сила океана, возьми мою душу, придай мне силы стать с тобой единым"))
        {
            event.setCancelled(true);
            Player player = event.getPlayer();
            Location loc = player.getLocation();
            World world = player.getWorld();
            player.sendMessage(Component.text("Вы использовали заклинание", TextColor.color(152, 152, 152)));
            if (world.getBlockAt(loc).getType() != Material.WATER && world.getBlockAt(new Location(loc.getWorld(), loc.getX(), loc.getY() + 1, loc.getZ())).getType() != Material.WATER) {
                player.sendMessage(makeText("Море вас не слышит. Коснитесь воды для общения с морем"));
            } else if (player.getTotalExperience() >= 50) {
                server.getScheduler().runTask(plugin, () -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 600, 0, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 600, 2, false, false));
                    player.playSound(player.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_AMBIENT, 1.0f, 1.0f);
                    player.giveExp(-50);
                });
                player.sendMessage(makeText("Море услышало вашу просьбу"));
            } else {
                player.sendMessage(makeText("Море отказало вам в вашей просьбе"));
            }
        }
    }
}
