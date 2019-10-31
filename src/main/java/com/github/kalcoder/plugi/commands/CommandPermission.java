package com.github.kalcoder.plugi.commands;

import org.bukkit.permissions.Permission;

public enum CommandPermission {

//  Admin
  ADMIN_ALL,
  ADMIN_PLUGICONFIG,
  ADMIN_SUDO,
  
//  Players
  PLAYERS_ALL,
  PLAYERS_INVSEE;
  
  private Permission permission;
  
  CommandPermission() {
    String permissionName = name().toLowerCase().replaceAll("_", ".").replaceAll("all", "*");
    System.out.println(permissionName);
    permission = new Permission(permissionName);
  }
  
  public Permission getPermission() {
    return permission;
  }
}
