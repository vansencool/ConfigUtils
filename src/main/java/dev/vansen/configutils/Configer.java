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
 * Configer is the helper class that lets you easily manage YAML configuration files.
 */
@SuppressWarnings("unused")
public class Configer {
    private final File configFile;
    private FileConfiguration config;

    private Configer(String fileName) {
        if (fileName == null) {
            throw new NullPointerException("File name cannot be null");
        }
        this.configFile = new File(ConfigUtils.getPluginInstance().getDataFolder(), fileName);
    }

    /**
     * Loads the configuration file.
     *
     * @param fileName The name of the file.
     * @return The Configer instance.
     * @throws NullPointerException If the file name is {@code null}.
     */
    public static Configer load(String fileName) {
        Configer configer = new Configer(fileName);
        configer.config = YamlConfiguration.loadConfiguration(configer.configFile);
        return configer;
    }

    /**
     * Loads the configuration file asynchronously.
     *
     * @param fileName The name of the file.
     * @return A CompletableFuture that will complete with the Configer instance.
     * @throws NullPointerException If the file name is {@code null}.
     */
    public static CompletableFuture<Configer> loadAsync(String fileName) {
        return CompletableFuture.supplyAsync(() -> load(fileName));
    }

    /**
     * Saves the configuration file.
     */
    public void save() {
        try {
            config.save(configFile);
        } catch (final IOException e) {
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
     */
    public Component getRichMessage(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getRichMessage(path);
    }

    /**
     * Gets a rich message from the configuration, or a default if it's not found.
     *
     * @param path The path to the rich message.
     * @param def  The default rich message.
     * @return The rich message or the default.
     * @throws NullPointerException If the path is {@code null}.
     */
    public Component getRichMessage(String path, Component def) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getRichMessage(path, def);
    }

    /**
     * Gets a list of strings from the configuration.
     *
     * @param path The path to the list.
     * @return The list of strings.
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
     */
    public boolean isConfigurationSection(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.isConfigurationSection(path);
    }

    /**
     * Checks if the value at the specified path is an ItemStack.
     *
     * @param path The path to check.
     * @return True if the value is an ItemStack, otherwise false.
     * @throws NullPointerException If the path is {@code null}.
     */
    public boolean isItemStack(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.isItemStack(path);
    }

    /**
     * Checks if the value at the specified path is an OfflinePlayer.
     *
     * @param path The path to check.
     * @return True if the value is an OfflinePlayer, otherwise false.
     * @throws NullPointerException If the path is {@code null}.
     */
    public boolean isOfflinePlayer(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.isOfflinePlayer(path);
    }

    /**
     * Checks if the specified path is set in the configuration.
     *
     * @param path The path to check.
     * @return True if the path is set, otherwise false.
     * @throws NullPointerException If the path is {@code null}.
     */
    public boolean isSet(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.isSet(path);
    }

    /**
     * Checks if the value at the specified path is a Vector.
     *
     * @param path The path to check.
     * @return True if the value is a Vector, otherwise false.
     * @throws NullPointerException If the path is {@code null}.
     */
    public boolean isVector(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.isVector(path);
    }

    /**
     * Checks if the value at the specified path is a Location.
     *
     * @param path The path to check.
     * @return True if the value is a Location, otherwise false.
     * @throws NullPointerException If the path is {@code null}.
     */
    public boolean isLocation(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.isLocation(path);
    }

    /**
     * Checks if the value at the specified path is a Color.
     *
     * @param path The path to check.
     * @return True if the value is a Color, otherwise false.
     * @throws NullPointerException If the path is {@code null}.
     */
    public boolean isColor(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.isColor(path);
    }

    /**
     * Checks if the value at the specified path is a String.
     *
     * @param path The path to check.
     * @return True if the value is a String, otherwise false.
     * @throws NullPointerException If the path is {@code null}.
     */
    public boolean isString(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.isString(path);
    }

    /**
     * Checks if the value at the specified path is a double.
     *
     * @param path The path to check.
     * @return True if the value is a double, otherwise false.
     * @throws NullPointerException If the path is {@code null}.
     */
    public boolean isDouble(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.isDouble(path);
    }

    /**
     * Checks if the value at the specified path is an integer.
     *
     * @param path The path to check.
     * @return True if the value is an integer, otherwise false.
     * @throws NullPointerException If the path is {@code null}.
     */
    public boolean isInt(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.isInt(path);
    }

    /**
     * Checks if the value at the specified path is a list.
     *
     * @param path The path to check.
     * @return True if the value is a list, otherwise false.
     * @throws NullPointerException If the path is {@code null}.
     */
    public boolean isList(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.isList(path);
    }

    /**
     * Checks if the value at the specified path is a long.
     *
     * @param path The path to check.
     * @return True if the value is a long, otherwise false.
     * @throws NullPointerException If the path is {@code null}.
     */
    public boolean isLong(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.isLong(path);
    }

    /**
     * Checks if the value at the specified path is a boolean.
     *
     * @param path The path to check.
     * @return True if the value is a boolean, otherwise false.
     * @throws NullPointerException If the path is {@code null}.
     */
    public boolean isBoolean(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.isBoolean(path);
    }

    /**
     * Gets a list of bytes from the configuration.
     *
     * @param path The path to the list.
     * @return The list of bytes.
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
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
     * @throws NullPointerException If the path is {@code null}.
     */
    public ConfigurationSection getConfigurationSection(String path) {
        if (path == null) throw new NullPointerException("Path cannot be null");
        return config.getConfigurationSection(path);
    }

    /**
     * Adds default values to the configuration.
     *
     * @param defaults The default values.
     * @throws NullPointerException If the defaults are {@code null}.
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