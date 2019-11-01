package com.github.kalcoder.plugi.commands.admin;

import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.commands.BaseCommand;
import com.github.kalcoder.plugi.commands.CommandPermission;
import com.github.kalcoder.plugi.commands.IRequirePermission;
import com.github.kalcoder.plugi.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SudoCommand extends BaseCommand implements IRequirePermission {
  
  public SudoCommand(Plugi plugi) {
    super("sudo", plugi);
    permissions.add(CommandPermission.ADMIN_SUDO);
  }
  
  @Override
  public void execute(CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 0) {
      sender.sendMessage(ChatUtil.translateColors("&cError: You must input a player name!"));
      return;
    } if (args.length == 1) {
      sender.sendMessage(ChatUtil.translateColors("&cError: You must give a command or chat message!"));
      return;
    }
    
    Player p = null;
    
    for (Player player :
            plugi.getServer().getOnlinePlayers()) {
      if (player.getName().equalsIgnoreCase(args[0])) {
        p = player;
        break;
      }
    }
  
    if (p == null) {
      sender.sendMessage(ChatUtil.translateColors("&cError: The player you have entered either doesn't exist or isn't online! Please check your spelling and try again!"));
      return;
    }
    
    if (args[1].startsWith("/")) {
      args[1] = args[1].substring(1);
      String[] commandMessage = Arrays.copyOfRange(args, 1, args.length);
      p.performCommand(String.join(" ", commandMessage));
    } else {
      String[] chatMessage = Arrays.copyOfRange(args, 1, args.length);
      p.chat(String.join(" ", chatMessage));
    }
    
  }
}
