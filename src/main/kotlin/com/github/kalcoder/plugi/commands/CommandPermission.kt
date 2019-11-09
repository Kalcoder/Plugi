package com.github.kalcoder.plugi.commands

import org.bukkit.permissions.Permission

enum class CommandPermission {

  //  Admin
  ADMIN_ALL,
  ADMIN_PLUGICONFIG,
  ADMIN_SUDO,

  //  Players
  PLAYERS_ALL,
  PLAYERS_INVSEE;

  val permission: Permission

  init {
    val permissionName = name.toLowerCase().replace("_".toRegex(), ".").replace("all".toRegex(), "*")
    println(permissionName)
    permission = Permission(permissionName)
  }

}