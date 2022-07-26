package me.jakeygilly.farmingplugin.utils;

import me.jakeygilly.farmingplugin.builders.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Item {
    private Material material;
    private int amount;
    private List<ItemFlag> itemFlags;
    private Map<Enchantment, Integer> enchantmentLevels;
    private List<String> lore;
    private String name;
    private int durability;
    private boolean unbreakable;

    public Item(Material material, int amount, String name, List<String> lore, List<ItemFlag> itemFlags, Map<Enchantment, Integer> enchantmentLevels, int durability, boolean unbreakable) {
        this.material = material;
        this.amount = amount;
        this.itemFlags = itemFlags;
        this.enchantmentLevels = enchantmentLevels;
        this.lore = lore;
        this.name = name;
        this.durability = durability;
        this.unbreakable = unbreakable;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<ItemFlag> getItemFlags() {
        return itemFlags;
    }

    public void setItemFlags(List<ItemFlag> itemFlags) {
        this.itemFlags = itemFlags;
    }

    public Map<Enchantment, Integer> getEnchantmentLevels() {
        return enchantmentLevels;
    }

    public void setEnchantmentLevels(Map<Enchantment, Integer> enchantmentLevels) {
        this.enchantmentLevels = enchantmentLevels;
    }

    public List<String> getLore() {
        return lore;
    }

    public List<String> getFormattedLore() {
        List<String> formattedLore = new ArrayList<>();
        for (String lore: this.lore) {
            formattedLore.add(ChatColor.translateAlternateColorCodes('&', lore));
        }
        return formattedLore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public String getName() {
        return name;
    }

    public String getFormattedName() {
        return ChatColor.translateAlternateColorCodes('&', name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public boolean isUnbreakable() {
        return unbreakable;
    }

    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
    }

    public void give(Player player) {
        player.getInventory().addItem(this.getItem());
    }

    public ItemStack getItem() {
        ItemBuilder itemBuilder = new ItemBuilder(this.material, this.amount);
        if (this.name != null) itemBuilder.setName(ChatColor.translateAlternateColorCodes('&', this.name));
        if (this.lore != null) {
            List<String> formattedLore = new ArrayList<>();
            for (String s : this.lore) {
                formattedLore.add(ChatColor.translateAlternateColorCodes('&', s));
            }
            itemBuilder.setLore(formattedLore);
            this.setLore(formattedLore);
        }
        if (this.durability > 0) itemBuilder.setDurability((short) this.durability);
        if (this.unbreakable) itemBuilder.setUnbreakable(true);
        if (this.itemFlags != null && this.itemFlags.size() > 0) itemBuilder.addItemFlags(this.itemFlags);
        if (this.enchantmentLevels.size() > 0) this.enchantmentLevels.forEach(itemBuilder::setEnchantment);
        itemBuilder.setAmount(1);
        return itemBuilder.build();
    }
}
