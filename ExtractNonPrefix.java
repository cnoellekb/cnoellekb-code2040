import java.util.Collection;
import java.util.LinkedList;

/**
 * Puts all of the words without a specified prefix in a list.
 *
 * @author Channing Kimble-Brown
 */
public class ExtractNonPrefix {

    /** Protective constructor. */
    private ExtractNonPrefix() {

    }

    /**
     * Sorts the prefixless words out of a Collection.
     *
     * @param words
     *            list of words to sort from
     * @param prefix
     *            the prefix we want the words in the final list to not have
     * @return the new list of prefixless words
     */
    public static Collection<String> makeNonPrefixList(
            Collection<String> words, String prefix) {
        // A linked list is best because this way the memory can be dynamically
        // allocated
        Collection<String> newlist = new LinkedList<String>();

        for (String w : words) {
            if (!w.substring(0, prefix.length()).equals(prefix)) {
                newlist.add(w);
            }
        }

        return newlist;
    }

    /**
     * Sorts the prefixless words out of an array of strings.
     *
     * @param words
     *            list of words to sort from
     * @param prefix
     *            the prefix we want the words in the final list to not have
     * @return the new list of prefixless words
     */
    public static Collection<String> makeNonPrefixList(String[] words,
            String prefix) {
        // A linked list is best because this way the memory can be dynamically
        // allocated
        Collection<String> newlist = new LinkedList<String>();

        for (String w : words) {
            if (!w.substring(0, prefix.length()).equals(prefix)) {
                newlist.add(w);
            }
        }

        return newlist;
    }

    /**
     * Sorts the prefixless words out of a list of words in one String. For
     * example: "word1", "word2", "word3".
     *
     * @param words
     *            list of words to sort from
     * @param prefix
     *            the prefix we want the words in the final list to not have
     * @return the new list of prefixless words
     */
    public static Collection<String> makeNonPrefixList(String words,
            String prefix) {
        Collection<String> newlist = new LinkedList<String>();

        while (words.length() > 1) {
            int comma;

            if (words.contains(",")) {
                comma = words.indexOf(',') - 1;
            } else {
                comma = words.length() - 1;
            }

            String newEl = words.substring(1, comma);
            if (!newEl.substring(0, prefix.length()).equals(prefix)) {
                newlist.add(newEl);
            }

            if (comma + 2 < words.length()) {
                words = words.substring(comma + 2);
            } else {
                words = "";
            }
        }

        return newlist;
    }
}
