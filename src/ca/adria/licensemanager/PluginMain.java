package ca.adria.licensemanager;

import ca.adria.licensemanager.util.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginMain extends JavaPlugin {

    @Override
    public void onEnable() {
        Logger.Info("Starting up.");
    }

    @Override
    public void onDisable() {
        Logger.Info("Shutting down.");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("lmgr")) {
            sender.sendMessage(ChatColor.YELLOW + "License Manager base command.");

            return true;
        }

        return false;
    }

}
