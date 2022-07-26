package me.jakeygilly.farmingplugin;

import me.jakeygilly.farmingplugin.commandcompleter.GiveItemCompleter;
import me.jakeygilly.farmingplugin.commandcompleter.GiveUpgradeItemCompleter;
import me.jakeygilly.farmingplugin.commands.GiveItem;
import me.jakeygilly.farmingplugin.commands.GiveUpgradeItem;
import me.jakeygilly.farmingplugin.listeners.FarmingToolListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class FarmingPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("giveitem").setExecutor(new GiveItem());
        getCommand("giveitem").setTabCompleter(new GiveItemCompleter());
        getCommand("giveupgradeitem").setExecutor(new GiveUpgradeItem());
        getCommand("giveupgradeitem").setTabCompleter(new GiveUpgradeItemCompleter());
        getServer().getPluginManager().registerEvents(new FarmingToolListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
