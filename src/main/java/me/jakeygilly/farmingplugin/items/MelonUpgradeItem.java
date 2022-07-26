package me.jakeygilly.farmingplugin.items;

import me.jakeygilly.farmingplugin.items.farmingitems.MelonAxe;
import me.jakeygilly.farmingplugin.utils.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.HashMap;

public class MelonUpgradeItem extends UpgradeItem {
    public MelonUpgradeItem(int level) {
        super(
                Material.BOOK,
                1,
                "Melon Axe Upgrade",
                new ArrayList<String>() {{
                    add(ChatColor.GRAY + "Use this to upgrade your Melon Axe.");
                    add(ChatColor.GRAY + "It can increase the amount of melons you can harvest.");
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
                UpgradeType.MELONUPGRADE,
                level
        );
    }
}
