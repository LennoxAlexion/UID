package aaap.uid_project.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class GetJSONFromUrl {
	
	public static String getJSONUrl(String url) {
		 StringBuilder str = new StringBuilder();
		 HttpClient client = new DefaultHttpClient();
		 HttpGet httpGet = new HttpGet(url);
		 try {
		 HttpResponse response = client.execute(httpGet);
		 StatusLine statusLine = response.getStatusLine();
		 int statusCode = statusLine.getStatusCode();
		 if (statusCode == 200) { // Download OK
		 HttpEntity entity = response.getEntity();
		 InputStream content = entity.getContent();
		 BufferedReader reader = new BufferedReader(new InputStreamReader(content));
		 String line;
		 while ((line = reader.readLine()) != null) {
		 str.append(line);
		 }
		 } else {
		 Log.e("Log", "Failed to download result..");
		 }
		 } catch (ClientProtocolException e) {
		 e.printStackTrace();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		 
		 return str.toString();
		 }

}
