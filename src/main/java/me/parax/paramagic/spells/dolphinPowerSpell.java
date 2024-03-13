package me.parax.paramagic.spells;


import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
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

    @EventHandler
    public void getDolphinPower(AsyncPlayerChatEvent event) {
        if (event.getMessage().equalsIgnoreCase("Сила океана, возьми мою душу, придай мне силы стать с тобой единым"))
        {
            event.setCancelled(true);
            Player player = event.getPlayer();
            Location loc = player.getLocation();
            World world = player.getWorld();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Вы использовали заклинание"));
            if (world.getBlockAt(loc).getType() != Material.WATER) {
                String coloredMessage = ChatColor.translateAlternateColorCodes('&', "&3[&bМоре вас не слышит. Коснитесь воды для общения с морем&3]");
                player.sendMessage(coloredMessage);
            } else if (player.getTotalExperience() >= 50) {
                String coloredMessage = ChatColor.translateAlternateColorCodes('&', "&3[&bМоре услышало вашу просьбу&3]");
                server.getScheduler().runTask(plugin, () -> {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 600, 1, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 600, 1, false, false));
                    player.playSound(player.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_AMBIENT, 1.0f, 1.0f);
                    player.sendMessage(coloredMessage);
                    player.giveExp(-50);
                });
            } else {
                String coloredMessage = ChatColor.translateAlternateColorCodes('&', "&3[&bМоре отказало вам в вашей просьбе&3]");
                server.getScheduler().runTask(plugin, () -> {
                    player.sendMessage(coloredMessage);
                });
            }
        }
    }
}
