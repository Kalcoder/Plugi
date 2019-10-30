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
  
  public final String name;
  public final Plugi plugi;
  
  public BaseCommand(String name, Plugi plugi) {
    plugi.getCommand(name).setExecutor(this);
    this.name = name;
    this.plugi = plugi;
  }
  
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
  
  public abstract void execute(CommandSender sender, Command command, String label, String[] args);
  
}
