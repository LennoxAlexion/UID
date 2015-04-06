package aaap.uid_project.Adapters;

import java.util.ArrayList;

import aaap.uid_project.DTOs.Roles;
import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class DropDownAdapter {
	Context mContext;
	Spinner spin;
	
	public DropDownAdapter(Context mContext, Spinner spin) {
		this.mContext=mContext;
		this.spin=spin;
		Log.e("Inside", "adapter");
		// TODO Auto-generated constructor stub
	}
	
	public void populateSpinner(){
		ArrayList<String> Membership = Roles.getRoles();
		
		
		
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(
				mContext, android.R.layout.simple_spinner_item,
				Membership);
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		try{
		spin.setAdapter(spinnerAdapter);
		}
		catch(NullPointerException e){
			Toast.makeText(mContext,
					"Check Membership ArrayList",
					Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}
}
