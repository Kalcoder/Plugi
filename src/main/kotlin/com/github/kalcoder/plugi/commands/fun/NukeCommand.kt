package com.github.kalcoder.plugi.commands.`fun`

import com.github.kalcoder.plugi.Plugi
import com.github.kalcoder.plugi.commands.BaseCommand
import com.github.kalcoder.plugi.util.ChatUtil
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

class NukeCommand(plugi: Plugi) : BaseCommand("nuke", plugi) {
  override fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>) {
    if (args.isEmpty()) {
      sender.sendMessage(ChatUtil.translateColors("&c:Error: You must input a player!"))
    }

    for (p in plugi.server.onlinePlayers) {
      if (p.name.equals(args[0], ignoreCase = true)) {
        p.location.createExplosion(100f, false, false)
        break
      }
    }
  }
}