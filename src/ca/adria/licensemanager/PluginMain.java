package ca.adria.licensemanager;

import ca.adria.licensemanager.event.BookListener;
import ca.adria.licensemanager.util.Logger;
import ca.adria.licensemanager.license.Examine;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class PluginMain extends JavaPlugin implements CommandExecutor {

    public static File DataFolder;

    @Override
    public void onEnable() {
        Logger.Info("Starting up.");

        // Register events
        PluginManager pluginMgr = this.getServer().getPluginManager();
        pluginMgr.registerEvents(new BookListener(), this);

        DataFolder = getDataFolder();
    }

    @Override
    public void onDisable() {
        Logger.Info("Shutting down.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("lmgr")) {
            if (sender instanceof Player) {
                // Execute player-only commands
                switch (args[0]) {
                    case "examine":
                        Examine.isLicense((Player)sender);
                        break;
                    default:
                        sender.sendMessage(ChatColor.YELLOW + "Unknown command!");
                        break;
                }
            }
            return true;
        }
        return false;
    }
}
