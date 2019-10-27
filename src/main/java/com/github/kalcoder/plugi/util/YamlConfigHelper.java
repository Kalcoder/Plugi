package com.github.kalcoder.plugi.util;

import com.github.kalcoder.plugi.Plugi;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class YamlConfigHelper {

  private static Map<YamlConfiguration, File> configurations = new HashMap<>();
  
  //region File Management
  public static void loadAllConfigs(Plugi plugi) {
    if (!plugi.getDataFolder().exists()) {
      plugi.getDataFolder().mkdir();
    }
    if (plugi.getDataFolder().listFiles() == null) return;
    
    for (File file :
            plugi.getDataFolder().listFiles()) {
  
      YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
      configurations.put(configuration, file);
    }
  }
  
  public static void saveAllConfigs(Plugi plugi) {
    for (YamlConfiguration config :
            configurations.keySet()) {
      try {
        config.save(configurations.get(config));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  public static void createNewConfig(String configName, Plugi plugi) {
    File configFile = new File(plugi.getDataFolder(), configName + ".yml");
    
    if (configFile.exists()) {
      plugi.getServer().getConsoleSender().sendMessage(ChatColor.RED + "Error: Could not create new config called " + configName + " because it already exists!");
      return;
    }
    
    try {
      configFile.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    configurations.put(YamlConfiguration.loadConfiguration(configFile), configFile);
  }
  
  public static boolean configurationExists(String configName, Plugi plugi) {
    File configFile = new File(plugi.getDataFolder(), configName + ".yml");
    return configFile.exists();
  }
  //endregion
  
  //region Config Management
  public static <T> T readFromConfig(String name, String path) {
    for (YamlConfiguration config :
            configurations.keySet()) {
      if (config.getName().equalsIgnoreCase(name)) return (T) config.get(path);
    }
    
    return null;
  }
  
  public static <T> T readFromConfig(YamlConfiguration config, String path) {
    return (T) config.get(path);
  }
  
  public static <T> T readFromConfig(File configFile, String path) {
    return (T) YamlConfiguration.loadConfiguration(configFile).get(path);
  }
  //endregion

}
