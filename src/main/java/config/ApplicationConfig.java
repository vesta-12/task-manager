package config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ApplicationConfig {
    private static Properties prop = null;

    public static Properties getConfig() {
        if (prop != null) {
            return prop;
        }

        Properties props = new Properties();

        try (InputStream input = Files.newInputStream(Path.of("application.properties"))) {
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        prop = props;

        return props;
    }
}
