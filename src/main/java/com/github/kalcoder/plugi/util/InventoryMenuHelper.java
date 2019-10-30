package com.github.kalcoder.plugi.util;

import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.inventorymenu.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import javax.annotation.Nullable;
import java.util.Arrays;

public class InventoryMenuHelper {

  public static OptionsInventoryMenu generateOptionsInventoryMenu(Plugi plugi, String name, InventoryMenuItem... items) {
    OptionsInventoryMenu inventoryMenu = new OptionsInventoryMenu(plugi, name, Arrays.asList(items));
    for (InventoryMenuItem item :
            items) {
      item.setMenu(inventoryMenu);
    }
    return inventoryMenu;
  }

}
