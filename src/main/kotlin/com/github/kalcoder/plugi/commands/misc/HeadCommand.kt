package com.github.kalcoder.plugi.commands.misc

import com.github.kalcoder.plugi.Plugi
import com.github.kalcoder.plugi.commands.BaseCommand
import com.github.kalcoder.plugi.commands.IClientCommand
import com.github.kalcoder.plugi.util.ChatUtil
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta

class HeadCommand(plugi: Plugi) : BaseCommand("head", plugi), IClientCommand {
  override fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>) {
    if (args.isEmpty()) {
      sender.sendMessage(ChatUtil.translateColors("&CError: You must input a player!"))
      return
    }

    var p: OfflinePlayer? = null

    for (player in plugi.server.offlinePlayers) {
      if (player.name!!.equals(args[0], ignoreCase = true)) {
        p = player
        break
      }
    }

    if (p == null) {
      sender.sendMessage(ChatUtil.translateColors("&cError: The player you have inputted does not exist!"))
      return
    }

    val itemStack = ItemStack(Material.PLAYER_HEAD)
    val skullMeta = itemStack.itemMeta as SkullMeta
    skullMeta.owningPlayer = p
    itemStack.itemMeta = skullMeta
    val player = sender as Player
    player.inventory.addItem(itemStack)
  }
}