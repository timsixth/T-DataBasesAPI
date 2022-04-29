package pl.timsixth.databasesapi.config;

import net.md_5.bungee.config.Configuration;

import java.io.File;
import java.io.IOException;
@Deprecated
public interface IConfigFileBungee extends IConfigFile{

    Configuration getFile(String name) throws IOException;
    File createFile(String name);
}
