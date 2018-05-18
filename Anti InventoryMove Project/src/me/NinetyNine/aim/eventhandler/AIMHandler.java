package me.NinetyNine.aim.eventhandler;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import me.NinetyNine.aim.AIM;
import me.NinetyNine.aim.utils.AIMUtils;

public class AIMHandler implements Listener {

	public static HashMap<Player, Inventory> playerInOpen;

	public static HashMap<Player, Integer> time;
	public static HashMap<Player, BukkitRunnable> task;

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player player = e.getPlayer();
		if (playerInOpen.containsKey(player)) {
			e.setCancelled(true);
			for (Player all : Bukkit.getServer().getOnlinePlayers()) {
				if (all.hasPermission("gcbanz.ban")) {
					AIMUtils.sendPlayerMessage(all, "&5" + player.getName()
							+ " &cmight be using InventoryMove! &6(Could be false if player got hit, was lagging, and quickly closed an inventory/chest!)");
					playerInOpen.remove(player);
				} else
					return;
			}
		} else
			return;
	}

	@EventHandler
	public void onPlayerSprint(PlayerToggleSprintEvent e) {
		Player player = e.getPlayer();
		if (playerInOpen.containsKey(player)) {
			e.setCancelled(true);
			for (Player all : Bukkit.getServer().getOnlinePlayers()) {
				if (all.hasPermission("gcbanz.ban")) {
					AIMUtils.sendPlayerMessage(all, "&5" + player.getName() + " &cmight be using InventoryMove!");
				} else
					return;
			}
		} else
			return;
	}

	@EventHandler
	public void onInventoryOpen(InventoryOpenEvent e) {
		Player player = (Player) e.getPlayer();
		Inventory inventory = e.getInventory();
		setCheck(player, inventory);
	}

	private static void setCheck(Player player, Inventory inventory) {
		if (!time.containsKey(player) && !task.containsKey(player) && !playerInOpen.containsKey(player)) {
			time.put(player, 1);
			task.put(player, new BukkitRunnable() {
				public void run() {
					time.put(player, time.get(player) - 1);
					if (time.get(player) == 0) {
						playerInOpen.put(player, inventory);
						time.remove(player);
						task.remove(player);
						cancel();
					}
				}
			});
			task.get(player).runTaskTimer(AIM.plugin, 20L, 20L);
		} else
			return;
	}

	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) {
		Player player = (Player) e.getPlayer();
		if (playerInOpen.containsKey(player)) {
			playerInOpen.remove(player);
		} else
			return;
	}
}