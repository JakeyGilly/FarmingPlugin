package me.jakeygilly.farmingplugin.commands;

import me.jakeygilly.farmingplugin.items.MelonUpgradeItem;
import me.jakeygilly.farmingplugin.items.PumpUpgradeItem;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class GiveUpgradeItem implements CommandExecutor {
    @Override
    public boolean onCommand(org.bukkit.command.CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;
        if (args.length < 1) {
            player.sendMessage("/giveupgradeitem <item>");
            return true;
        }
        String item = args[0];
        int level;
        try {
            level = Integer.parseInt(args[1]);
        } catch (Exception e) {
            level = 0;
        }
        switch (item) {
            case "pumpkin":
                new PumpUpgradeItem(level).give(player);
                player.sendMessage("You have been given a Pumpkin Tool Upgrade.");
                return true;
            case "melon":
                new MelonUpgradeItem(level).give(player);
                player.sendMessage("You have been given a Melon Tool Upgrade.");
                return true;
        }
        player.sendMessage(String.format("%sUnknown item: %s", ChatColor.RED, item));
        return true;
    }
}

