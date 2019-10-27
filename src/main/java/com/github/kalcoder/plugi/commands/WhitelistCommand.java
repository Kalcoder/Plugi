package com.github.kalcoder.plugi.commands;

import com.github.kalcoder.plugi.CommandPermission;
import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.inventorymenu.InventoryMenu;
import com.github.kalcoder.plugi.inventorymenu.InventoryMenuSettingsToggle;
import com.github.kalcoder.plugi.inventorymenu.InventoryMenuToggle;
import com.github.kalcoder.plugi.inventorymenu.InventoryMenuType;
import com.github.kalcoder.plugi.util.ChatHelper;
import com.github.kalcoder.plugi.util.InventoryMenuHelper;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public class WhitelistCommand extends BaseCommand implements IRequirePermission{
  
  Plugi plugi;
  
  public WhitelistCommand(Plugi plugi) {
    super("test");
    this.plugi = plugi;
    permissions.add(CommandPermission.ADMIN_WHITELIST);
    plugi.getCommand(name).setExecutor(this);
  }
  
  @Override
  void execute(CommandSender sender, Command command, String label, String[] args) {
    InventoryMenu inventoryMenu = InventoryMenuHelper.generateInventoryMenu(plugi,"My Inventory", InventoryMenuType.OPTIONS,
            new InventoryMenuSettingsToggle(ChatHelper.translateColors("&aTest Toggle"), ChatHelper.translateColors("&cTest Toggle"), plugi.settings.findSetting("Whitelist")));
    inventoryMenu.show((Player) sender);
  }
}
