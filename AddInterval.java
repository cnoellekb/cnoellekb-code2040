import java.util.GregorianCalendar;

/**Adds a specified interval (in seconds) to a date and returns
 * the new date.
 *
 * @author Channing Kimble-Brown
 */
public class AddInterval {
    /**The original datestamp.*/
    private static String datestamp;

    /**The interval in seconds.*/
    private static int interval;

    /**Protective constructor.*/
    private AddInterval() {
    }

    /**Adds interval to datestamp parsed from a string.
     *
     * @param d datestamp
     * @param i interval
     * @return new date
     */
    public static String addInterval(String d, String i) {
        interval = Integer.parseInt(i);
        datestamp = d;
        String thedate = parseDateAndInterval();
        return thedate;
    }

    /**Adds interval to datestamp. Interval is an integer.
     *
     * @param d datestamp
     * @param i interval
     * @return new date
     */
    public static String addInterval(String d, int i) {
        interval = i;
        datestamp = d;
        String thedate = parseDateAndInterval();
        return thedate;
    }

    /**Parses the different parts of the datestamp to create a calendar object
     * and then add the interval to that object.
     *
     * @return new date
     */
    private static String parseDateAndInterval() {
        // parse datestamp, nanos never change so they will become a constant string later
        int year = Integer.parseInt(datestamp.substring(0, 4));
        int month = Integer.parseInt(datestamp.substring(datestamp.indexOf("-")+1, datestamp.lastIndexOf("-"))) - 1;
        int date = Integer.parseInt(datestamp.substring(datestamp.lastIndexOf("-")+1, datestamp.indexOf("T")));
        int hour = Integer.parseInt(datestamp.substring(datestamp.indexOf("T")+1, datestamp.indexOf("T")+3));
        int minute = Integer.parseInt(datestamp.substring(datestamp.indexOf("T")+4, datestamp.indexOf("T")+6));
        int second = Integer.parseInt(datestamp.substring(datestamp.indexOf("T")+7, datestamp.indexOf(".")));

        // add the interval using the gregorian calendar class
        GregorianCalendar cal = new GregorianCalendar();
        cal.set(year, month, date, hour, minute, second);
        cal.add(GregorianCalendar.SECOND, interval);

        // format parts of the timestamp
        String years = "" + cal.get(GregorianCalendar.YEAR);
        String months = "" + (((cal.get(GregorianCalendar.MONTH) + 1) < 10) ? "0":"") + (cal.get(GregorianCalendar.MONTH) + 1);
        String dates = "" + ((cal.get(GregorianCalendar.DATE) < 10) ? "0":"") + cal.get(GregorianCalendar.DATE);
        String hours = "" + ((cal.get(GregorianCalendar.HOUR) < 10) ? "0":"") + cal.get(GregorianCalendar.HOUR);
        String minutes = "" + ((cal.get(GregorianCalendar.MINUTE) < 10) ? "0":"") + cal.get(GregorianCalendar.MINUTE);
        String seconds = "" + ((cal.get(GregorianCalendar.SECOND) < 10) ? "0":"") + cal.get(GregorianCalendar.SECOND);
        String nanos = ".000Z";

        String thedate = years + "-" + months + "-" + dates + "T" + hours + ":" + minutes + ":" + seconds + nanos;

        return thedate;
    }
}
