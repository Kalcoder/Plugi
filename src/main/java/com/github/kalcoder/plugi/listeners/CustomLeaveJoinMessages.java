package com.github.kalcoder.plugi.listeners;

import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.util.ChatUtil;
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
  public void onPlayerJoin(PlayerJoinEvent e) {
    if (!((boolean) plugi.settings.findSetting("Use Join Message").getValue())) return;
    
    e.setJoinMessage(ChatUtil.translateColors("&8[&a+&8] &f" + e.getPlayer().getDisplayName()));
  }
  
  @EventHandler
  public void onPlayerQuit(PlayerQuitEvent e) {
    if (!((boolean) plugi.settings.findSetting("Use Leave Message").getValue())) return;
  
    e.setQuitMessage(ChatUtil.translateColors("&8[&c-&8] &f" + e.getPlayer().getDisplayName()));
  }
}
