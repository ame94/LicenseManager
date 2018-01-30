package ca.adria.licensemanager.license;


import ca.adria.licensemanager.util.Logger;
import ca.adria.licensemanager.license.TagType;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A licence object is simply a collection of tags that are defined in a book.
 *
 * Tags may be simple strings, or key:value pairs stored in a dictionary.
 */
public class License {

    private List<String> Tags = new ArrayList<String>();
    private HashMap<String, String> Keys = new HashMap<String, String>();

    /**
     * Examine a string and determine if it is a tag or a key/value pair
     * @param s The string being evaluated
     * @return The tag type
     */
    private TagType evaluateTagType(String s) {
        TagType type = TagType.INVALID;

        Pattern p = Pattern.compile("^[0-9a-z_:]+$");
        Matcher m = p.matcher(s);
        if(m.matches()) {
            int colinCount = s.length() - s.replace(":", "").length();
            if (colinCount == 0) {
                type = TagType.TAG;
            } else if (colinCount == 1) {
                type = TagType.KEY;
            }
        }

        return type;
    }

    /**
     * This method assumes the string passed contains two tokens separated by a colon character.
     * The token on the LEFT is the key.
     * @param s The string in question
     * @return The left
     */
    private String getKey(String s) {
        return s.split(":")[0];
    }

    /**
     * This method assumes the string passed contains two tokens separated by a colon character.
     * The token on the RIGHT is the value.
     * @param s The string in question
     * @return The left
     */
    private String getValue(String s) {
        return s.split(":")[1];
    }

    /**
     * Create a new License object from a book's meta
     * @param meta The meta from the book being parsed.
     */
    public License(BookMeta meta) {
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
                String tag = mCmd.group(1).toLowerCase();
                Logger.Info("  Page #" + pageNum + " tag: \"" + tag + "\"");

                switch (evaluateTagType(tag)) {
                    case TAG:
                        Tags.add(tag);
                        Logger.Info("Added tag: " + tag.toLowerCase());
                        break;
                    case KEY:
                        Keys.put(getKey(tag), getValue(tag));
                        Logger.Info("Added key: " + getKey(tag) + " : " + getValue(tag));
                        break;
                    case INVALID:
                    default:
                        Logger.Info("Invalid tag: " + tag);
                        break;
                }
            }
        }

    }

    /**
     * Check if the license book contains a specific tag
     * @param tag The tag in question
     *
     * @return true if the tag is present
     */
    public boolean hasTag(String tag) {
        for (String t : Tags) {
            if (t.equalsIgnoreCase(tag)) {
                return true;
            }
        }
        return false;
    }

    public int getNumTags() {
        return Tags.size();
    }
}
