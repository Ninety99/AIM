package me.NinetyNine.aim;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.NinetyNine.aim.eventhandler.AIMHandler;
import me.NinetyNine.aim.utils.AIMUtils;

public class AIM extends JavaPlugin {

	public static AIM plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
		registerListeners();
		registerStatics();
	}
	
	private void registerListeners() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new AIMUtils(), this);
		pm.registerEvents(new AIMHandler(), this);
	}
	
	private void registerStatics() {
		AIMHandler.playerInOpen = new HashMap<Player, Inventory>();
	}
}
