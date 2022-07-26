package me.jakeygilly.farmingplugin.CommandCompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class GiveItemCompleter implements TabCompleter {
    List<String> items = new ArrayList<String>() {{
        add("carrottool");
        add("potatotool");
        add("wheattool");
        add("melonaxe");
        add("pumpkinaxe");
    }};
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
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
