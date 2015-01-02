import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

public class Prefix {

    public static void main(String[] args) {
    	// get the words	
    	String results = "";
		JSONObject body = new JSONObject();
		body.put("token", "RX4b7hMAz3");
		
		try {			
			URL url = new URL("http://challenge.code2040.org/api/prefix");			
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
		String words = results.substring(results.indexOf('[')+1, results.indexOf(']'));
		Collection<String> prefixedWords = new LinkedList<String>();
		String prefix = results.substring(results.indexOf("prefix\":\"")+9, results.lastIndexOf("\""));
    	
		while (words.length() > 1) {
			int comma;
			
			if (words.contains(",")) {
				comma = words.indexOf(',') - 1;
			} else {
				comma = words.length() - 1;
			}
			
			String newEl = words.substring(1, comma);
			if (!newEl.substring(0, prefix.length()).equals(prefix)) {
				prefixedWords.add(newEl);
            }					
			
			if (comma + 2 < words.length()) {
				words = words.substring(comma+2);
			} else {
				words = "";
			}
		}		
        
		// post the resulting array
        JSONObject body2 = new JSONObject();
		body2.put("token", "RX4b7hMAz3");
		body2.put("array", prefixedWords);
		
		try {			
			URL url = new URL("http://challenge.code2040.org/api/validateprefix");			
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
				results = line;
			}
			
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}    	
    }
}
