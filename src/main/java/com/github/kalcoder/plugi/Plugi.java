package com.github.kalcoder.plugi;

import com.github.kalcoder.plugi.commands.admin.PlugiConfigCommand;
import com.github.kalcoder.plugi.commands.admin.SudoCommand;
import com.github.kalcoder.plugi.commands.players.InvseeCommand;
import com.github.kalcoder.plugi.listeners.CustomLeaveJoinMessages;
import com.github.kalcoder.plugi.util.YamlConfigUtil;
import org.bukkit.plugin.java.JavaPlugin;

public final class Plugi extends JavaPlugin {
  
  public Settings settings;
  
  @Override
  public void onEnable() {
    preLoad();
    load();
    postLoad();
  }
  
  private void registerEvents() {
    new CustomLeaveJoinMessages(this);
  }
  
  private void registerCommands() {
    new PlugiConfigCommand(this);
    new InvseeCommand(this);
    new SudoCommand(this);
  }
  
  private void registerSettings() {
    settings.new Setting<>("Use Join Message", true);
    settings.new Setting<>("Use Leave Message", true);
  }
  
  private void preLoad() {
    YamlConfigUtil.loadAllConfigs(this);
  }
  
  private void load() {
    settings = new Settings(this);
    
    registerCommands();
    registerEvents();
    registerSettings();
  }
  
  private void postLoad() {
  
  }
  
  @Override
  public void onDisable() {
  
  }
}
