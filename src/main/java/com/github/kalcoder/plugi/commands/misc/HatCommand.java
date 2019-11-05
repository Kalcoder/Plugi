package com.github.kalcoder.plugi.commands.misc;

import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.commands.BaseCommand;
import com.github.kalcoder.plugi.commands.IPlayerCommand;
import com.github.kalcoder.plugi.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HatCommand extends BaseCommand implements IPlayerCommand {
  public HatCommand(Plugi plugi) {
    super("hat", plugi);
  }
  
  @Override
  public void execute(CommandSender sender, Command command, String label, String[] args) {
    Player p = (Player) sender;
    
    if (p.getInventory().getItemInMainHand().getType() == Material.AIR) {
      sender.sendMessage(ChatUtil.translateColors("&cError: You must have an item in your hand!"));
      return;
    }
    
    p.getInventory().setHelmet(p.getInventory().getItemInMainHand());
    p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
    
    
  }
}
