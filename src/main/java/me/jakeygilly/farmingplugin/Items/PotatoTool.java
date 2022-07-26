package me.jakeygilly.farmingplugin.Items;

import me.jakeygilly.farmingplugin.utils.FarmingTool;
import me.jakeygilly.farmingplugin.utils.Item;
import me.jakeygilly.farmingplugin.utils.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class PotatoTool extends FarmingTool {
    public PotatoTool() {
        super(
                Material.GOLDEN_HOE,
                1,
                ChatColor.AQUA + "Potato Tool",
                new ArrayList<String>() {{
                    add(ChatColor.GRAY + "A tool for harvesting potatoes.");
                }},
                new ArrayList<ItemFlag>() {{
                    add(ItemFlag.HIDE_ENCHANTS);
                    add(ItemFlag.HIDE_ATTRIBUTES);
                    add(ItemFlag.HIDE_UNBREAKABLE);
                }},
                new HashMap<Enchantment, Integer>() {{
                    put(Enchantment.MENDING, 1);
                }},
                0,
                true,
                Rarity.UNCOMMON,
                0,
                20
        );
    }

    @Override
    public void onEquip(Player player) {

    }

    @Override
    public void onUnequip(Player player) {

    }

    @Override
    public void clickEntity(Player player, Entity target, boolean shifting) {

    }

    @Override
    public void punchEntity(Player player, Entity target, double damage, boolean shifting) {

    }

    @Override
    public void leftClickOnBlock(Player player, Block block, boolean shifting) {

    }

    @Override
    public void leftClickOnAir(Player player, Block block, boolean shifting) {

    }

    @Override
    public void rightClickOnBlock(Player player, Block block, boolean shifting) {

    }

    @Override
    public void rightClickOnAir(Player player, Block block, boolean shifting) {

    }

    @Override
    public void onBlockBreak(Player player, Block block, boolean sneaking) {

    }

    @Override
    public void onDrop(Player player) {

    }

    @Override
    public void onPickup(Player player) {

    }

    @Override
    public void onUpgrade(Player player, ItemStack upgradeItem, int upgradeLevel) {

    }
}
