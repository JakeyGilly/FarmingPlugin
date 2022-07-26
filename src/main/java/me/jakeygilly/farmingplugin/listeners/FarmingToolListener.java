package me.jakeygilly.farmingplugin.listeners;

import me.jakeygilly.farmingplugin.utils.FarmingTool;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.Map;

public class FarmingToolListener implements Listener{
    @EventHandler
    private void onChangeFarmingTool(PlayerItemHeldEvent event) {
        ItemStack prev = event.getPlayer().getInventory().getItem(event.getPreviousSlot());
        ItemStack next = event.getPlayer().getInventory().getItem(event.getNewSlot());
        if (next != null && FarmingTool.isFarmingTool(next)) {
            FarmingTool farmingTool = FarmingTool.getFarmingTool(next);
            if (farmingTool == null) return;
            farmingTool.onEquip(event.getPlayer());
        } else if (prev != null && FarmingTool.isFarmingTool(prev)) {
            FarmingTool farmingTool = FarmingTool.getFarmingTool(prev);
            if (farmingTool == null) return;
            farmingTool.onUnequip(event.getPlayer());
        }
    }

    @EventHandler
    private void onAttackEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            if (FarmingTool.isFarmingTool(player.getInventory().getItemInMainHand())) {
                FarmingTool weaponItem = FarmingTool.getFarmingTool(player.getInventory().getItemInMainHand());
                if (weaponItem == null) return;
                weaponItem.punchEntity(player, event.getEntity(), event.getDamage(), player.isSneaking());
            }
        }
    }

    @EventHandler
    private void playerInteractionEntity(PlayerInteractEntityEvent event) {
        FarmingTool weaponItem = null;
        if (event.getHand() == EquipmentSlot.HAND) {
            weaponItem = FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItemInMainHand());
        } else if (event.getHand() == EquipmentSlot.OFF_HAND) {
            weaponItem = FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItemInOffHand());
        }
        if (weaponItem != null) {
            weaponItem.clickEntity(event.getPlayer(), event.getRightClicked(), event.getPlayer().isSneaking());
        }
    }

    @EventHandler
    private void playerInteraction(PlayerInteractEvent event) {
        FarmingTool weaponItem = null;
        if (event.getHand() == EquipmentSlot.HAND) {
            weaponItem = FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItemInMainHand());
        } else if (event.getHand() == EquipmentSlot.OFF_HAND) {
            weaponItem = FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItemInOffHand());
        }
        if (weaponItem != null) {
            if (event.getAction() == Action.LEFT_CLICK_BLOCK) {
                weaponItem.leftClickOnBlock(event.getPlayer(), event.getClickedBlock(), event.getPlayer().isSneaking());
            } else if (event.getAction() == Action.LEFT_CLICK_AIR) {
                weaponItem.leftClickOnAir(event.getPlayer(), event.getClickedBlock(), event.getPlayer().isSneaking());
            } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                weaponItem.rightClickOnBlock(event.getPlayer(), event.getClickedBlock(), event.getPlayer().isSneaking());
            } else if (event.getAction() == Action.RIGHT_CLICK_AIR) {
                weaponItem.rightClickOnAir(event.getPlayer(), event.getClickedBlock(), event.getPlayer().isSneaking());
            }
        }
    }

    @EventHandler
    private void playerBreakBlock(BlockBreakEvent event) {
        if (FarmingTool.isFarmingTool(event.getPlayer().getInventory().getItemInMainHand())) {
            FarmingTool weaponItem = FarmingTool.getFarmingTool(event.getPlayer().getInventory().getItemInMainHand());
            if (weaponItem != null) {
                weaponItem.onBlockBreak(event.getPlayer(), event.getBlock(), event.getPlayer().isSneaking());
            }
        }
    }

    @EventHandler
    private void playerDropItem(PlayerDropItemEvent event) {
        if (FarmingTool.isFarmingTool(event.getItemDrop().getItemStack())) {
            FarmingTool weaponItem = FarmingTool.getFarmingTool(event.getItemDrop().getItemStack());
            if (weaponItem != null) {
                weaponItem.onDrop(event.getPlayer());
            }
        }
    }

    @EventHandler
    private void playerPickupItem(EntityPickupItemEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (FarmingTool.isFarmingTool(event.getItem().getItemStack())) {
                FarmingTool weaponItem = FarmingTool.getFarmingTool(event.getItem().getItemStack());
                if (weaponItem != null) {
                    weaponItem.onPickup(player);
                }
            }
        }
    }

    @EventHandler
    private void upgradeFarmingTool(InventoryClickEvent event) {
        if (event.getAction() == InventoryAction.SWAP_WITH_CURSOR) {
            if (FarmingTool.isFarmingTool(event.getCurrentItem())) {
                FarmingTool farmingTool = FarmingTool.getFarmingTool(event.getCurrentItem());
                Player player = (Player) event.getWhoClicked();
                if (farmingTool != null) {
                    farmingTool.onUpgrade(player, event.getCursor(), farmingTool.getUpgrades());
                }
            }
        }
    }
}
