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
    settings = new Settings(this);
    YamlConfigUtil.loadAllConfigs(this);
    
    registerSettings();
    registerCommands();
    registerEvents();
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
  
  @Override
  public void onDisable() {
  
  }
}
