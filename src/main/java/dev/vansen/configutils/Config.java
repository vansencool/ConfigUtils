package dev.vansen.configutils;

import net.kyori.adventure.text.Component;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Config is the helper class that lets you easily manage YAML configuration files.
 */
@SuppressWarnings("unused")
public class Config {
    private final File configFile;
    private FileConfiguration config;

    private Config(String fileName) {
        if (fileName == null) {
            throw new NullPointerException("File name cannot be null");
        }
        this.configFile = new File(ConfigUtils.getPluginInstance().getDataFolder(), fileName);
    }

    /**
     * Loads the configuration file.
     *
     * @param fileName The name of the file.
     * @return A CompletableFuture that will complete with the Config instance.
     */
    public static CompletableFuture<Config> load(String fileName) {
        return CompletableFuture.supplyAsync(() -> {
            Config config = new Config(fileName);
            config.config = YamlConfiguration.loadConfiguration(config.configFile);
            return config;
        });
    }

    /**
     * Saves the configuration file.
     */
    public void save() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the configuration file asynchronously.
     *
     * @return A CompletableFuture that completes when the save is done.
     */
    public CompletableFuture<Void> saveAsync() {
        return CompletableFuture.runAsync(this::save);
    }

    /**
     * Reloads the configuration file.
     */
    public void reload() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    /**
     * Reloads the configuration file asynchronously.
     *
     * @return A CompletableFuture that completes when the reload is done.
     */
    public CompletableFuture<Void> reloadAsync() {
        return CompletableFuture.runAsync(this::reload);
    }

    /**
     * Gets the keys in the configuration.
     *
     * @param deep If true, gets keys in nested sections too.
     * @return A set of keys.
     */
    public Set<String> getKeys(boolean deep) {
        return config.getKeys(deep);
    }

