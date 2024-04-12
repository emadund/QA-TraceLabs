package test.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DBUtil {

    private Properties properties;

    public DBUtil() throws FileNotFoundException, IOException {
        properties = new Properties();
        properties.load(new FileInputStream("db.properties"));
    }
    public String getURL() {
        return properties.getProperty("url");
    }
    public String getUser() {
        return properties.getProperty("user");
    }
    public String getPassword() {
        return properties.getProperty("password");
    }

}
