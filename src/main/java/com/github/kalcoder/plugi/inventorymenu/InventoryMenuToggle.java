package com.github.kalcoder.plugi.inventorymenu;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryMenuToggle extends InventoryMenuItem {
  
  boolean toggled;
  public String nameOn;
  public String nameOff;
  
  public InventoryMenuToggle(String nameOn, String nameOff, boolean defaultValue) {
    super(new ItemStack(Material.GRAY_DYE), nameOn);
    this.nameOn = nameOn;
    this.nameOff = nameOff;
    this.toggled = defaultValue;
  }
  
  void toggle() {
    toggled = !toggled;
    
    if (toggled) {
      this.item.setType(Material.LIME_DYE);
      ItemMeta itemMeta = this.item.getItemMeta();
      itemMeta.setDisplayName(nameOn);
      this.item.setItemMeta(itemMeta);
    }
    else {
      this.item.setType(Material.GRAY_DYE);
      ItemMeta itemMeta = this.item.getItemMeta();
      System.out.println(itemMeta.getDisplayName());
      System.out.println(nameOff);
      itemMeta.setDisplayName(nameOff);
      System.out.println(itemMeta.getDisplayName());
      this.item.setItemMeta(itemMeta);
    }
  }
  
  @Override
  public void onClick(Player p) {
    toggle();
    super.onClick(p);
  }
}
