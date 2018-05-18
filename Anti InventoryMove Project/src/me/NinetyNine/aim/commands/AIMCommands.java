package me.NinetyNine.aim.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import me.NinetyNine.aim.utils.AIMUtils;

public class AIMCommands implements Listener, CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;

			if (cmd.getName().equalsIgnoreCase("aim")) {
				if (player.hasPermission("gcbanz.ban")) {
					if (args.length == 0) {
						AIMUtils.sendPlayerMessage(player, "&cUsage: /aim <player>");
						return true;
					}
					@SuppressWarnings("deprecation")
					Player target = Bukkit.getPlayer(args[0]);
					if (target.isOnline()) {
						AIMUtils.openPlayerInventory(target);
						AIMUtils.sendPlayerMessage(player, "&5" + target.getName()
								+ " &cis being checked! &7(This opens their inventory, if player keeps moving, it means they're using the InventoryMove hack!)");
						return true;
					} else {
						AIMUtils.sendPlayerMessage(player, "&cPlayer is not online/cannot find player");
						return true;
					}
				}
			}
		}
		return true;
	}
}