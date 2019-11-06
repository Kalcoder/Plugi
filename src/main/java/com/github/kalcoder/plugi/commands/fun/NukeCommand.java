/*
  Author - Kalcoder
  Date - 2019-11-05
  Time - 9:41 PM
*/
package com.github.kalcoder.plugi.commands.fun;

import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.commands.BaseCommand;
import com.github.kalcoder.plugi.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NukeCommand extends BaseCommand {
  
  public NukeCommand(Plugi plugi) {
    super("nuke", plugi);
  }
  
  @Override
  public void execute(CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 0) {
      sender.sendMessage(ChatUtil.translateColors("&cError: You must input a player!"));
      return;
    }
    
    Player p = null;
    
    for (Player player : plugi.getServer().getOnlinePlayers()) {
      if (args[0].equalsIgnoreCase(player.getName())) {
        p = player;
        break;
      }
    }
    
    if (p == null) {
      sender.sendMessage(ChatUtil.translateColors("&cError: The player you have entered is not online! Please check your spelling and try again!"));
      return;
    }
    
    p.getLocation().createExplosion(100f, false, false);
    
  }
}
