package com.github.kalcoder.plugi.commands;

import org.bukkit.permissions.Permission;

public enum CommandPermission {
  
  ADMIN_ALL,
  ADMIN_WHITELIST;
  
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
