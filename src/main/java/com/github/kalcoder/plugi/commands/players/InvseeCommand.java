package com.github.kalcoder.plugi.commands.players;

import com.github.kalcoder.plugi.Plugi;
import com.github.kalcoder.plugi.commands.BaseCommand;
import com.github.kalcoder.plugi.commands.CommandPermission;
import com.github.kalcoder.plugi.commands.IClientCommand;
import com.github.kalcoder.plugi.commands.IRequirePermission;
import com.github.kalcoder.plugi.util.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InvseeCommand extends BaseCommand implements IRequirePermission, IClientCommand, Listener {
  
  private Inventory inventory;
  
  public InvseeCommand( Plugi plugi) {
    super("invsee", plugi);
    plugi.getServer().getPluginManager().registerEvents(this, plugi);
    permissions.add(CommandPermission.PLAYERS_INVSEE);
  }
  
  @Override
  public void execute(CommandSender sender, Command command, String label, String[] args) {
    if (args.length < 1) {
      sender.sendMessage(ChatUtil.translateColors("&cError: You must input a player!"));
      return;
    }
    
    Player player = null;
    
    for (Player p:
         plugi.getServer().getOnlinePlayers()) {
      if (p.getName().equalsIgnoreCase(args[0])) {
        player = p;
        break;
      }
    }
  
    if (player != null) {
  
      inventory = Bukkit.createInventory(null, 54, player.getName());
  
      inventory.setItem(2, player.getInventory().getHelmet());
      inventory.setItem(3, player.getInventory().getChestplate());
      inventory.setItem(4, player.getInventory().getItemInOffHand());
      inventory.setItem(5, player.getInventory().getLeggings());
      inventory.setItem(6, player.getInventory().getBoots());
  
      for (int i = 9; i < 18; i++) {
        inventory.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
      }
      
      int index = 18;
  
      player.getInventory();
      
      for (ItemStack item :
              player.getInventory().getStorageContents()) {
        inventory.setItem(index++, item);
      }
  
      ((Player) sender).openInventory(inventory);
  
  
    } else sender.sendMessage(ChatUtil.translateColors("&cError: The player you have entered either doesn't exist or is not online! Please check your spelling and try again"));
    
  }
  
  @EventHandler
  public void onInventoryClick(InventoryClickEvent e) {
    if (e.getInventory().equals(inventory)) e.setCancelled(true);
  }
}
