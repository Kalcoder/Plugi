package com.github.kalcoder.plugi.util;

import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.inventorymenu.InventoryMenu;
import com.github.kalcoder.plugi.inventorymenu.InventoryMenuItem;
import com.github.kalcoder.plugi.inventorymenu.InventoryMenuType;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

public class InventoryMenuHelper {

  public static InventoryMenu generateInventoryMenu(Plugi plugi, String name, InventoryMenuType menuType, InventoryMenuItem... items) {
    InventoryMenu inventoryMenu = new InventoryMenu(plugi, name, menuType, Arrays.asList(items));
    for (InventoryMenuItem item :
            items) {
      item.setMenu(inventoryMenu);
    }
    return inventoryMenu;
  }

}
