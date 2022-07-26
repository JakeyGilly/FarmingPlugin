package me.jakeygilly.farmingplugin.utils;

import me.jakeygilly.farmingplugin.Items.CarrotTool;
import org.bukkit.inventory.ItemStack;

public class ItemManager {
    public static ItemStack carrotTool;
    public static void init() {
        carrotTool =  CarrotTool.createCarrotTool();
    }

}
