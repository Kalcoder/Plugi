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
  private int rows;
  
  public InventoryMenu(Plugi plugi, String name, InventoryMenuType menuType, List<InventoryMenuItem> menuItems) {
    plugi.getServer().getPluginManager().registerEvents(this, plugi);
    this.menuItems = menuItems;
    this.menuType = menuType;
    this.name = name;
  
    generateInventory();
  }
  
  private void generateInventory() {
    switch (menuType) {
      case OPTIONS:
  
        rows = menuItems.size() % 4 == 0 ? menuItems.size() / 4 : menuItems.size() / 4 + 1;
        menu = Bukkit.createInventory(null, (rows + 2) * 9, name);
  
        fillInventory();
    }
  }
  
  private void fillInventory() {
    int inventoryIndex;
    int currentMenuItemIndex = 0;
    
    for (int c = 1; c <= rows; c++) {
      
      inventoryIndex = c * 9;
      
      if (menuItems.size() - currentMenuItemIndex >= 4) {
        int i = 0;
        
        for (i = currentMenuItemIndex; i < currentMenuItemIndex+4; i++, inventoryIndex++) {
          menu.setItem(++inventoryIndex, menuItems.get(i).item);
          System.out.println("i = " + i);
        }
        
        currentMenuItemIndex = i;
      } else {
        
        if (menuItems.size() - (currentMenuItemIndex) == 1) {
          menu.setItem(inventoryIndex + 4, menuItems.get(currentMenuItemIndex).item);
        }
        else if (menuItems.size() - (currentMenuItemIndex) == 2) {
          menu.setItem(inventoryIndex + 3, menuItems.get(currentMenuItemIndex++).item);
          menu.setItem(inventoryIndex + 5, menuItems.get(currentMenuItemIndex).item);
        }
        else if (menuItems.size() - (currentMenuItemIndex) == 3) {
          menu.setItem(inventoryIndex + 2, menuItems.get(currentMenuItemIndex++).item);
          menu.setItem(inventoryIndex + 4, menuItems.get(currentMenuItemIndex++).item);
          menu.setItem(inventoryIndex + 6, menuItems.get(currentMenuItemIndex).item);
        }
      }
    }
  }
  
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
