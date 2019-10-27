package com.github.kalcoder.plugi.inventorymenu;

import com.github.kalcoder.plugi.Plugi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryMenu implements Listener {
  
  private final InventoryMenuType menuType;
  private final String name;
  public List<InventoryMenuItem> menuItems;
  private Inventory menu;
  
  public InventoryMenu(Plugi plugi, String name, InventoryMenuType menuType, List<InventoryMenuItem> menuItems) {
    plugi.getServer().getPluginManager().registerEvents(this, plugi);
    this.menuItems = menuItems;
    this.menuType = menuType;
    this.name = name;
  
    generateInventory();
  }
  
  private void generateInventory() {
    int rows;
    
    switch (menuType) {
      case OPTIONS:
        
        rows = (menuItems.size() / 9 + 1);
        menu = Bukkit.createInventory(null, (rows + 2) * 9, name);
        for (InventoryMenuItem item :
                menuItems) {
          menu.addItem(item.item);
        }
    }
  }
  
  private void refreshInventory() {
    menu.clear();
      for (InventoryMenuItem item :
              menuItems) {
        menu.addItem(item.item);
      }
  }
  
  public void refresh(Player p) {
    refreshInventory();
//    p.closeInventory();
//    p.openInventory(menu);
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
