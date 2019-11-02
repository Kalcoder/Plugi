package com.github.kalcoder.plugi.util;

import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.errors.ConfigurationNotFoundException;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class YamlConfigUtil {

  private static Map<YamlConfiguration, File> configurations = new HashMap<>();
  
  //region File Management
  public static void loadAllConfigs(Plugi plugi) {
    if (!plugi.getDataFolder().exists()) {
      System.out.println("Making data folder!");
      plugi.getDataFolder().mkdir();
    }
    if (plugi.getDataFolder().listFiles() == null) return;
    System.out.println("Files were found!");
    
    for (File file :
            plugi.getDataFolder().listFiles()) {
      System.out.println(file.getAbsolutePath());
      YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
      configurations.put(configuration, file);
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
  public static Set<String> getAllKeysFromConfig(String name, boolean deep) throws ConfigurationNotFoundException {
    for (File file :
            configurations.values()) {
      System.out.println("file.getName() = " + file.getName().substring(0, file.getName().length() - 4));
      if (file.getName().substring(0, file.getName().length() - 4).equalsIgnoreCase(name)) return YamlConfiguration.loadConfiguration(file).getKeys(deep);
    }
    throw new ConfigurationNotFoundException(name);
  }
  
  public static Object readFromConfig(String name, String path) throws ConfigurationNotFoundException {
    for (File file :
            configurations.values()) {
      if (file.getName().substring(0, file.getName().length() - 4).equalsIgnoreCase(name)) return YamlConfiguration.loadConfiguration(file).get(path);
    }
    throw new ConfigurationNotFoundException(name);
  }
  
  public static <T> T readFromConfig(YamlConfiguration config, String path) {
    return (T) config.get(path);
  }
  
  public static <T> T readFromConfig(File configFile, String path) {
    return (T) YamlConfiguration.loadConfiguration(configFile).get(path);
  }
  
  public static boolean readBooleanFromConfig(String name, String path) throws ConfigurationNotFoundException {
    for (File file :
            configurations.values()) {
      if (file.getName().substring(0, file.getName().length() - 4).equalsIgnoreCase(name)) return YamlConfiguration.loadConfiguration(file).getBoolean(path);
    }
    throw new ConfigurationNotFoundException(name);
    
  }
  
  public static String readStringFromConfig(String name, String path) throws ConfigurationNotFoundException {
    for (File file :
            configurations.values()) {
      if (file.getName().substring(0, file.getName().length() - 4).equalsIgnoreCase(name)) return YamlConfiguration.loadConfiguration(file).getString(path);
    }
    throw new ConfigurationNotFoundException(name);
    
  }
  
  public static int readIntFromConfig(String name, String path) throws ConfigurationNotFoundException {
    for (File file :
            configurations.values()) {
      if (file.getName().substring(0, file.getName().length() - 4).equalsIgnoreCase(name)) return YamlConfiguration.loadConfiguration(file).getInt(path);
    }
    throw new ConfigurationNotFoundException(name);
    
  }
  
  public static void setInConfig(String name, String path, Object value) throws ConfigurationNotFoundException {
    for (File file :
            configurations.values()) {
      if (file.getName().substring(0, file.getName().length() - 4).equalsIgnoreCase(name)) {
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set(path, value);
        try {
          config.save(file);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    throw new ConfigurationNotFoundException(name);
  }
  
  //endregion

}
