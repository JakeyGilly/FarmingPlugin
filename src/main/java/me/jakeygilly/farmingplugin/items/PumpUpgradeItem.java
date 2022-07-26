package me.jakeygilly.farmingplugin.items;

import me.jakeygilly.farmingplugin.utils.Rarity;
import me.jakeygilly.farmingplugin.utils.UpgradeItem;
import me.jakeygilly.farmingplugin.utils.UpgradeType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.*;

public class PumpUpgradeItem extends UpgradeItem {
    public PumpUpgradeItem(int level) {
        super(
                Material.BOOK,
                1,
                "Pumpkin Axe Upgrade",
                new ArrayList<String>() {{
                    add(ChatColor.GRAY + "Use this to upgrade your Pumpkin Axe.");
                    add(ChatColor.GRAY + "It can increase the amount of pumpkins you can harvest.");
                }},
                new ArrayList<ItemFlag>() {{
                    add(ItemFlag.HIDE_ENCHANTS);
                    add(ItemFlag.HIDE_ATTRIBUTES);
                }},
                new HashMap<Enchantment, Integer>() {{
                    put(Enchantment.MENDING, 1);
                }},
                0,
                false,
                Rarity.UNCOMMON,
                UpgradeType.PUMPUPGRADE,
                level
        );
    }
}
