package me.jakeygilly.farmingplugin.Items;

import me.jakeygilly.farmingplugin.utils.FarmingTool;
import me.jakeygilly.farmingplugin.utils.Item;
import me.jakeygilly.farmingplugin.utils.Rarity;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

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
        if (event.getBlock().getType() != Material.POTATOES) {
            event.setCancelled(true);
            return;
        }
        Ageable ageable = (Ageable) event.getBlock().getBlockData();
        if (ageable.getAge() != 7) event.setCancelled(true);
        List<ItemStack> drops = new ArrayList<ItemStack>() {{
            addAll(event.getBlock().getDrops());
        }};
        for (ItemStack drop : drops) drop.setAmount(drop.getAmount() * (int) (0.2 * this.getCurrentUpgrade()));
    }

    @Override
    public void onDrop(PlayerDropItemEvent event) {

    }

    @Override
    public void onPickup(EntityPickupItemEvent event) {

    }

    @Override
    public void onUpgrade(InventoryClickEvent event) {

    }
}
