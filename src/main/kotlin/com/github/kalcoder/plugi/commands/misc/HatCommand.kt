package com.github.kalcoder.plugi.commands.misc

import com.github.kalcoder.plugi.Plugi
import com.github.kalcoder.plugi.commands.BaseCommand
import com.github.kalcoder.plugi.commands.IClientCommand
import com.github.kalcoder.plugi.util.ChatUtil
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class HatCommand(plugi: Plugi) : BaseCommand("hat".toLowerCase(), plugi), IClientCommand {
  override fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>) {
    val p: Player = sender as Player

    if (p.inventory.itemInMainHand.type.isAir) {
      sender.sendMessage(ChatUtil.translateColors("&cError: You must hold the item to wear as a hat!"))
      return
    }

    val helmet = p.inventory.helmet
    p.inventory.helmet = p.inventory.itemInMainHand
    p.inventory.setItemInMainHand(helmet)
  }
}