package com.github.kalcoder.plugi.commands;

import com.github.kalcoder.plugi.CommandPermission;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public interface IRequirePermission {
  
  List<CommandPermission> permissions = new ArrayList<>();
  
}
