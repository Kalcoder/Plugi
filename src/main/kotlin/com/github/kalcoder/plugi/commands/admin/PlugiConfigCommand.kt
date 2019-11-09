package com.github.kalcoder.plugi.commands.admin;

import com.github.kalcoder.plugi.Plugi
import com.github.kalcoder.plugi.Settings.Setting
import com.github.kalcoder.plugi.commands.BaseCommand
import com.github.kalcoder.plugi.commands.IClientCommand
import com.github.kalcoder.plugi.inventorymenu.InventoryMenuSettingsToggle
import com.github.kalcoder.plugi.util.ChatUtil
import com.github.kalcoder.plugi.util.InventoryMenuUtil
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PlugiConfigCommand(plugi: Plugi) : BaseCommand("plugiconfig", plugi), IClientCommand {

  override fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>) {
    InventoryMenuUtil.generateOptionsInventoryMenu(plugi, "Plugi Settings",
            InventoryMenuSettingsToggle(ChatUtil.translateColors("&5Join Message"), Material.WRITABLE_BOOK, plugi.settings!!.findSetting("usejoinmessage") as Setting<Boolean>),
            InventoryMenuSettingsToggle(ChatUtil.translateColors("&5Leave Message"), Material.WRITABLE_BOOK, plugi.settings!!.findSetting("useleavemessage") as Setting<Boolean>))
      .show(sender as Player);
    
  }

}
