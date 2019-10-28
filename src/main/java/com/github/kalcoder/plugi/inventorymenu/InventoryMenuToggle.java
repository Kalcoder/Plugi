package com.github.kalcoder.plugi.inventorymenu;

import com.github.kalcoder.plugi.util.ChatHelper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class InventoryMenuToggle extends InventoryMenuItem {
  
  boolean toggled;
  
  public InventoryMenuToggle(String name, Material material, boolean defaultValue) {
    super(new ItemStack(material), name);
    this.toggled = defaultValue;
    updateLore();
  }
  
  private void updateLore() {
    ItemMeta itemMeta = item.getItemMeta();
    List<String> lore = itemMeta.getLore();
    lore = new ArrayList<>();
    lore.add(ChatHelper.translateColors(toggled ? "&f> &6On" : "  &6On"));
    lore.add(ChatHelper.translateColors(toggled ? "&6  Off" : "&f> &6Off"));
    itemMeta.setLore(lore);
    
    item.setItemMeta(itemMeta);
  }
  
  void toggle() {
    toggled = !toggled;
    updateLore();
  }
  
  @Override
  public void onClick(Player p) {
    toggle();
  }
}
