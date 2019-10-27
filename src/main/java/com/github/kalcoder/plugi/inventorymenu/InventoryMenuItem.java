package com.github.kalcoder.plugi.inventorymenu;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryMenuItem {
  
  private final String name;
  private InventoryMenu menu;
  ItemStack item;
  
  public InventoryMenuItem(ItemStack item, String name) {
    this.item = item;
    this.name = name;
  
    ItemMeta itemMeta = item.getItemMeta();
    itemMeta.setDisplayName(name);
    item.setItemMeta(itemMeta);
  }
  
  public void setMenu(InventoryMenu menu) {
    this.menu = menu;
  }
  
  public void onClick(Player p) {
  
  }
  
  public void refreshMenu(Player p) {
    menu.refresh(p);
  }
  
}
