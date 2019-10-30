package com.github.kalcoder.plugi.inventorymenu;

import com.github.kalcoder.plugi.Plugi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public abstract class InventoryMenu implements Listener {
  
  protected final String name;
  public List<InventoryMenuItem> menuItems;
  protected Inventory menu;
  protected int rows;
  
  public InventoryMenu(Plugi plugi, String name, List<InventoryMenuItem> menuItems) {
    plugi.getServer().getPluginManager().registerEvents(this, plugi);
    this.menuItems = menuItems;
    this.name = name;
  }
  
  abstract void generateInventory();
  
  abstract void fillInventory();
  
  private void refreshInventory() {
    menu.clear();
    fillInventory();
  }
  
  public void refresh(Player p) {
    refreshInventory();
  }
  
  public List<InventoryMenuItem> getMenuItems() {
    return menuItems;
  }
  
  public void show(Player p) {
    p.openInventory(menu);
  }
  
  @EventHandler()
  public void onInventoryClick(InventoryClickEvent event) {
    if (!event.getClickedInventory().equals(menu)) return;
    if (event.getCurrentItem() == null) return;
    
    event.setCancelled(true);
    
    for (InventoryMenuItem menuItem :
            menuItems) {
      if (event.getCurrentItem().equals(menuItem.item)) {
        menuItem.onClick(((Player) event.getWhoClicked()));
      }
    }
  }
}
