package dev.vansen.configutils;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.CompletableFuture;

/**
 * This class holds a instance of JavaPlugin
 */
public class ConfigUtils {
    /**
     * Stores the instance of the JavaPlugin that is being managed by this class.
     */
    private static JavaPlugin plugin;

    /**
     * Initializes the {@code ConfigUtils} with the given {@link JavaPlugin} instance.
     * This method sets the static {@link #plugin} field to the provided plugin instance.
     *
     * @param plugin The {@link JavaPlugin} instance to be managed by this utility class.
     * @throws NullPointerException If the provided plugin is {@code null}
     */
    public static void init(JavaPlugin plugin) {
        if (plugin == null) {
            throw new NullPointerException("Plugin cannot be null");
        }
        ConfigUtils.plugin = plugin;
    }

    /**
     * Retrieves the {@link JavaPlugin} instance that was previously initialized with the {@link #init(JavaPlugin)} method.
     * <p>
     * This method returns the currently stored {@link JavaPlugin} instance. If the instance has not been initialized,
     * calling this method will result in a {@link IllegalStateException} being thrown.
     *
     * @return The {@link JavaPlugin} instance that was initialized with the {@link #init(JavaPlugin)} method
     * @throws IllegalStateException If the {@link JavaPlugin} instance has not been initialized
     */
    public static JavaPlugin getPluginInstance() {
        if (plugin == null) {
            throw new IllegalStateException("Something tried to get the plugin instance while its not set");
        }
        return plugin;
    }

    /**
     * Asynchronously loads a configuration file based on the provided file name.
     * <p>
     * This method delegates the actual loading operation to the {@link Config#load(String)} method and returns a {@link CompletableFuture}
     * that completes with the loaded {@link Config} object once the operation is finished.
     *
     * @param fileName The name of the configuration file to load.
     * @return A {@link CompletableFuture} that completes with the loaded {@link Config} object.
     */
    public static CompletableFuture<Config> get(String fileName) {
        return Config.load(fileName);
    }
}