package me.parax.paramagic;

import me.parax.paramagic.altars.staffOfLightCraft;
import me.parax.paramagic.commands.getStaff;
import me.parax.paramagic.commands.getStaffCompleter;
import me.parax.paramagic.commands.manaCMD;
import me.parax.paramagic.commands.testCMD;
import me.parax.paramagic.magic.smelting;
import me.parax.paramagic.spells.HarvestDaySpell;
import me.parax.paramagic.spells.dolphinPowerSpell;
import me.parax.paramagic.staffs.staffOfLight;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class ParaMagic extends JavaPlugin {
    private final Logger log = this.getLogger();
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new smelting(), this);  // АвтоПлавка

        getServer().getPluginManager().registerEvents(new dolphinPowerSpell(getServer(), this), this);  // Заклинание силы дельфина
        getServer().getPluginManager().registerEvents(new HarvestDaySpell(getServer(), this), this);  // Заклинание автофермы

        getServer().getPluginManager().registerEvents(new staffOfLight(), this);  // Посох света
        getServer().getPluginManager().registerEvents(new staffOfLightCraft(this), this);

        getCommand("mana").setExecutor(new manaCMD());  // Команда информации о том, сколько у тебя маны
        getCommand("getstaff").setExecutor(new getStaff());  // Команда выдачи посоха
        getCommand("getstaff").setTabCompleter(new getStaffCompleter());

        getCommand("test").setExecutor(new testCMD());

        log.info("Плагин успешно запустился!");
    }

    @Override
    public void onDisable() {
        log.info("Плагин завершил работу!");
    }
}
