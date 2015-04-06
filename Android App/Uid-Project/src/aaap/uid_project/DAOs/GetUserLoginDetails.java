package aaap.uid_project.DAOs;

import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import aaap.uid_project.Activities.SecondPageActivity;
import aaap.uid_project.Utils.PostJSONFromUrl;
import aaap.uid_project.Utils.SQLiteLoginHandler;
import aaap.uid_project.Utils.SessionManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class GetUserLoginDetails extends AsyncTask<Void, Void, Void> {

	private String url = "http://10.0.2.2/LoginHandler.php";

	private String USERNAME = "username";
	private String NAME = "name";
	private String ROLE = "role";
	private String UID = "uid";
	private String TAG = "Tag";
	private Boolean LoginBool = false;
	// private String url = "http://192.168.42.112/get_Roles.php";

	Context mContext;
	List<NameValuePair> para;

	public GetUserLoginDetails(Context mContext, List<NameValuePair> para) {
		this.para = para;
		this.mContext = mContext;
	}

	/*
	 * public void getRoles() { try { ArrayList<String> temp = new
	 * ArrayList<String>(); JSONArray data = new
	 * JSONArray(GetJSONUrl.getJSONUrl((url))); for (int i = 0; i <
	 * data.length(); i++) { JSONObject c = data.getJSONObject(i);
	 * temp.add(c.getString("Role").toString()); } Roles roles = new Roles();
	 * roles.setRoles(temp); } catch (JSONException e) { // TODO Auto-generated
	 * catch block e.printStackTrace(); }
	 * 
	 * }
	 */

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub

		try {
			JSONObject jObj = new JSONObject(PostJSONFromUrl.PostJSONUrl(url,
					this.para));
			boolean error = jObj.getBoolean("error");

			Log.d("ERROR", "" + error);

			if (!error) {
				// User successfully stored in MySQL
				// Now store the user in sqlite
				UID = jObj.getString("uid");

				JSONObject user = jObj.getJSONObject("user");
				NAME = user.getString("name");
				USERNAME = user.getString("username");
				ROLE = user.getString("role");

				Log.d("Name", NAME);
				Log.d("USERNAME", USERNAME);
				Log.d("ROLE", ROLE);
				// Inserting row in users table
				SQLiteLoginHandler db = new SQLiteLoginHandler(mContext);
				db.addUser(USERNAME, NAME, UID, ROLE);
				LoginBool = true;
			} else {

				String errorMsg = jObj.getString("error_msg");
				LoginBool = false;
				Log.d("Error Message", errorMsg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(mContext,"Cannot access the web service"+e.toString(), Toast.LENGTH_LONG).show(); 
		}
		Log.d("LoginBool = ", ""+LoginBool);
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if (LoginBool==true){
		SessionManager session = new SessionManager(mContext);

		session.setLogin(true);
		Intent intent = new Intent(mContext, SecondPageActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
		mContext.startActivity(intent);
		}
	}

}
