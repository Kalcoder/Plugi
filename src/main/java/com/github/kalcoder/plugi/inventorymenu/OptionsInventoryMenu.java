package com.github.kalcoder.plugi.inventorymenu;

import com.github.kalcoder.plugi.Plugi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.List;

public class OptionsInventoryMenu extends InventoryMenu {
  
  public OptionsInventoryMenu(Plugi plugi, String name, @Nullable List<InventoryMenuItem> menuItems) {
    super(plugi, name, menuItems);
  }
  
  @Override
  protected void generateInventory() {
    rows = menuItems.size() % 4 == 0 ? menuItems.size() / 4 : menuItems.size() / 4 + 1;
    menu = Bukkit.createInventory(null, (rows + 2) * 9, name);
  
    fillInventory();
  }
  
  @Override
  protected void fillInventory() {
    int inventoryIndex;
    int currentMenuItemIndex = 0;
  
    for (int c = 1; c <= rows; c++) {
    
      inventoryIndex = c * 9;
    
      if (menuItems.size() - currentMenuItemIndex >= 4) {
        int i = 0;
      
        for (i = currentMenuItemIndex; i < currentMenuItemIndex + 4; i++, inventoryIndex++) {
          menu.setItem(++inventoryIndex, menuItems.get(i).item);
          System.out.println("i = " + i);
        }
      
        currentMenuItemIndex = i;
      } else {
      
        if (menuItems.size() - (currentMenuItemIndex) == 1) {
          menu.setItem(inventoryIndex + 4, menuItems.get(currentMenuItemIndex).item);
        } else if (menuItems.size() - (currentMenuItemIndex) == 2) {
          menu.setItem(inventoryIndex + 3, menuItems.get(currentMenuItemIndex++).item);
          menu.setItem(inventoryIndex + 5, menuItems.get(currentMenuItemIndex).item);
        } else if (menuItems.size() - (currentMenuItemIndex) == 3) {
          menu.setItem(inventoryIndex + 2, menuItems.get(currentMenuItemIndex++).item);
          menu.setItem(inventoryIndex + 4, menuItems.get(currentMenuItemIndex++).item);
          menu.setItem(inventoryIndex + 6, menuItems.get(currentMenuItemIndex).item);
        }
      }
    }
  }
}
