package com.github.kalcoder.plugi.commands

import com.github.kalcoder.plugi.Plugi
import com.github.kalcoder.plugi.util.ChatUtil
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

abstract class BaseCommand(val name: String, val plugi: Plugi) : CommandExecutor {

  init {
    plugi.getCommand(name)?.setExecutor(this)
  }

  /**
   * Executes the given command, returning its success.
   * <br></br>
   * If false is returned, then the "usage" plugin.yml entry for this command
   * (if defined) will be sent to the player.
   *
   * @param sender Source of the command
   * @param command Command which was executed
   * @param label Alias of the command which was used
   * @param args Passed command arguments
   * @return true if a valid command, otherwise false
   */
  override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
    if (this is IClientCommand && sender !is Player) {
      sender.sendMessage(ChatUtil.translateColors("&cError while executing command: Only a player can use this command!"))
      return true
    }
    if (this is IConsoleCommand && sender !is ConsoleCommandSender) {
      sender.sendMessage(ChatUtil.translateColors("&cError while executing command: Only the console can use this command!"))
      return true
    }
//    if (this is IRequirePermission) {
//      println((this as IRequirePermission).permissions)
//      println(sender.effectivePermissions)
//      for (p in (this as IRequirePermission).permissions) {
//        if (!sender.hasPermission(p.permission)) {
//          sender.sendMessage(ChatUtil.translateColors("&cError while executing command: You are missing the required permission " + p.getPermission().getName() + "!"))
//          return true
//        }
//      }
//    }

    execute(sender, command, label, args)
    return true
  }

  abstract fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>)
}