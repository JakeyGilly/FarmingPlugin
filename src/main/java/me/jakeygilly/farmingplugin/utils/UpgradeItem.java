package me.jakeygilly.farmingplugin.utils;

import me.jakeygilly.farmingplugin.FarmingPlugin;
import me.jakeygilly.farmingplugin.builders.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class UpgradeItem extends Item {
    Rarity rarity;
    int upgradeLevel;
    UpgradeType upgradeType;
    static List<UpgradeItem> upgradeItems = new ArrayList<>();
    public UpgradeItem(Material material, int amount, String name, List<String> lore, List<ItemFlag> itemFlags, Map<Enchantment, Integer> enchantmentLevels, int durability, boolean unbreakable, Rarity rarity, UpgradeType upgradeType, int upgradeLevel) {
        super(material, amount, name, lore, itemFlags, enchantmentLevels, durability, unbreakable);
        this.rarity = rarity;
        this.upgradeType = upgradeType;
        this.upgradeLevel = upgradeLevel;
        upgradeItems.add(this);
    }
    public Rarity getRarity() {
        return rarity;
    }
    public void setRarity(Rarity rarity) { this.rarity = rarity; }
    public int getUpgradeLevel() { return upgradeLevel; }
    public void setUpgradeLevel(int upgradeLevel) {  this.upgradeLevel = upgradeLevel; }
    public UpgradeType getUpgradeType() { return upgradeType; }
    public void setUpgradeType(UpgradeType upgradeType) { this.upgradeType = upgradeType; }

    @Override
    public void give(Player player) {
        ItemStack item = this.getItem();
        player.getInventory().addItem(item);
    }

    @Override
    public ItemStack getItem() {
        super.getItem();
        ItemBuilder itemBuilder = new ItemBuilder(this.getMaterial(), this.getAmount());
        if (this.getName() != null) itemBuilder.setName(this.getFormattedName());
        if (this.getLore() != null) itemBuilder.setLore(this.getFormattedLore());
        itemBuilder.addLoreLine("");
        itemBuilder.addLoreLine(String.format("%sThis is a level %d upgrade.", ChatColor.GRAY, this.getUpgradeLevel()));
        itemBuilder.addLoreLine("");
        if (this.getRarity() != null) {
            StringBuilder s = new StringBuilder();
            for (ChatColor colour : this.getRarity().getColor()) s.append(colour);
            itemBuilder.addLoreLine(String.format("%s%s UPGRADE", s, this.getRarity().name()));
        }
        if (this.getDurability() > 0) itemBuilder.setDurability((short) this.getDurability());
        if (this.isUnbreakable()) itemBuilder.setUnbreakable(true);
        if (this.getItemFlags() != null && this.getItemFlags().size() > 0)
            itemBuilder.addItemFlags(this.getItemFlags());
        if (this.getEnchantmentLevels().size() > 0)
            this.getEnchantmentLevels().forEach(itemBuilder::setEnchantment);
        ItemStack item = itemBuilder.build();
        NamespacedKey key = new NamespacedKey(FarmingPlugin.getPlugin(FarmingPlugin.class), "upgradeitem");
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return null;
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(key, PersistentDataType.STRING, itemMeta.getDisplayName());
        item.setItemMeta(itemMeta);
        return itemBuilder.build();
    }

    public static UpgradeItem getUpgradeItem(ItemStack item) {
        NamespacedKey key = new NamespacedKey(FarmingPlugin.getPlugin(FarmingPlugin.class), "upgradeitem");
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return null;
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        if (!container.has(key, PersistentDataType.STRING)) return null;
        String name = container.get(key, PersistentDataType.STRING);
        for (UpgradeItem upgradeItem : upgradeItems) {
            if (upgradeItem.getFormattedName().equals(name)) return upgradeItem;
        }
        return null;
    }

    public static boolean isUpgradeItem(ItemStack item) {
        NamespacedKey k = new NamespacedKey(FarmingPlugin.getPlugin(FarmingPlugin.class), "upgradeitem");
        if (item == null) return false;
        if (!item.hasItemMeta()) return false;
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return false;
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        return container.has(k, PersistentDataType.STRING);
    }

    public static void setUpgradeItems(List<UpgradeItem> upgradeItems) {
        UpgradeItem.upgradeItems = upgradeItems;
    }

    public static List<UpgradeItem> getUpgradeItems() {
        return UpgradeItem.upgradeItems;
    }

}

