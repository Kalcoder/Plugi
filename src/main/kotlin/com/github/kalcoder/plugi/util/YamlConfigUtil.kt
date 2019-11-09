package com.github.kalcoder.plugi.util

import com.github.kalcoder.plugi.Plugi
import com.github.kalcoder.plugi.errors.ConfigurationNotFoundException
import org.bukkit.ChatColor
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File
import java.io.IOException
import java.util.*

object YamlConfigUtil {

  private val configurations = HashMap<YamlConfiguration, File>()

  //region File Management
  @JvmStatic
  fun loadAllConfigs(plugi: Plugi) {
    if (!plugi.dataFolder.exists()) {
      println("Making data folder!")
      plugi.dataFolder.mkdir()
    }
    if (plugi.dataFolder.listFiles() == null) return
    println("Files were found!")

    for (file in plugi.dataFolder.listFiles()!!) {
      println(file.absolutePath)
      val configuration = YamlConfiguration.loadConfiguration(file)
      configurations[configuration] = file
    }
  }

  @JvmStatic
  fun createNewConfig(configName: String, plugi: Plugi) {
    val configFile = File(plugi.dataFolder, "$configName.yml")

    if (configFile.exists()) {
      plugi.server.consoleSender.sendMessage(ChatColor.RED.toString() + "Error: Could not create new config called " + configName + " because it already exists!")
      return
    }

    try {
      configFile.createNewFile()
    } catch (e: IOException) {
      e.printStackTrace()
    }

    configurations[YamlConfiguration.loadConfiguration(configFile)] = configFile
  }

  @JvmStatic
  fun configurationExists(configName: String, plugi: Plugi): Boolean {
    val configFile = File(plugi.dataFolder, "$configName.yml")
    return configFile.exists()
  }
  //endregion

  //region Config Management
  @JvmStatic
  @Throws(ConfigurationNotFoundException::class)
  fun getAllKeysFromConfig(name: String, deep: Boolean): Set<String> {
    for (file in configurations.values) {
      println("file.getName() = " + file.name.substring(0, file.name.length - 4))
      if (file.name.substring(0, file.name.length - 4).equals(name, ignoreCase = true)) return YamlConfiguration.loadConfiguration(file).getKeys(deep)
    }
    throw ConfigurationNotFoundException(name)
  }

  @JvmStatic
  @Throws(ConfigurationNotFoundException::class)
  fun readFromConfig(name: String, path: String): Any? {
    for (file in configurations.values) {
      if (file.name.substring(0, file.name.length - 4).equals(name, ignoreCase = true)) return YamlConfiguration.loadConfiguration(file).get(path)
    }
    throw ConfigurationNotFoundException(name)
  }

  @JvmStatic
  fun readFromConfig(config: YamlConfiguration, path: String): Any? {
    return config.get(path)
  }

  @JvmStatic
  fun readFromConfig(configFile: File, path: String): Any? {
    return YamlConfiguration.loadConfiguration(configFile).get(path)
  }

  @JvmStatic
  @Throws(ConfigurationNotFoundException::class)
  fun readBooleanFromConfig(name: String, path: String): Boolean {
    for (file in configurations.values) {
      if (file.name.substring(0, file.name.length - 4).equals(name, ignoreCase = true)) return YamlConfiguration.loadConfiguration(file).getBoolean(path)
    }
    throw ConfigurationNotFoundException(name)

  }

  @JvmStatic
  @Throws(ConfigurationNotFoundException::class)
  fun readStringFromConfig(name: String, path: String): String? {
    for (file in configurations.values) {
      if (file.name.substring(0, file.name.length - 4).equals(name, ignoreCase = true)) return YamlConfiguration.loadConfiguration(file).getString(path)
    }
    throw ConfigurationNotFoundException(name)

  }

  @JvmStatic
  @Throws(ConfigurationNotFoundException::class)
  fun readIntFromConfig(name: String, path: String): Int {
    for (file in configurations.values) {
      if (file.name.substring(0, file.name.length - 4).equals(name, ignoreCase = true)) return YamlConfiguration.loadConfiguration(file).getInt(path)
    }
    throw ConfigurationNotFoundException(name)

  }

  @JvmStatic
  @Throws(ConfigurationNotFoundException::class)
  fun setInConfig(name: String, path: String, value: Any) {
    for (file in configurations.values) {
      if (file.name.substring(0, file.name.length - 4).equals(name, ignoreCase = true)) {
        val config = YamlConfiguration.loadConfiguration(file)
        config.set(path, value)
        try {
          config.save(file)
        } catch (e: IOException) {
          e.printStackTrace()
        }

      }
    }
    throw ConfigurationNotFoundException(name)
  }

  //endregion

}