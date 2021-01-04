package com.fdmgroup.TradingPlatform;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log {
	
	Logger log;
	
	public Log(User user)
	{
		log = Logger.getLogger(user.getClass());
		PropertyConfigurator.configure("H:\\Java\\workspace\\TradingPlatform\\log4j.properties");
	}
	
	public Log()
	{
		log = Logger.getLogger("LogFile");
		PropertyConfigurator.configure("H:\\Java\\workspace\\TradingPlatform\\log4j.properties");
	}
	
	public void logWarn(String message)
	{
		log.warn(message);
	}
	
	public void logError(String message)
	{
		log.error(message);
	}
	
	public void logTrace(String message)
	{
		log.trace(message);
	}
	
	public void logFatal(String message)
	{
		log.fatal(message);
	}
	
	public void logInfo(String message)
	{
		log.info(message);
	}
}
