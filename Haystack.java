import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;


public class Haystack {

    public static void main(String[] args) {
    	// get the string		
    	String results = "";
		JSONObject body = new JSONObject();
		body.put("token", "RX4b7hMAz3");
		
		try {			
			URL url = new URL("http://challenge.code2040.org/api/haystack");			
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
    	
		// find the index
		
		String hay = results.substring(results.indexOf('[')+1, results.indexOf(']'));
		String newEl = ""; // new element
		String needle = results.substring(results.indexOf("needle\":\"")+9, results.lastIndexOf("\""));
		
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
		
		System.out.println("Found at index " + index);
		
		// post index
		JSONObject body2 = new JSONObject();
		body2.put("token", "RX4b7hMAz3");
		body2.put("needle", index);
		
		try {			
			URL url = new URL("http://challenge.code2040.org/api/validateneedle");			
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

			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			
			String line = "";
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
			
			out.close();
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}    	

    }
}
