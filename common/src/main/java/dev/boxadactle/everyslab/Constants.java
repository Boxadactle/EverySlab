package dev.boxadactle.everyslab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

public class Constants {

	public static final String MOD_ID = "everyslab";
	public static final String MOD_NAME = "EverySlab";
	public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);
	public static boolean IS_DEV;
	public static Path CONFIG_PATH;
	public static String MC_VERSION = "1.20.4";
}