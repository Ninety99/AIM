package me.NinetyNine.aim.eventhandler;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;

import me.NinetyNine.aim.utils.AIMUtils;

public class AIMHandler implements Listener {

	public static HashMap<Player, Inventory> playerInOpen;

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		Inventory inventory = (Inventory) player.getOpenInventory();
		if (playerInOpen.containsKey(player) && playerInOpen.containsValue(inventory)) {
			e.setCancelled(true);
			for (Player all : Bukkit.getServer().getOnlinePlayers()) {
				if (all.hasPermission("gcbanz.ban")) {
					AIMUtils.sendPlayerMessage(all, "&5" + player.getName() + " &c might be using InventoryMove!");
				} else
					return;
			}
		}
	}

	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent e) {
		Player player = (Player) e.getPlayer();
		Inventory inventory = e.getInventory();
		if (playerInOpen.containsKey(player)) {
			return;
		} else
			playerInOpen.put(player, inventory);
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		Player player = (Player) e.getPlayer();
		if (playerInOpen.containsKey(player)) {
			return;
		} else
			playerInOpen.remove(player);
	}
}