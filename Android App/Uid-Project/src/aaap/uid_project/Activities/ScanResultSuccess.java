package aaap.uid_project.Activities;

import aaap.uid_project.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ScanResultSuccess extends Activity{
	
	private TextView FirstName;
	private TextView LastName;
	private TextView Uid;
	private TextView BloodGroup;
	private TextView Address;
	
	private String FIRSTNAME;
	private String LASTNAME;
	private String UID;
	private String BLOODGROUP;
	private String ADDRESS;
	
	
	private Button WrongB;
	private Button FetchB;
	private Button FineB;
	
	
	private String ScannedText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qr_result_success);
		
		ScannedText = getIntent().getStringExtra("SCANNED_TEXT");
		/*
		 * Call Function to DECODE QR and separate to String Fields here.
		 * Then store in Offline People DB
		 * */
		Uid = (TextView) findViewById(R.id.TextView_qr_result_ScannedCode);
		FirstName = (TextView) findViewById(R.id.TextView_qr_result_FirstName);
		LastName = (TextView) findViewById(R.id.TextView_qr_result_LastName);
		BloodGroup = (TextView) findViewById(R.id.TextView_qr_result_BloodGroup);
		Address = (TextView) findViewById(R.id.TextView_qr_result_Address);
		
		WrongB = (Button) findViewById(R.id.Button_qr_result_WrongQR);
		FetchB = (Button) findViewById(R.id.Button_qr_result_ImposeFine);
		
		
		
		Log.e("Scanned Text: ", "" + ScannedText);
		Uid.setText(""+ScannedText.toString());
		
		FetchB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				
			}
		});
		
		
	}

}
