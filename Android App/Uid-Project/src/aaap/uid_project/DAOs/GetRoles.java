package aaap.uid_project.DAOs;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import aaap.uid_project.Adapters.DropDownAdapter;
import aaap.uid_project.DTOs.Roles;
import aaap.uid_project.Utils.GetJSONFromUrl;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Spinner;

public class GetRoles  extends AsyncTask<Void, Void, Void>{

	private String url = "http://10.0.2.2/get_Roles.php";
	
	//private String url = "http://192.168.42.112/get_Roles.php";
	
	Context mContext;
	Spinner spin;
	ArrayList<String> temp = new ArrayList<String>();
	public GetRoles(Context mContext, Spinner spin){
		this.mContext=mContext;
		this.spin = spin;
	}

	/*public void getRoles() {
		try {
			ArrayList<String> temp = new ArrayList<String>();
			JSONArray data = new JSONArray(GetJSONUrl.getJSONUrl((url)));
			for (int i = 0; i < data.length(); i++) {
				JSONObject c = data.getJSONObject(i);
				temp.add(c.getString("Role").toString());
			}
			Roles roles = new Roles();
			roles.setRoles(temp);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}*/

	@Override
	protected Void doInBackground(Void... params) {
		// TODO Auto-generated method stub
		
		try {
			
			JSONArray data = new JSONArray(GetJSONFromUrl.getJSONUrl((url)));
			for (int i = 0; i < data.length(); i++) {
				JSONObject c = data.getJSONObject(i);
				temp.add(c.getString("Role").toString());
				
				Log.e("Role", c.getString("Role").toString()+"");
			}
			Roles.setRoles(temp);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		DropDownAdapter di = new DropDownAdapter(this.mContext, this.spin); 
		di.populateSpinner();
		
	}

}
