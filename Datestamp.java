import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;

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
		System.out.println("Original timestamp: " + datestamp);
		String interval = results.substring(results.indexOf("interval")+10, results.indexOf("}}"));
		System.out.println("Interval: " + interval + "s");

		String date = AddInterval.addInterval(datestamp, interval);

		System.out.println("New timestamp: " + date);

		JSONObject body2 = new JSONObject();
        body2.put("token", "RX4b7hMAz3");
        body2.put("datestamp", date);

        try {
            URL url = new URL("http://challenge.code2040.org/api/validatetime");
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            OutputStreamWriter out = new OutputStreamWriter(
                    connection.getOutputStream());

            out.write(body2.toString());
            out.flush();

            System.out.println("Sent the request...");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line = "";
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                //results = line;
            }

            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
