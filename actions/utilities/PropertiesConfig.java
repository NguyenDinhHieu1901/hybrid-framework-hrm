package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import commons.GlobalConstants;

public class PropertiesConfig {
	private final Properties properties;
	private final String propertyFilePath = GlobalConstants.getGlobalConstants().getProjectPath() + File.separator + "resource" + File.separator + "config.properties";

	private static PropertiesConfig configLoader;

	private PropertiesConfig() {
		properties = PropertiesConfig.propertyLoader(propertyFilePath);
	}

	public static PropertiesConfig getFileConfigReader() {
		return (configLoader == null) ? new PropertiesConfig() : configLoader;
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	private static Properties propertyLoader(String propertyFilePath) {
		Properties properties = new Properties();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("Failed to load properties file " + propertyFilePath);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration properties not found at " + propertyFilePath);
		}
		return properties;
	}
}
