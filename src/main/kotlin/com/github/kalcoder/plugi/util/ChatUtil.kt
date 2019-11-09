package com.github.kalcoder.plugi.util

import org.bukkit.ChatColor

object ChatUtil {

  @JvmStatic
  fun translateColors(message: String): String {
    return ChatColor.translateAlternateColorCodes('&', message)
  }

}