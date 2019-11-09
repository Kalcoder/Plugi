package com.github.kalcoder.plugi.commands.players

import com.github.kalcoder.plugi.Plugi
import com.github.kalcoder.plugi.commands.BaseCommand
import com.github.kalcoder.plugi.commands.IClientCommand
import com.github.kalcoder.plugi.util.ChatUtil
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class InvseeCommand(plugi: Plugi) : BaseCommand("invsee".toLowerCase(), plugi), IClientCommand {

  private lateinit var inventory: Inventory

  override fun execute(sender: CommandSender, command: Command, label: String, args: Array<String>) {
    if (args.isEmpty()) {
      sender.sendMessage(ChatUtil.translateColors("&cError: You must input a player!"))
      return
    }

    val p: Player = sender as Player
    var player: Player? = null

    for (onlinePlayer in plugi.server.onlinePlayers) {
      if (onlinePlayer.name.equals(args[0], ignoreCase = true)) {
        player = onlinePlayer
        break
      }
    }

    if (player == null) {
      sender.sendMessage(ChatUtil.translateColors("&cError: The player you have entered either doesn't exist or is not online! Please check your spelling and try again"))
      return
    }

    inventory = Bukkit.createInventory(null, 54, player.name)

    inventory.setItem(2, player.inventory.helmet)
    inventory.setItem(3, player.inventory.chestplate)
    inventory.setItem(4, player.inventory.itemInOffHand)
    inventory.setItem(5, player.inventory.leggings)
    inventory.setItem(6, player.inventory.boots)

    for (i in 9..17) {
      inventory.setItem(i, ItemStack(Material.GRAY_STAINED_GLASS_PANE))
    }

    var index = 18

    for (item in player.inventory.storageContents) {
      inventory.setItem(index++, item)
    }

    p.openInventory(inventory)
  }

  @EventHandler
  fun onInventoryClick(e: InventoryClickEvent) {
    if (e.inventory == inventory) e.isCancelled = true
  }
}