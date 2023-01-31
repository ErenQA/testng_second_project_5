package utilities;

import org.testng.internal.EclipseInterface;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();

    static {
        try {
            // Load file
            FileInputStream fileInputStream = new FileInputStream("config.properties");
            // Read contents of the file
            properties.load(fileInputStream);
            // Close file
            fileInputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}