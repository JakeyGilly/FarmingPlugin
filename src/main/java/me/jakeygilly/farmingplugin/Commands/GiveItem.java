package me.jakeygilly.farmingplugin.Commands;

import me.jakeygilly.farmingplugin.Items.*;
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
        switch (item) {
            case "carrottool":
                new CarrotTool().give(player);
                player.sendMessage("You have been given a Carrot Tool.");
                return true;
            case "potatotool":
                new PotatoTool().give(player);
                player.sendMessage("You have been given a Potato Tool.");
                return true;
            case "wheattool":
                new WheatTool().give(player);
                player.sendMessage("You have been given a Wheat Tool.");
                return true;
            case "melonaxe":
                new MelonAxe().give(player);
                player.sendMessage("You have been given a Melon Axe.");
                return true;
            case "pumpkinaxe":
                new PumpkinAxe().give(player);
                player.sendMessage("You have been given a Pumpkin Axe.");
                return true;
        }
        player.sendMessage(String.format("%sUnknown item: %s", ChatColor.RED, item));
        return true;
    }
}
