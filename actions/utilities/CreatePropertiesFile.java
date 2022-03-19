package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import commons.GlobalConstants;

public class CreatePropertiesFile {
	public static CreatePropertiesFile getCreatePropertiesFile() {
		return new CreatePropertiesFile();
	}

	public void writeConfigFile(String propertiesFilePath) {
		Properties properties = new Properties();
		try {
			FileOutputStream output = new FileOutputStream(propertiesFilePath);
			properties.setProperty("db.url", "localhost");
			properties.setProperty("url.username", "root");
			properties.setProperty("db.password", "secret");
			properties.setProperty("app.username", "Admin");
			properties.setProperty("app.password", "Admin123");
			try {
				properties.store(output, null);
				System.out.println("Creating a Properties File");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		CreatePropertiesFile.getCreatePropertiesFile().writeConfigFile(GlobalConstants.getGlobalConstants().getProjectPath() + File.separator + "resource" + File.separator + "config.properties");
		
	}
}
