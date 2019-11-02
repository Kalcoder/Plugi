package com.github.kalcoder.plugi.commands.admin;

import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.commands.BaseCommand;
import com.github.kalcoder.plugi.commands.CommandPermission;
import com.github.kalcoder.plugi.commands.IPlayerCommand;
import com.github.kalcoder.plugi.commands.IRequirePermission;
import com.github.kalcoder.plugi.inventorymenu.InventoryMenuSettingsToggle;
import com.github.kalcoder.plugi.util.ChatUtil;
import com.github.kalcoder.plugi.util.InventoryMenuUtil;
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
    InventoryMenuUtil.generateOptionsInventoryMenu(plugi, "Plugi Settings",
            new InventoryMenuSettingsToggle(ChatUtil.translateColors("&5Join Message"), Material.WRITABLE_BOOK, plugi.settings.findSetting("usejoinmessage")),
            new InventoryMenuSettingsToggle(ChatUtil.translateColors("&5Leave Message"), Material.WRITABLE_BOOK, plugi.settings.findSetting("useleavemessage"))).show((Player) sender);
    
  }
}
