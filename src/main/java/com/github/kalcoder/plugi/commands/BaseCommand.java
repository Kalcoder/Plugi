package com.github.kalcoder.plugi.commands;

import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.util.ChatHelper;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class BaseCommand implements CommandExecutor {
  
  final String name;
  final Plugi plugi;
  
  BaseCommand(String name, Plugi plugi) {
    plugi.getCommand(name).setExecutor(this);
    this.name = name;
    this.plugi = plugi;
  }
  
  /**
   * Executes the given command, returning its success.
   * <br>
   * If false is returned, then the "usage" plugin.yml entry for this command
   * (if defined) will be sent to the player.
   *
   * @param sender  Source of the command
   * @param command Command which was executed
   * @param label   Alias of the command which was used
   * @param args    Passed command arguments
   * @return true if a valid command, otherwise false
   */
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    
    if (this instanceof IPlayerCommand && !(sender instanceof Player)) {
      sender.sendMessage(ChatHelper.translateColors("&cError while executing command: Only a player can use this command!"));
      return true;
    }
    if (this instanceof IConsoleCommand && !(sender instanceof ConsoleCommandSender)) {
      sender.sendMessage(ChatHelper.translateColors("&cError while executing command: Only the console can use this command!"));
      return true;
    }
    if (this instanceof IRequirePermission) {
      System.out.println(((IRequirePermission) this).permissions);
      System.out.println(sender.getEffectivePermissions());
      for (CommandPermission p :
              ((IRequirePermission) this).permissions) {
        if (!sender.hasPermission(p.getPermission())) {
          sender.sendMessage(ChatHelper.translateColors("&cError while executing command: You are missing the required permission " + p.getPermission().getName() + "!"));
          return true;
        }
      }
    }
    
    execute(sender, command, label, args);
    return true;
  }
  
  abstract void execute(CommandSender sender, Command command, String label, String[] args);
  
}
