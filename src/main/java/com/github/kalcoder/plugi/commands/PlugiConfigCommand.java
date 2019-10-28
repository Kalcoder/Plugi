package com.github.kalcoder.plugi.commands;

import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.inventorymenu.InventoryMenuSettingsToggle;
import com.github.kalcoder.plugi.inventorymenu.InventoryMenuToggle;
import com.github.kalcoder.plugi.inventorymenu.InventoryMenuType;
import com.github.kalcoder.plugi.util.ChatHelper;
import com.github.kalcoder.plugi.util.InventoryMenuHelper;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlugiConfigCommand extends BaseCommand implements IPlayerCommand {
  
  public PlugiConfigCommand(Plugi plugi) {
    super("plugiconfig", plugi);
  }
  
  @Override
  void execute(CommandSender sender, Command command, String label, String[] args) {
    InventoryMenuHelper.generateInventoryMenu(plugi, "Plugi Settings", InventoryMenuType.OPTIONS,
            new InventoryMenuSettingsToggle(ChatHelper.translateColors("&5Join Message"), Material.WRITABLE_BOOK, plugi.settings.findSetting("Use Join Message")),
            new InventoryMenuSettingsToggle(ChatHelper.translateColors("&5Leave Message"), Material.WRITABLE_BOOK, plugi.settings.findSetting("Use Leave Message"))).show((Player) sender);
    
  }
}
