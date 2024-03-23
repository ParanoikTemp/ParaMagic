package me.parax.paramagic.commands;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;

public class getStaff implements CommandExecutor {
    public static ItemStack staffOfLightItem(Player player) {
        ItemStack staffOfLight = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta im = staffOfLight.getItemMeta();
        im.displayName(Component.text("Посох Света").color(TextColor.color(255, 255, 0)).decoration(TextDecoration.BOLD, true));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text("Одаряет мир светом там,").color(TextColor.color(255, 255, 255)));
        lore.add(Component.text("где прикажет его владелец.").color(TextColor.color(255, 255, 255)));
        lore.add(Component.text("Создан магом " + player.getName()).color(TextColor.color(153, 153, 153)));
        im.lore(lore);
        im.getPersistentDataContainer().set(NamespacedKey.fromString("paramagic"), PersistentDataType.BOOLEAN, true);
        im.getPersistentDataContainer().set(NamespacedKey.fromString("staff"), PersistentDataType.STRING, "staffOfLight");
        im.getPersistentDataContainer().set(NamespacedKey.fromString("owner"), PersistentDataType.STRING, player.getName());
        staffOfLight.setItemMeta(im);
        return staffOfLight;
    }

    public static ItemStack shaftOfSilenceItem() {
        ItemStack shaftOfSilence = new ItemStack(Material.BLAZE_ROD);
        ItemMeta im = shaftOfSilence.getItemMeta();
        im.displayName(Component.text("Древко посоха тишины").color(TextColor.color(209, 214, 182)));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text("Является основой для").color(TextColor.color(255, 255, 255)));
        lore.add(Component.text("могущественного посоха").color(TextColor.color(255, 255, 255)));
        lore.add(Component.text("тишины.").color(TextColor.color(255, 255, 255)));
        im.lore(lore);
        im.getPersistentDataContainer().set(NamespacedKey.fromString("paramagic"), PersistentDataType.BOOLEAN, true);
        im.getPersistentDataContainer().set(NamespacedKey.fromString("craftitem"), PersistentDataType.STRING, "shaftOfSilence");
        shaftOfSilence.setItemMeta(im);
        return shaftOfSilence;
    }

    public static ItemStack staffOfSilenceItem(Player player) {
        ItemStack staffOfSilence = new ItemStack(Material.STONE_SWORD);
        ItemMeta im = staffOfSilence.getItemMeta();
        im.displayName(Component.text("Посох Тишины").color(TextColor.color(41, 223, 235)).decoration(TextDecoration.BOLD, true));
        ArrayList<Component> lore = new ArrayList<>();
        lore.add(Component.text("Опасный посох, созданный").color(TextColor.color(255, 255, 255)));
        lore.add(Component.text("с помощью сил тишины.").color(TextColor.color(255, 255, 255)));
        lore.add(Component.text("Создан магом " + player.getName()).color(TextColor.color(153, 153, 153)));
        im.lore(lore);
        im.getPersistentDataContainer().set(NamespacedKey.fromString("paramagic"), PersistentDataType.BOOLEAN, true);
        im.getPersistentDataContainer().set(NamespacedKey.fromString("staff"), PersistentDataType.STRING, "staffOfSilence");
        im.getPersistentDataContainer().set(NamespacedKey.fromString("owner"), PersistentDataType.STRING, player.getName());
        staffOfSilence.setItemMeta(im);
        return staffOfSilence;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player) {
            if (strings[0].equals("staffOfLight")) {
                ((Player) commandSender).getInventory().addItem(staffOfLightItem((Player) commandSender));
                return true;
            } else if (strings[0].equals("shaftOfSilence")) {
                ((Player) commandSender).getInventory().addItem(shaftOfSilenceItem());
                return true;
            } else if (strings[0].equals("staffOfSilence")) {
                ((Player) commandSender).getInventory().addItem(staffOfSilenceItem((Player) commandSender));
                return true;
            } else return false;
        } else {
            commandSender.sendMessage("Данная команда только для игроков!");
            return true;
        }
    }
}
