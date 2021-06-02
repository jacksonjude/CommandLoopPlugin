package com.jacksonjude.CommandLoop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandLoopPlugin extends JavaPlugin
{
	private FileConfiguration fileConfig;
	private List<ScheduledCommand> scheduledCommands;
	
	private Timer scheduledCommandTimer;
	private int updateInterval;
	private int startMinuteInterval;
	
	@Override
    public void onEnable()
	{
		saveDefaultConfig();
		loadCommandConfig();
		
		getCommand(Constants.ADMIN_COMMAND).setExecutor(new CommandLoopCommand(this));
		getCommand(Constants.ADMIN_COMMAND).setTabCompleter(new CommandLoopCompleter());
    }
	
	@Override
	public void onDisable()
	{
		if (scheduledCommandTimer != null) scheduledCommandTimer.cancel();
	}
	
	public void loadCommandConfig()
	{
		fileConfig = getConfig();
		
		updateInterval = fileConfig.getInt(Constants.UPDATE_INTERVAL_KEY);
		startMinuteInterval = fileConfig.getInt(Constants.START_MINUTE_INTERVAL_KEY);
		
		scheduledCommands = new ArrayList<ScheduledCommand>();
		for (String scheduledCommandName : fileConfig.getConfigurationSection(Constants.SCHEDULED_COMMANDS_CONFIG_KEY).getKeys(false))
		{
			ConfigurationSection newScheduledCommandData = fileConfig.getConfigurationSection(Constants.SCHEDULED_COMMANDS_CONFIG_KEY + "." + scheduledCommandName);
			TimeRange newTimeRange = new TimeRange(newScheduledCommandData, scheduledCommandName, this);
			String command = (String) newScheduledCommandData.getValues(false).get(Constants.COMMAND_CONFIG_KEY);
			
			scheduledCommands.add(new ScheduledCommand(scheduledCommandName, newTimeRange, command, this));
		}
		
		restartCommandTimer();
	}
	
	class CommandTimerTask extends TimerTask
	{
		public void run()
		{
			runScheduledCommands();
		}
	}
	
	public void restartCommandTimer()
	{
		if (scheduledCommandTimer != null) scheduledCommandTimer.cancel();
		
		Calendar calendar = Calendar.getInstance();
		int seconds = calendar.get(Calendar.SECOND);
		int minutes = calendar.get(Calendar.MINUTE);
				
		int minuteAdjust = startMinuteInterval-((minutes+1)%startMinuteInterval);
		if ((minutes+1)%startMinuteInterval == 0)
			minuteAdjust = 0;
		
		scheduledCommandTimer = new Timer();
		scheduledCommandTimer.scheduleAtFixedRate(new CommandTimerTask(), 1000*(60-seconds)+1000*60*minuteAdjust, 1000*60*updateInterval);		
	}
	
	public void runScheduledCommands()
	{
		long currentTime = (new Date()).getTime();
		for (ScheduledCommand command : scheduledCommands)
			command.runCommand(currentTime);
	}
	
	public void throwComponentIntParseError(String rangeName, String componentKey)
	{
		this.getLogger().log(Level.WARNING, Constants.INT_PARSE_ERROR.replaceFirst("<timeRangesKey>", Constants.SCHEDULED_COMMANDS_CONFIG_KEY).replaceFirst("<rangeName>", rangeName).replaceFirst("<timeKey>", Constants.TIME_CONFIG_KEY).replaceFirst("<componentKey>", componentKey));
	}
	
	public void throwComponentRangeParseError(String rangeName, String componentKey)
	{
		this.getLogger().log(Level.WARNING, Constants.RANGE_PARSE_ERROR.replaceFirst("<timeRangesKey>", Constants.SCHEDULED_COMMANDS_CONFIG_KEY).replaceFirst("<rangeName>", rangeName).replaceFirst("<timeKey>", Constants.TIME_CONFIG_KEY).replaceFirst("<componentKey>", componentKey));
	}
	
	public void throwComponentValueError(String rangeName, String componentKey)
	{
		this.getLogger().log(Level.WARNING, Constants.VALUE_ERROR.replaceFirst("<timeRangesKey>", Constants.SCHEDULED_COMMANDS_CONFIG_KEY).replaceFirst("<rangeName>", rangeName).replaceFirst("<timeKey>", Constants.TIME_CONFIG_KEY).replaceFirst("<componentKey>", componentKey));
	}
}
