package me.jakeygilly.farmingplugin.listeners;

import jdk.jfr.internal.LogLevel;
import jdk.jpackage.internal.Log;
import me.jakeygilly.farmingplugin.utils.FarmingTool;
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

import java.util.logging.Logger;

public class FarmingToolListener implements Listener {
    @EventHandler
    private void onChangeFarmingTool(PlayerItemHeldEvent event) {
        if (event.getPlayer().getInventory().getItem(event.getNewSlot()) != null) {
            FarmingTool farmingTool = FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItem(event.getNewSlot()));
            if (farmingTool == null) return;
            Logger.getLogger("FarmingPlugin").info("FarmingToolListener.onChangeFarmingTool()");
            farmingTool.onEquip(event);
        } else if (event.getPlayer().getInventory().getItem(event.getPreviousSlot()) != null) {
            FarmingTool farmingTool = FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItem(event.getPreviousSlot()));
            if (farmingTool == null) return;
            Logger.getLogger("FarmingPlugin").info("FarmingToolListener.onChangeFarmingTool()");
            farmingTool.onUnequip(event);
        }
    }

    @EventHandler
    private void onAttackEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) return;
        FarmingTool weaponItem = FarmingTool.getFarmingTool(((Player)event.getDamager()).getInventory().getItemInMainHand());
        if (weaponItem == null) return;
        Logger.getLogger("FarmingPlugin").info("FarmingToolListener.onAttackEntity()");
        weaponItem.punchEntity(event);
    }

    @EventHandler
    private void playerInteractionWithEntity(PlayerInteractEntityEvent event) {
        FarmingTool weaponItem = event.getHand() == EquipmentSlot.HAND ? FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItemInMainHand()) : FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItemInOffHand());
        if (weaponItem == null) return;
        Logger.getLogger("FarmingPlugin").info("FarmingToolListener.playerInteractionWithEntity()");
        weaponItem.clickEntity(event);
    }

    @EventHandler
    private void playerInteraction(PlayerInteractEvent event) {
        FarmingTool weaponItem = event.getHand() == EquipmentSlot.HAND ? FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItemInMainHand()) : FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItemInOffHand());
        if (weaponItem == null) return;
        Logger.getLogger("FarmingPlugin").info("FarmingToolListener.playerInteraction()");
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
        Logger.getLogger("FarmingPlugin").info("FarmingToolListener.playerBreakBlock()");
        weaponItem.onBlockBreak(event);
    }

    @EventHandler
    private void playerDropItem(PlayerDropItemEvent event) {
        FarmingTool weaponItem = FarmingTool.getFarmingTool(event.getItemDrop().getItemStack());
        if (weaponItem == null) return;
        Logger.getLogger("FarmingPlugin").info("FarmingToolListener.playerDropItem()");
        weaponItem.onDrop(event);
    }

    @EventHandler
    private void playerPickupItem(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) return;
        FarmingTool weaponItem = FarmingTool.getFarmingTool(event.getItem().getItemStack());
        if (weaponItem == null) return;
        Logger.getLogger("FarmingPlugin").info("FarmingToolListener.playerPickupItem()");
        weaponItem.onPickup(event);
    }

    @EventHandler
    private void upgradeFarmingTool(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        if (event.getAction() != InventoryAction.SWAP_WITH_CURSOR) return;
        FarmingTool farmingTool = FarmingTool.getFarmingTool(event.getCurrentItem());
        if (farmingTool == null) return;
        Logger.getLogger("FarmingPlugin").info("FarmingToolListener.upgradeFarmingTool()");
        farmingTool.onUpgrade(event);
    }
}
