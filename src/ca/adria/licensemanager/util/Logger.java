package ca.adria.licensemanager.util;

import org.bukkit.Bukkit;
import java.util.logging.Level;

public class Logger {

    public static void Info(String msg) {
        Bukkit.getLogger().log(Level.INFO, "[LicenseManager] " + msg);
    }

}
