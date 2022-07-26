package me.jakeygilly.farmingplugin.items.farmingitems;

import me.jakeygilly.farmingplugin.utils.FarmingTool;
import me.jakeygilly.farmingplugin.utils.Rarity;
import me.jakeygilly.farmingplugin.utils.UpgradeItem;
import me.jakeygilly.farmingplugin.utils.UpgradeType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PumpkinAxe extends FarmingTool {
    public PumpkinAxe() {
        super(
                Material.GOLDEN_AXE,
                1,
                ChatColor.AQUA + "Pumpkin Axe",
                new ArrayList<String>() {{
                    add(ChatColor.GRAY + "A axe for harvesting pumpkins.");
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
    public void onEquip(PlayerItemHeldEvent event) {

    }

    @Override
    public void onUnequip(PlayerItemHeldEvent event) {

    }

    @Override
    public void clickEntity(PlayerInteractEntityEvent event) {

    }

    @Override
    public void punchEntity(EntityDamageByEntityEvent event) {

    }

    @Override
    public void leftClickOnBlock(PlayerInteractEvent event) {

    }

    @Override
    public void leftClickOnAir(PlayerInteractEvent event) {

    }

    @Override
    public void rightClickOnBlock(PlayerInteractEvent event) {

    }

    @Override
    public void rightClickOnAir(PlayerInteractEvent event) {

    }

    @Override
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.PUMPKIN) {
            event.setCancelled(true);
            return;
        }
        List<ItemStack> drops = new ArrayList<ItemStack>() {{
            addAll(event.getBlock().getDrops());
        }};
        for (ItemStack drop : drops) {
            drop.setAmount((int)(drop.getAmount() * (int) (0.2 * this.getCurrentUpgrade()) * (Math.random() * 2) + 1));
        }
        event.setDropItems(false);
        for (ItemStack drop : drops) event.getBlock().getWorld().dropItem(event.getBlock().getLocation(), drop);
    }

    @Override
    public void onDrop(PlayerDropItemEvent event) {

    }

    @Override
    public void onPickup(EntityPickupItemEvent event) {

    }

    @Override
    public void onUpgrade(InventoryClickEvent event) {
        UpgradeItem upgradeItem = UpgradeItem.getUpgradeItem(event.getCursor());
        if (upgradeItem.getUpgradeType() != UpgradeType.PUMPUPGRADE) return;
//        if (!(upgradeItem.getUpgradeLevel() == this.getCurrentUpgrade() + 1)) return;
        if (this.getUpgrades() < this.getCurrentUpgrade()) {
            event.getWhoClicked().sendMessage(String.format("%sYou have maxed out this axe's upgrades.", ChatColor.RED));
            event.setCancelled(true);
            return;
        }
        this.setCurrentUpgrade(this.getCurrentUpgrade() + 1);
        event.setCurrentItem(this.getItem());
        event.getWhoClicked().setItemOnCursor(null);
    }
}
