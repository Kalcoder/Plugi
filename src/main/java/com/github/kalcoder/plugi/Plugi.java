package com.github.kalcoder.plugi;

import com.github.kalcoder.plugi.commands.PlugiConfigCommand;
import com.github.kalcoder.plugi.listeners.CustomLeaveJoinMessages;
import com.github.kalcoder.plugi.util.YamlConfigHelper;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugi extends JavaPlugin {
  
  public Settings settings;
  
  @Override
  public void onEnable() {
    settings = new Settings(this);
    YamlConfigHelper.loadAllConfigs(this);
    
    registerSettings();
    registerCommands();
    registerEvents();
  }
  
  private void registerEvents() {
    new CustomLeaveJoinMessages(this);
  }
  
  private void registerCommands() {
    new PlugiConfigCommand(this);
  }
  
  private void registerSettings() {
    settings.new Setting<>("Use Join Message", true);
    settings.new Setting<>("Use Leave Message", true);
  }
  
  @Override
  public void onDisable() {
    YamlConfigHelper.saveAllConfigs(this);
  }
}
