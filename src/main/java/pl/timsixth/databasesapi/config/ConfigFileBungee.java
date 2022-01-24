package pl.timsixth.databasesapi.config;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import pl.timsixth.databasesapi.bungee.DatabasesAPIBungee;
import pl.timsixth.databasesapi.database.DataBaseType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@RequiredArgsConstructor
public class ConfigFileBungee implements IConfigFileBungee {

    private final DatabasesAPIBungee databasesAPIBungee;

    @Override
    public File createFile(String name) {
        if (!databasesAPIBungee.getDataFolder().exists()) {
            databasesAPIBungee.getDataFolder().mkdir();
        }

        File file = new File(databasesAPIBungee.getDataFolder(), name);
        if (!file.exists()) {
            try (InputStream in = databasesAPIBungee.getResourceAsStream(name)) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    @Override
    public DataBaseType getDataBaseType() throws IOException {
        return DataBaseType.valueOf(getFile("config.yml").getString("type").toUpperCase());
    }
    @Override
    public Configuration getFile(String name) throws IOException {
        return ConfigurationProvider.getProvider(YamlConfiguration.class).
                load(new File(databasesAPIBungee.getDataFolder(), name));
    }
}
