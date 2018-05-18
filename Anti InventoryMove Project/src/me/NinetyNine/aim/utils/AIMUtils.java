package me.NinetyNine.aim.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import static org.bukkit.ChatColor.*;

import org.bukkit.Bukkit;

public class AIMUtils implements Listener {
	
	private static final String prefix = "&7[&9AIM&7] ";

	public static void sendPlayerMessage(Player player, String message) {
		player.sendMessage(translateAlternateColorCodes('&', prefix + message));
	}
	
	public static void sendConsoleMessage(String message) {
		Bukkit.getServer().getLogger().info("[AIM] " + message);
	}
}
