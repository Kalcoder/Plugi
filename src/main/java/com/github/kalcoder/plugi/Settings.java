package com.github.kalcoder.plugi;

import com.github.kalcoder.plugi.errors.ConfigurationNotFoundException;
import com.github.kalcoder.plugi.util.YamlConfigUtil;

import java.util.ArrayList;
import java.util.List;

public class Settings {
  
  private final Plugi plugi;
  private List<Setting> settings = new ArrayList<>();
  
  public Settings(Plugi plugi) {
    this.plugi = plugi;
    if (!YamlConfigUtil.configurationExists("settings", plugi)) YamlConfigUtil.createNewConfig("settings", plugi);
    try {
      for (String path :
              YamlConfigUtil.getAllKeysFromConfig("settings", false)) {
  
        new Setting<>(path, YamlConfigUtil.readFromConfig("settings", path));
      }
    } catch (ConfigurationNotFoundException e) {
      e.printStackTrace();
    }
  }
  
  public List<Setting> getSettings() {
    return settings;
  }
  
  public Setting findSetting(String name) {
    
    for (Setting setting : settings) {
      if (setting.name.equals(name)) {
        return setting;
      }
    }
    
    return null;
  }
  
  public Setting findSetting(String name, Class type) {
    
    for (Setting setting : settings) {
      if (setting.name.equals(name) && setting.getValue().getClass().equals(type)) {
        return setting;
      }
    }
    
    return null;
  }
  
  public class Setting<V> {
    V value;
    String name;
    
    public Setting(String name, V value) {
      if (settings.contains(this)) return;
      this.name = name;
      this.value = value;
      settings.add(this);
    }
  
    public V getValue() {
      return value;
    }
    
    public void setValue(V value) {
      this.value = value;
    }
  }

}
