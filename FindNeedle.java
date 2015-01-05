import java.util.Collection;

/**Finds a needle in a haystack (a specific word in a list
 * of a bunch of words).
 * @author Channing Kimble-Brown
 */
public class FindNeedle {

    /**Protective constructor.*/
    private FindNeedle() {

    }

    /**Extracts a needle from a haystack in the form of a
     * POSTed list (for example: "first", "second", "third") all
     * in one String.
     * @param hay the haystack
     * @param needle the needle
     * @return the position of the needle
     */
    public int findNeedle(String hay, String needle) {
        String newEl = ""; // new element

        int index = -1;

        while (!hay.equals("\"")) {
            index++;
            int comma = hay.indexOf(',') - 1;
            newEl = hay.substring(1, comma);
            if (newEl.equals(needle)) {
                break;
            }
            hay = hay.substring(comma+2);
        }

        return index;
    }

    /**Extracts a needle from a haystack in the form of an
     * array that can be iterated.
     * @param hay the haystack
     * @param needle the needle
     * @return the position of the needle
     */
    public int findNeedle(String[] haystack, String needle) {
        int index = -1;

        for (String h : haystack) {
            index++;
            if (h.equals(needle)) {
                break;
            }
        }

        return index;
    }

    /**Extracts a needle from a haystack in the form of a
     * Collection that can be iterated.
     * @param hay the haystack
     * @param needle the needle
     * @return the position of the needle
     */
    public int findNeedle(Collection<String> haystack, String needle) {
        int index = -1;

        for (String h : haystack) {
            index++;
            if (h.equals(needle)) {
                break;
            }
        }

        return index;
    }
}