    /**
     * Gets the keys from a specific path in the configuration.
     *
     * @param path The path to look in.
     * @param deep If true, gets keys in nested sections too.
     * @return A set of keys.
     */
    public Set<String> getKeys(String path, boolean deep) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getConfigurationSection(path).getKeys(deep);
    }

    /**
     * Gets a value from the configuration.
     *
     * @param path The path to the value.
     * @param <T>  The type of the value.
     * @return The value.
     */
    public <T> T get(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return (T) config.get(path);
    }

    /**
     * Gets a value from the configuration, or a default if it's not found.
     *
     * @param path The path to the value.
     * @param def  The default value.
     * @param <T>  The type of the value.
     * @return The value or the default.
     */
    public <T> T get(String path, T def) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return (T) config.get(path, def);
    }

    /**
     * Gets a string from the configuration.
     *
     * @param path The path to the string.
     * @return The string.
     */
    public String getString(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getString(path);
    }

    /**
     * Gets a string from the configuration, or a default if it's not found.
     *
     * @param path The path to the string.
     * @param def  The default string.
     * @return The string or the default.
     */
    public String getString(String path, String def) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getString(path, def);
    }

    /**
     * Gets an integer from the configuration.
     *
     * @param path The path to the integer.
     * @return The integer.
     */
    public int getInt(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getInt(path);
    }

    /**
     * Gets an integer from the configuration, or a default if it's not found.
     *
     * @param path The path to the integer.
     * @param def  The default integer.
     * @return The integer or the default.
     */
    public int getInt(String path, int def) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getInt(path, def);
    }

    /**
     * Gets a boolean from the configuration.
     *
     * @param path The path to the boolean.
     * @return The boolean.
     */
    public boolean getBoolean(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getBoolean(path);
    }

    /**
     * Gets a boolean from the configuration, or a default if it's not found.
     *
     * @param path The path to the boolean.
     * @param def  The default boolean.
     * @return The boolean or the default.
     */
    public boolean getBoolean(String path, boolean def) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getBoolean(path, def);
    }

    /**
     * Gets a double from the configuration.
     *
     * @param path The path to the double.
     * @return The double.
     */
    public double getDouble(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getDouble(path);
    }

    /**
     * Gets a double from the configuration, or a default if it's not found.
     *
     * @param path The path to the double.
     * @param def  The default double.
     * @return The double or the default.
     */
    public double getDouble(String path, double def) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getDouble(path, def);
    }

    /**
     * Gets a long from the configuration.
     *
     * @param path The path to the long.
     * @return The long.
     */
    public long getLong(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getLong(path);
    }

    /**
     * Gets a long from the configuration, or a default if it's not found.
     *
     * @param path The path to the long.
     * @param def  The default long.
     * @return The long or the default.
     */
    public long getLong(String path, long def) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getLong(path, def);
    }

    /**
     * Gets a list from the configuration.
     *
     * @param path The path to the list.
     * @return The list.
     */
    public List<?> getList(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getList(path);
    }

    /**
     * Gets a list from the configuration, or a default if it's not found.
     *
     * @param path The path to the list.
     * @param def  The default list.
     * @return The list or the default.
     */
    public List<?> getList(String path, List<?> def) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getList(path, def);
    }

    /**
     * Gets a rich message from the configuration.
     *
     * @param path The path to the rich message.
     * @return The rich message.
     */
    public Component getRichMessage(String path) {
        return config.getRichMessage(path);
    }

    /**
     * Gets a rich message from the configuration, or a default if it's not found.
     *
     * @param path The path to the rich message.
     * @param def  The default rich message.
     * @return The rich message or the default.
     */
    public Component getRichMessage(String path, Component def) {
        return config.getRichMessage(path, def);
    }

    /**
     * Gets a list of strings from the configuration.
     *
     * @param path The path to the list.
     * @return The list of strings.
     */
    public List<String> getStringList(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getStringList(path);
    }

    /**
     * Gets a list of integers from the configuration.
     *
     * @param path The path to the list.
     * @return The list of integers.
     */
    public List<Integer> getIntegerList(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getIntegerList(path);
    }

    /**
     * Gets a list of booleans from the configuration.
     *
     * @param path The path to the list.
     * @return The list of booleans.
     */
    public List<Boolean> getBooleanList(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getBooleanList(path);
    }

    /**
     * Gets a list of doubles from the configuration.
     *
     * @param path The path to the list.
     * @return The list of doubles.
     */
    public List<Double> getDoubleList(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getDoubleList(path);
    }

    /**
     * Gets the name of the configuration file.
     *
     * @return The name of the configuration file.
     */
    public String getName() {
        return config.getName();
    }

    /**
     * Gets the current path of the configuration.
     *
     * @return The current path of the configuration.
     */
    public String getCurrentPath() {
        return config.getCurrentPath();
    }

    /**
     * Checks if the value at the specified path is a configuration section.
     *
     * @param path The path to check.
     * @return True if the value is a configuration section, otherwise false.
     */
    public boolean isConfigurationSection(String path) {
        return config.isConfigurationSection(path);
    }

    /**
     * Checks if the value at the specified path is an ItemStack.
     *
     * @param path The path to check.
     * @return True if the value is an ItemStack, otherwise false.
     */
    public boolean isItemStack(String path) {
        return config.isItemStack(path);
    }

    /**
     * Checks if the value at the specified path is an OfflinePlayer.
     *
     * @param path The path to check.
     * @return True if the value is an OfflinePlayer, otherwise false.
     */
    public boolean isOfflinePlayer(String path) {
        return config.isOfflinePlayer(path);
    }

    /**
     * Checks if the specified path is set in the configuration.
     *
     * @param path The path to check.
     * @return True if the path is set, otherwise false.
     */
    public boolean isSet(String path) {
        return config.isSet(path);
    }

    /**
     * Checks if the value at the specified path is a Vector.
     *
     * @param path The path to check.
     * @return True if the value is a Vector, otherwise false.
     */
    public boolean isVector(String path) {
        return config.isVector(path);
    }

    /**
     * Checks if the value at the specified path is a Location.
     *
     * @param path The path to check.
     * @return True if the value is a Location, otherwise false.
     */
    public boolean isLocation(String path) {
        return config.isLocation(path);
    }

    /**
     * Checks if the value at the specified path is a Color.
     *
     * @param path The path to check.
     * @return True if the value is a Color, otherwise false.
     */
    public boolean isColor(String path) {
        return config.isColor(path);
    }

    /**
     * Checks if the value at the specified path is a String.
     *
     * @param path The path to check.
     * @return True if the value is a String, otherwise false.
     */
    public boolean isString(String path) {
        return config.isString(path);
    }

    /**
     * Checks if the value at the specified path is a double.
     *
     * @param path The path to check.
     * @return True if the value is a double, otherwise false.
     */
    public boolean isDouble(String path) {
        return config.isDouble(path);
    }

    /**
     * Checks if the value at the specified path is an integer.
     *
     * @param path The path to check.
     * @return True if the value is an integer, otherwise false.
     */
    public boolean isInt(String path) {
        return config.isInt(path);
    }

    /**
     * Checks if the value at the specified path is a list.
     *
     * @param path The path to check.
     * @return True if the value is a list, otherwise false.
     */
    public boolean isList(String path) {
        return config.isList(path);
    }

    /**
     * Checks if the value at the specified path is a long.
     *
     * @param path The path to check.
     * @return True if the value is a long, otherwise false.
     */
    public boolean isLong(String path) {
        return config.isLong(path);
    }

    /**
     * Checks if the value at the specified path is a boolean.
     *
     * @param path The path to check.
     * @return True if the value is a boolean, otherwise false.
     */
    public boolean isBoolean(String path) {
        return config.isBoolean(path);
    }

    /**
     * Gets a list of bytes from the configuration.
     *
     * @param path The path to the list.
     * @return The list of bytes.
     */
    public List<Byte> getByteList(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getByteList(path);
    }

    /**
     * Gets a list of shorts from the configuration.
     *
     * @param path The path to the list.
     * @return The list of shorts.
     */
    public List<Short> getShortList(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getShortList(path);
    }

    /**
     * Gets a list of floats from the configuration.
     *
     * @param path The path to the list.
     * @return The list of floats.
     */
    public List<Float> getFloatList(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getFloatList(path);
    }

    /**
     * Gets a list of longs from the configuration.
     *
     * @param path The path to the list.
     * @return The list of longs.
     */
    public List<Long> getLongList(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getLongList(path);
    }

    /**
     * Gets a list of maps from the configuration.
     *
     * @param path The path to the list.
     * @return The list of maps.
     */
    public List<Map<?, ?>> getMapList(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getMapList(path);
    }

    /**
     * Gets a location from the configuration.
     *
     * @param path The path to the location.
     * @return The location.
     */
    public Location getLocation(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getLocation(path);
    }

    /**
     * Gets a location from the configuration, or a default if it's not found.
     *
     * @param path The path to the location.
     * @param def  The default location.
     * @return The location or the default.
     */
    public Location getLocation(String path, Location def) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getLocation(path, def);
    }

    /**
     * Gets an item stack from the configuration.
     *
     * @param path The path to the item stack.
     * @return The item stack.
     */
    public ItemStack getItemStack(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getItemStack(path);
    }

    /**
     * Gets an item stack from the configuration, or a default if it's not found.
     *
     * @param path The path to the item stack.
     * @param def  The default item stack.
     * @return The item stack or the default.
     */
    public ItemStack getItemStack(String path, ItemStack def) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getItemStack(path, def);
    }

    /**
     * Gets a color from the configuration.
     *
     * @param path The path to the color.
     * @return The color.
     */
    public Color getColor(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getColor(path);
    }

    /**
     * Gets a color from the configuration, or a default if it's not found.
     *
     * @param path The path to the color.
     * @param def  The default color.
     * @return The color or the default.
     */
    public Color getColor(String path, Color def) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getColor(path, def);
    }

    /**
     * Gets the configuration comments for a given path.
     *
     * @param path The path to the section.
     * @return The comments.
     */
    public List<String> getComments(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getComments(path);
    }

    /**
     * Sets a value in the configuration.
     *
     * @param path  The path to the value.
     * @param value The value to set.
     */
    public void set(String path, Object value) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        config.set(path, value);
    }

    /**
     * Creates a new section in the configuration.
     *
     * @param path The path to the section.
     * @return The new section.
     */
    public ConfigurationSection createSection(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.createSection(path);
    }

    /**
     * Creates a new section in the configuration with initial values.
     *
     * @param path   The path to the section.
     * @param values The initial values for the section.
     * @return The new section.
     */
    public ConfigurationSection createSection(String path, Map<?, ?> values) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.createSection(path, values);
    }

    /**
     * Gets a configuration section from the configuration.
     *
     * @param path The path to the section.
     * @return The section.
     */
    public ConfigurationSection getConfigurationSection(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getConfigurationSection(path);
    }

    /**
     * Adds default values to the configuration.
     *
     * @param defaults The default values.
     */
    public void addDefaults(Map<String, Object> defaults) {
        if (defaults == null) throw new NullPointerException("Defaults cannot be null");
        config.addDefaults(defaults);
    }

    /**
     * Turns on or off copying of default values to the configuration.
     *
     * @param value If true, copies default values.
     */
    public void optionsCopyDefaults(boolean value) {
        config.options().copyDefaults(value);
    }

    /**
     * Sets the header of the configuration.
     *
     * @param header The header text.
     */
    public void optionsHeader(String header) {
        config.options().header(header);
    }

    /**
     * Turns on or off copying of the header to the configuration.
     *
     * @param value If true, copies the header.
     */
    public void optionsCopyHeader(boolean value) {
        config.options().copyHeader(value);
    }

    /**
     * Gets the header of the configuration.
     *
     * @return The header text.
     */
    public String optionsHeader() {
        return config.options().header();
    }

    /**
     * Checks if copying defaults is turned on.
     *
     * @return True if copying defaults is turned on.
     */
    public boolean optionsCopyDefaults() {
        return config.options().copyDefaults();
    }

    /**
     * Turns on or off parsing of comments in the configuration.
     *
     * @param value If true (by default), parses comments.
     */
    public void optionsParseComments(boolean value) {
        config.options().parseComments(value);
    }

    /**
     * Checks if parsing comments is turned on.
     *
     * @return True if parsing comments is turned on.
     */
    public boolean optionsParseComments() {
        return config.options().parseComments();
    }
}