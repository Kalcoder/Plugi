package com.github.kalcoder.plugi.errors;

public class ConfigurationNotFoundException extends Exception {
  public ConfigurationNotFoundException() {
    super();
  }
  
  public ConfigurationNotFoundException(String name) {
    super("Configuration \"" + name + ".yml\" was not found!");
  }
  
  public ConfigurationNotFoundException(String name, Throwable cause) {
    super("Configuration \"" + name + ".yml\" was not found!", cause);
  }
  
  public ConfigurationNotFoundException(Throwable cause) {
    super(cause);
  }
  
  protected ConfigurationNotFoundException(String name, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super("Configuration \"" + name + ".yml\" was not found!", cause, enableSuppression, writableStackTrace);
  }
}
