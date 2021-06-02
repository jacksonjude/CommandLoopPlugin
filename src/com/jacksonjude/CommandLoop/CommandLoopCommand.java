package com.jacksonjude.CommandLoop;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandLoopCommand implements CommandExecutor
{
	private final CommandLoopPlugin plugin;
	
	public CommandLoopCommand(CommandLoopPlugin plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (!sender.hasPermission(Constants.ADMIN_PERMISSION))
		{
			sender.sendMessage(ChatColor.RED + "You cannot edit CommandLoop config");
			return true;
		}
		
		if (args.length == 0)
		{
			sender.sendMessage(ChatColor.GOLD + "/commandloop reload" + ChatColor.GRAY + " - reloads configuration" + "\n" + ChatColor.GOLD);
			return true;
		}
		
		switch (args[0].toLowerCase())
		{
		case "reload":
			plugin.reloadConfig();
			plugin.loadCommandConfig();
			sender.sendMessage(ChatColor.GREEN + "CommandLoop config reloaded");
			
			return true;
		}
		
		return false;
	}
}
