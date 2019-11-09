package com.github.kalcoder.plugi.util

import com.github.kalcoder.plugi.Plugi
import com.github.kalcoder.plugi.inventorymenu.InventoryMenuItem
import com.github.kalcoder.plugi.inventorymenu.OptionsInventoryMenu

object InventoryMenuUtil {

  @JvmStatic
  fun generateOptionsInventoryMenu(plugi: Plugi, name: String, vararg items: InventoryMenuItem): OptionsInventoryMenu {
    val inventoryMenu = OptionsInventoryMenu(plugi, name, listOf(*items))
    for (item in items) {
      item.setMenu(inventoryMenu)
    }
    return inventoryMenu
  }

}