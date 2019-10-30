package com.github.kalcoder.plugi.commands.admin;

import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.commands.BaseCommand;
import com.github.kalcoder.plugi.commands.CommandPermission;
import com.github.kalcoder.plugi.commands.IPlayerCommand;
import com.github.kalcoder.plugi.commands.IRequirePermission;
import com.github.kalcoder.plugi.inventorymenu.InventoryMenuSettingsToggle;
import com.github.kalcoder.plugi.util.ChatHelper;
import com.github.kalcoder.plugi.util.InventoryMenuHelper;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlugiConfigCommand extends BaseCommand implements IRequirePermission, IPlayerCommand {
  
  public PlugiConfigCommand(Plugi plugi) {
    super("plugiconfig", plugi);
    permissions.add(CommandPermission.ADMIN_PLUGICONFIG);
  }
  
  @Override
  public void execute(CommandSender sender, Command command, String label, String[] args) {
    InventoryMenuHelper.generateOptionsInventoryMenu(plugi, "Plugi Settings",
            new InventoryMenuSettingsToggle(ChatHelper.translateColors("&5Join Message"), Material.WRITABLE_BOOK, plugi.settings.findSetting("Use Join Message")),
            new InventoryMenuSettingsToggle(ChatHelper.translateColors("&5Leave Message"), Material.WRITABLE_BOOK, plugi.settings.findSetting("Use Leave Message"))).show((Player) sender);
    
  }
}
