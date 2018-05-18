package me.NinetyNine.aim.utils;

import static org.bukkit.ChatColor.translateAlternateColorCodes;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class AIMUtils implements Listener {

	private static final String prefix = "&7[&9AIM&7] ";

	public static void sendPlayerMessage(Player player, String message) {
		player.sendMessage(translateAlternateColorCodes('&', prefix + message));
	}

	public static void sendConsoleMessage(String message) {
		Bukkit.getServer().getLogger().info("[AIM] " + message);
	}

	public static void openPlayerInventory(Player player) {
		Inventory playerinv = player.getInventory();
		player.openInventory(playerinv);
	}
}