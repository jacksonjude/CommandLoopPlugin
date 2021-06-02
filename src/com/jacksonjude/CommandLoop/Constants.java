package com.jacksonjude.CommandLoop;

public class Constants {
	public static final String SCHEDULED_COMMANDS_CONFIG_KEY = "commands";
	public static final String COMMAND_CONFIG_KEY = "command";
	public static final String TIME_CONFIG_KEY = "time";
	public static final String TIMEZONE_CONFIG_KEY = "timezone";
	public static final String UPDATE_INTERVAL_KEY = "update-interval";
	public static final String START_MINUTE_INTERVAL_KEY = "start-minute-interval";
	
	public static final String TIME_COMPONENT_YEAR_KEY = "year";
	public static final String TIME_COMPONENT_MONTH_KEY = "month";
	public static final String TIME_COMPONENT_WEEK_KEY = "week";
	public static final String TIME_COMPONENT_WEEKDAY_KEY = "weekday";
	public static final String TIME_COMPONENT_DAY_KEY = "day";
	public static final String TIME_COMPONENT_HOUR_KEY = "hour";
	public static final String TIME_COMPONENT_MINUTE_KEY = "minute";
	
	public static final int TIME_COMPONENT_MONTH_MAX = 11;
	public static final int TIME_COMPONENT_WEEK_MAX = 52;
	public static final int TIME_COMPONENT_WEEKDAY_MAX = 7;
	public static final int TIME_COMPONENT_DAY_MAX = 31;
	public static final int TIME_COMPONENT_HOUR_MAX = 23;
	public static final int TIME_COMPONENT_MINUTE_MAX = 59;
	
	public static final String INT_PARSE_ERROR = "Integer parse error on <timeRangesKey>.<rangeName>.<timeKey>.<componentKey>";
	public static final String RANGE_PARSE_ERROR = "Range parse error on <timeRangesKey>.<rangeName>.<timeKey>.<componentKey>";
	public static final String VALUE_ERROR = "Value error on <timeRangesKey>.<rangeName>.<timeKey>.<componentKey>";
	
	public static final String ADMIN_COMMAND = "commandloop";
	public static final String ADMIN_PERMISSION = "commandloop.admin";
}
