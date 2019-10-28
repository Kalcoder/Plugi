package com.github.kalcoder.plugi.listeners;

import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.util.ChatHelper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class CustomLeaveJoinMessages implements Listener {
  
  Plugi plugi;
  
  public CustomLeaveJoinMessages(Plugi plugi) {
    this.plugi = plugi;
    plugi.getServer().getPluginManager().registerEvents(this, plugi);
  }
  
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    if (!((boolean) plugi.settings.findSetting("Use Join Message").getValue())) return;
    
    event.setJoinMessage(ChatHelper.translateColors("&8[&a+&8] &f" + event.getPlayer().getDisplayName()));
  }
  
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent event) {
    if (!((boolean) plugi.settings.findSetting("Use Leave Message").getValue())) return;
  
    event.setQuitMessage(ChatHelper.translateColors("&8[&c-&8] &f" + event.getPlayer().getDisplayName()));
  }
}
