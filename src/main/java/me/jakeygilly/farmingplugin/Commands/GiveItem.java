package me.jakeygilly.farmingplugin.Commands;

import me.jakeygilly.farmingplugin.utils.ItemManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class GiveItem implements CommandExecutor {
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        if (args.length < 1) {
            player.sendMessage("/giveitem <item>");
            return true;
        }
        String item = args[0];
        if (item.equals("wand")) {
            player.getInventory().addItem(ItemManager.wand);
            player.sendMessage("You have been given a wand!");
            return true;
        }
        player.sendMessage(String.format("%sUnknown item: %s", ChatColor.RED, item));
        return true;
    }
}
