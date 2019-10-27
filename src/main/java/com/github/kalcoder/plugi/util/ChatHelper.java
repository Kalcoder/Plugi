package com.github.kalcoder.plugi.util;

import org.bukkit.ChatColor;

public class ChatHelper {
  
  public static String translateColors(String message) {
    return ChatColor.translateAlternateColorCodes('&', message);
  }
  
}
