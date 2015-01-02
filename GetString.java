import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



public class GetString {

    public static void main(String[] args) {
        // get the string
    	String stringToReverse = "";		
		JSONObject body = new JSONObject();
		body.put("token", "RX4b7hMAz3");
		
		try {
			
			URL url = new URL("http://challenge.code2040.org/api/getstring");			
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
				stringToReverse = line;
			}
			
			out.close();
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}    	
		
		stringToReverse = stringToReverse.substring(stringToReverse.indexOf(":\"")+2, stringToReverse.indexOf("\"}"));
		System.out.println(stringToReverse);
		
		// reverse the string
        String reversedString = "";

        for (int i = stringToReverse.length() - 1; i >= 0; i--) {
            reversedString += stringToReverse.charAt(i);
        }

        System.out.println(stringToReverse);
        System.out.println(reversedString);
        		
		// POST results
        JSONObject body2 = new JSONObject();
		body2.put("token", "RX4b7hMAz3");
		body2.put("string", reversedString);
		
		try {
			
			URL url = new URL("http://challenge.code2040.org/api/validatestring");			
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
