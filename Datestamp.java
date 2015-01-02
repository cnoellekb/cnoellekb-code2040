import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Datestamp {
	
	public static void main(String[] arg) throws ParseException {
		// get the date stamp	
    	String results = "";
		JSONObject body = new JSONObject();
		body.put("token", "RX4b7hMAz3");
		
		try {			
			URL url = new URL("http://challenge.code2040.org/api/time");			
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			connection.setDoInput(true);

			OutputStreamWriter out = new OutputStreamWriter(
					connection.getOutputStream());
			out.write(body.toString());
			out.flush();

			System.out.println("Sent the request...");

			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			
			String line = "";
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				results = line;
			}
			
			out.close();
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}    	
		
		String datestamp = results.substring(results.indexOf("\":\"")+3, results.indexOf("\",\""));
		System.out.println(datestamp);
		String interval = results.substring(results.indexOf("interval")+10, results.indexOf("}}"));
		System.out.println(interval);
		
		addInterval(datestamp, interval);		
		
	}
	
	public static String addInterval(String datestamp, String i) {
		int interval = Integer.parseInt(i);
		
		// parse datestamp
		int year = Integer.parseInt(datestamp.substring(0, 4)) - 1900;
		System.out.println(year);
		
		int month = Integer.parseInt(datestamp.substring(datestamp.indexOf("-")+1, datestamp.lastIndexOf("-")));
		System.out.println(month);
		
		int date = Integer.parseInt(datestamp.substring(datestamp.lastIndexOf("-")+1, datestamp.indexOf("T")));
		System.out.println(date);
		
		int hour = Integer.parseInt(datestamp.substring(datestamp.indexOf("T")+1, datestamp.indexOf("T")+3));
		System.out.println(hour);
		
		int minute = Integer.parseInt(datestamp.substring(datestamp.indexOf("T")+4, datestamp.indexOf("T")+6));
		System.out.println(minute);
		
		int second = Integer.parseInt(datestamp.substring(datestamp.indexOf("T")+7, datestamp.indexOf(".")));
		System.out.println(second);
		
		int nano = Integer.parseInt(datestamp.substring(datestamp.indexOf("T")+10, datestamp.indexOf("Z")));
		System.out.println(nano);
		
		Date datething = new Date(year, month, date, hour, minute, second, nano);
		
		return "";
	}
	
}
