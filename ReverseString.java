/**Reverses a string.
 *
 * @author Channing Kimble-Brown
 */
public class ReverseString {

    /**Protective constructor.*/
    private ReverseString() {

    }

    /**Reverses a string.
     *
     * @param stringToReverse the string that will be reversed
     * @return the reversed string
     */
    public String reverseString(String stringToReverse) {
        String reversedString = "";

        for (int i = stringToReverse.length() - 1; i >= 0; i--) {
            reversedString += stringToReverse.charAt(i);
        }

        return reversedString;
    }

}
