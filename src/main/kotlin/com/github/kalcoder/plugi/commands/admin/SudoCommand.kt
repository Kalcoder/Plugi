package com.github.kalcoder.plugi.commands.admin

import com.github.kalcoder.plugi.Plugi
import com.github.kalcoder.plugi.commands.BaseCommand
import com.github.kalcoder.plugi.util.ChatUtil
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class SudoCommand(plugi: Plugi) : BaseCommand("sudo", plugi) {

  override fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>) {
    if (args.isEmpty()) {
      sender.sendMessage(ChatUtil.translateColors("&cError: You must input a player name!"))
      return
    }
    if (args.size == 1) {
      sender.sendMessage(ChatUtil.translateColors("&cError: You must give a command or chat message!"))
      return
    }

    var p: Player? = null

    for (player in plugi.server.onlinePlayers) {
      if (player.name.equals(args[0], ignoreCase = true)) {
        p = player
        break
      }
    }

    if (p == null) {
      sender.sendMessage(ChatUtil.translateColors("&cError: The player you have entered either doesn't exist or isn't online! Please check your spelling and try again!"))
      return
    }

    if (args[1].startsWith("/")) {
      args[1] = args[1].substring(1)
      val commandMessage = Arrays.copyOfRange(args, 1, args.size)
      p.performCommand(commandMessage.joinToString(" "))
    } else {
      val chatMessage = Arrays.copyOfRange(args, 1, args.size)
      p.chat(chatMessage.joinToString(" "))
    }
  }

}