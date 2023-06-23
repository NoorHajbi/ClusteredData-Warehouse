package com.progresssoft.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author noor.hajbi 
 * utillogger methods for each log level
 */
public class LoggerUtil {
	private static final Logger LOGGER = LogManager.getLogger(LoggerUtil.class);

	public void info(String message) {
		LOGGER.info(message);
	}

	public void debug(String message) {
		LOGGER.debug(message);
	}

	public void warn(String value, String message) {
		LOGGER.warn(value, message);
	}

	public void error(String message) {
		LOGGER.error(message);
	}

	public void error(String value, String message) {
		LOGGER.error(value, message);
	}

}
