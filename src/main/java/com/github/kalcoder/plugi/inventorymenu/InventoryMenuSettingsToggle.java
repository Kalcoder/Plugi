package com.github.kalcoder.plugi.inventorymenu;

import com.github.kalcoder.plugi.Settings.Setting;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryMenuSettingsToggle extends InventoryMenuToggle {
  
  private final Setting<Boolean> setting;
  
  public InventoryMenuSettingsToggle(String nameOn, String nameOff, Setting<Boolean> setting) {
    super(nameOn, nameOff, setting.getValue());
    this.setting = setting;
  }
  
  @Override
  public void onClick(Player p) {
    super.onClick(p);
    setting.setValue(!setting.getValue());
    p.sendMessage(ChatColor.RED + setting.getValue().toString());
    refreshMenu(p);
  }
}
