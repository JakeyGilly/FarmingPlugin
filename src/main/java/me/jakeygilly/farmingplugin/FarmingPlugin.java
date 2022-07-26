package me.jakeygilly.farmingplugin;

import me.jakeygilly.farmingplugin.CommandCompleter.GiveItemCompleter;
import me.jakeygilly.farmingplugin.Commands.GiveItem;
import me.jakeygilly.farmingplugin.utils.ItemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FarmingPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("giveitem").setExecutor(new GiveItem());
        getCommand("giveitem").setTabCompleter(new GiveItemCompleter());
        ItemManager.init();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
