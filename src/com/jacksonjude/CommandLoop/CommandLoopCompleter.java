package com.jacksonjude.CommandLoop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class CommandLoopCompleter implements TabCompleter
{
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) 
	{
		switch (args.length)
		{
		case 1:
			List<String> subcommands = new ArrayList<String>(Arrays.asList("reload"));
			return subcommands;
		}
		
		return null;
	}
}
