package ca.adria.licensemanager.event;

import ca.adria.licensemanager.util.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookListener implements Listener {

    private static int count = 0;

    @EventHandler
    public void onBookEdit(PlayerEditBookEvent e) {
        Logger.Info("Book edit #" + ++count);
        if (e.isSigning()) {
            BookMeta meta = e.getNewBookMeta();
            String title = meta.getTitle();

            Logger.Info("Signed a book called: " + title);

            List<String> pages = meta.getPages();
            int numPages = pages.size();
            for (int pageNum = 0; pageNum < numPages; ++pageNum) {
                String data = pages.get(pageNum);

                Pattern pCmd = Pattern.compile("\\[(.*?)\\]");
                Matcher mCmd = pCmd.matcher(data);
                int numTags = mCmd.groupCount();

                while (mCmd.find()) {
                    if (mCmd.groupCount() == 0) {
                        break;
                    }
                    String tag = mCmd.group(1);
                    Logger.Info("  Page #" + pageNum + " tag: \"" + tag + "\"");
                }
            }
        }
    }

}
