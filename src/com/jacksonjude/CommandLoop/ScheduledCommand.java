package com.jacksonjude.CommandLoop;

public class ScheduledCommand
{
	private CommandLoopPlugin plugin;
	
	private String scheduledCommandName;
	private TimeRange timeRange;
	private String command;
	
	public ScheduledCommand(String name, TimeRange range, String command, CommandLoopPlugin plugin)
	{
		this.scheduledCommandName = name;
		this.timeRange = range;
		this.command = command;
		this.plugin = plugin;
	}
	
	public void runCommand(long timeToTest)
	{
		if (timeRange.isTimeInRange(timeToTest))
		{
			plugin.getServer().getScheduler().callSyncMethod(plugin, () -> plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), this.command));
			System.out.println("Executed " + this.scheduledCommandName + " at " + timeToTest);
		}
	}
}
