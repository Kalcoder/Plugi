package com.github.kalcoder.plugi.commands.misc;

import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.commands.BaseCommand;
import com.github.kalcoder.plugi.commands.IClientCommand;
import com.github.kalcoder.plugi.util.ChatUtil;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadCommand extends BaseCommand implements IClientCommand {
  
  public HeadCommand(Plugi plugi) {
    super("head", plugi);
  }
  
  @Override
  public void execute(CommandSender sender, Command command, String label, String[] args) {
    if (args.length == 0) {
      sender.sendMessage(ChatUtil.translateColors("&CError: You must input a player!"));
      return;
    }
    
    OfflinePlayer p = null;
    
    for (OfflinePlayer player :
            plugi.getServer().getOfflinePlayers()) {
      if (player.getName().equalsIgnoreCase(args[0])) {
        p = player;
        break;
      }
    }
    
    if (p == null) {
      sender.sendMessage(ChatUtil.translateColors("&cError: The player you have inputted does not exist!"));
      return;
    }
  
    ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
    SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
    skullMeta.setOwningPlayer(p);
    itemStack.setItemMeta(skullMeta);
    Player player = (Player) sender;
    player.getInventory().addItem(itemStack);
  }
}
