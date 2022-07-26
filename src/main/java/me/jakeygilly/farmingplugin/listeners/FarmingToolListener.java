package me.jakeygilly.farmingplugin.listeners;

import me.jakeygilly.farmingplugin.utils.FarmingTool;
import me.jakeygilly.farmingplugin.utils.UpgradeItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;

public class FarmingToolListener implements Listener {
    @EventHandler
    private void onChangeFarmingTool(PlayerItemHeldEvent event) {
        if (event.getPlayer().getInventory().getItem(event.getNewSlot()) != null) {
            FarmingTool farmingTool = FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItem(event.getNewSlot()));
            if (farmingTool == null) return;
            farmingTool.onEquip(event);
        } else if (event.getPlayer().getInventory().getItem(event.getPreviousSlot()) != null) {
            FarmingTool farmingTool = FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItem(event.getPreviousSlot()));
            if (farmingTool == null) return;
            farmingTool.onUnequip(event);
        }
    }

    @EventHandler
    private void onAttackEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        FarmingTool weaponItem = FarmingTool.getFarmingTool(((Player)event.getDamager()).getInventory().getItemInMainHand());
        if (weaponItem == null) return;
        weaponItem.punchEntity(event);
    }

    @EventHandler
    private void playerInteractionWithEntity(PlayerInteractEntityEvent event) {
        FarmingTool weaponItem = event.getHand() == EquipmentSlot.HAND ? FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItemInMainHand()) : FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItemInOffHand());
        if (weaponItem == null) return;
        weaponItem.clickEntity(event);
    }

    @EventHandler
    private void playerInteraction(PlayerInteractEvent event) {
        FarmingTool weaponItem = event.getHand() == EquipmentSlot.HAND ? FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItemInMainHand()) : FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItemInOffHand());
        if (weaponItem == null) return;
        switch (event.getAction()) {
            case LEFT_CLICK_BLOCK:
                weaponItem.leftClickOnBlock(event);
                break;
            case LEFT_CLICK_AIR:
                weaponItem.leftClickOnAir(event);
                break;
            case RIGHT_CLICK_BLOCK:
                weaponItem.rightClickOnBlock(event);
                break;
            case RIGHT_CLICK_AIR:
                weaponItem.rightClickOnAir(event);
                break;
        }
    }

    @EventHandler
    private void playerBreakBlock(BlockBreakEvent event) {
        FarmingTool weaponItem = FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItemInMainHand());
        if (weaponItem == null) return;
        weaponItem.onBlockBreak(event);
    }

    @EventHandler
    private void playerDropItem(PlayerDropItemEvent event) {
        FarmingTool weaponItem = FarmingTool.getFarmingTool(event.getItemDrop().getItemStack());
        if (weaponItem == null) return;
        weaponItem.onDrop(event);
    }

    @EventHandler
    private void playerPickupItem(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        FarmingTool weaponItem = FarmingTool.getFarmingTool(event.getItem().getItemStack());
        if (weaponItem == null) return;
        weaponItem.onPickup(event);
    }

    @EventHandler
    private void upgradeFarmingTool(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        if (event.getAction() != InventoryAction.SWAP_WITH_CURSOR) return;
        if (event.getCursor() == null) return;
        if (event.getCurrentItem() == null) return;
        FarmingTool farmingTool = FarmingTool.getFarmingTool(event.getCurrentItem());
        if (farmingTool == null) return;
        UpgradeItem upgradeItem = UpgradeItem.getUpgradeItem(event.getCursor());
        if (upgradeItem == null) return;
        farmingTool.onUpgrade(event);
    }
}
