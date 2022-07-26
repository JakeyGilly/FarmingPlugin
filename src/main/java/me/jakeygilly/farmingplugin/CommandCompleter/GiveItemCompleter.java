package me.jakeygilly.farmingplugin.CommandCompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class GiveItemCompleter implements TabCompleter {
    List<String> items;
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (items.isEmpty()) {
            items.add("wand");
        }
        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String item : items) {
                if (item.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(item);
                }
            }
            return result;
        }
        return null;
    }
}
