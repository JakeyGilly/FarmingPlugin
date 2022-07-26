package me.jakeygilly.farmingplugin.utils;

import me.jakeygilly.farmingplugin.FarmingPlugin;
import me.jakeygilly.farmingplugin.builders.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class FarmingTool extends Item {
    Rarity rarity;
    int currentUpgrade;
    int upgrades;
    static List<FarmingTool> farmingTools = new ArrayList<>();
    public FarmingTool(Material material, int amount, String name, List<String> lore, List<ItemFlag> itemFlags, Map<Enchantment, Integer> enchantmentLevels, int durability, boolean unbreakable, Rarity rarity, int currentUpgrade, int upgrades) {
        super(material, amount, name, lore, itemFlags, enchantmentLevels, durability, unbreakable);
        this.rarity = rarity;
        this.currentUpgrade = currentUpgrade;
        this.upgrades = upgrades;
        farmingTools.add(this);
    }
    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) { this.rarity = rarity; }

    public int getUpgrades() { return upgrades; }

    public void setUpgrades(int upgrades) { this.upgrades = upgrades; }
    public int getCurrentUpgrade() { return currentUpgrade; }
    public void setCurrentUpgrade(int currentUpgrade) { this.currentUpgrade = currentUpgrade; }


    public abstract void onEquip(Player player);
    public abstract void onUnequip(Player player);
    public abstract void clickEntity(Player player, Entity target, boolean shifting);
    public abstract void punchEntity(Player player, Entity target, double damage, boolean shifting);
    public abstract void leftClickOnBlock(Player player, Block block, boolean shifting);
    public abstract void leftClickOnAir(Player player, Block block, boolean shifting);
    public abstract void rightClickOnBlock(Player player, Block block, boolean shifting);
    public abstract void rightClickOnAir(Player player, Block block, boolean shifting);
    public abstract void onBlockBreak(Player player, Block block, boolean sneaking);
    public abstract void onDrop(Player player);
    public abstract void onPickup(Player player);
    public abstract void onUpgrade(Player player, ItemStack upgradeItem, int upgradeLevel);

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
        itemBuilder.addLoreLine(ChatColor.GRAY + "Upgrade: " + this.getCurrentUpgrade() + "/" + this.getUpgrades());
        if (this.getRarity() != null) {
            StringBuilder s = new StringBuilder();
            for (ChatColor colour : this.getRarity().getColor()) s.append(colour);
            itemBuilder.addLoreLine(String.format("%s%s FARMING TOOL", s, this.getRarity().name()));
        }
        if (this.getDurability() > 0) itemBuilder.setDurability((short) this.getDurability());
        if (this.isUnbreakable()) itemBuilder.setUnbreakable(true);
        if (this.getItemFlags() != null && this.getItemFlags().size() > 0)
            itemBuilder.addItemFlags(this.getItemFlags());
        if (this.getEnchantmentLevels().size() > 0)
            this.getEnchantmentLevels().forEach(itemBuilder::setEnchantment);
        ItemStack item = itemBuilder.build();
        NamespacedKey key = new NamespacedKey(FarmingPlugin.getPlugin(FarmingPlugin.class), "farmingTool");
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return null;
        PersistentDataContainer container = itemMeta.getPersistentDataContainer();
        container.set(key, PersistentDataType.STRING, itemMeta.getDisplayName());
        item.setItemMeta(itemMeta);
        return itemBuilder.build();
    }

    public static FarmingTool getFarmingTool(ItemStack item) {
            NamespacedKey key = new NamespacedKey(FarmingPlugin.getPlugin(FarmingPlugin.class), "farmingTool");
            ItemMeta itemMeta = item.getItemMeta();
            if (itemMeta == null) return null;
            PersistentDataContainer container = itemMeta.getPersistentDataContainer();
            if (!container.has(key, PersistentDataType.STRING)) return null;
            String name = container.get(key, PersistentDataType.STRING);
            for (FarmingTool farmingTool : farmingTools) {
                if (farmingTool.getFormattedName().equals(name)) return farmingTool;
            }
            return null;
        }

        public static boolean isFarmingTool(ItemStack item) {
            NamespacedKey k = new NamespacedKey(FarmingPlugin.getPlugin(FarmingPlugin.class), "farmingTool");
            if (item == null) return false;
            if (!item.hasItemMeta()) return false;
            ItemMeta itemMeta = item.getItemMeta();
            if (itemMeta == null) return false;
            PersistentDataContainer container = itemMeta.getPersistentDataContainer();
            return container.has(k, PersistentDataType.STRING);
        }

        public static void setFarmingTools(List<FarmingTool> farmingTools) {
            FarmingTool.farmingTools = farmingTools;
        }

        public static List<FarmingTool> getFarmingTools() {
            return FarmingTool.farmingTools;
        }

}

