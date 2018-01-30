package ca.adria.licensemanager.io;

import ca.adria.licensemanager.PluginMain;
import ca.adria.licensemanager.license.db.LicenseDatabase;
import org.bukkit.Bukkit;

import java.io.File;

public class PluginData {

    public static LicenseDatabase loadLicenseDatabase() {
        LicenseDatabase db = null;


        File f = new File(PluginMain.DataFolder, File.separator + "");

        return db;
    }
}
