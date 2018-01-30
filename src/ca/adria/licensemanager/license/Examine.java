package ca.adria.licensemanager.license;

import ca.adria.licensemanager.util.Logger;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class Examine {
    /**
     *
     * @param p The player in question
     * @return is the item the player is hold a license book?
     */
    public static boolean isLicense(Player p) {
        ItemStack item = p.getInventory().getItemInMainHand();

        if (item.getType().toString().contains("WRITTEN_BOOK")) {
            if (!item.hasItemMeta()) {
                // Should not get here.
                p.sendMessage("Internal error; check console.");
                Logger.Info("Written book in hand does not contain item meta!");
            } else {
                License lic = new License((BookMeta)item.getItemMeta());
                if (lic.hasTag("license")) {
                    p.sendMessage("This is a license.");
                } else {
                    p.sendMessage("This is NOT a license!");
                }
            }
        } else {
            // Not a license book
            if (item.getType().toString().contains("AIR")) {
                p.sendMessage("You need to hold a license book to examine it.");
            } else if (item.getType().toString().contains("BOOK_AND_QUILL")) {
                p.sendMessage("This book needs to be signed before it can be made a license.");
            } else {
                p.sendMessage("The item in hand is not a license book.");
            }

        }
        return false;
    }
}
