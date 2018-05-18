package me.NinetyNine.aim;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.NinetyNine.aim.commands.AIMCommands;
import me.NinetyNine.aim.eventhandler.AIMHandler;
import me.NinetyNine.aim.utils.AIMUtils;

public class AIM extends JavaPlugin {

	public static AIM plugin;

	@Override
	public void onEnable() {
		plugin = this;

		registerListeners();
		registerStatics();
		registerCommands();

		AIMUtils.sendConsoleMessage("Enabled!");
	}

	@Override
	public void onDisable() {
		AIMUtils.sendConsoleMessage("Disabled!");
	}

	private void registerListeners() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new AIMUtils(), this);
		pm.registerEvents(new AIMCommands(), this);
		pm.registerEvents(new AIMHandler(), this);
	}

	private void registerStatics() {
		AIMHandler.playerInOpen = new HashMap<Player, Inventory>();
		AIMHandler.time = new HashMap<Player, Integer>();
		AIMHandler.task = new HashMap<Player, BukkitRunnable>();
	}

	private void registerCommands() {
		getCommand("aim").setExecutor(new AIMCommands());
	}
}
