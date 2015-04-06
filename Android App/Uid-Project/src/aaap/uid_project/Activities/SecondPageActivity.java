package aaap.uid_project.Activities;

import java.util.HashMap;

import aaap.uid_project.R;
import aaap.uid_project.Utils.SQLiteLoginHandler;
import aaap.uid_project.Utils.SessionManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondPageActivity extends Activity {

	private Button LogoutB;
	private Button ScanActivityB;
	private TextView Username;
	private TextView Name;
	private TextView Role;
	private HashMap<String, String> LoggedInUserData;
	SQLiteLoginHandler db;
	SessionManager session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sec_page);

		ScanActivityB = (Button) findViewById(R.id.Button_Sec_Page_Scan);
		LogoutB = (Button) findViewById(R.id.Button_Logout);
		Username = (TextView) findViewById(R.id.TextView_Username);
		Name = (TextView) findViewById(R.id.TextView_Name);
		Role = (TextView) findViewById(R.id.TextView_Role);

		session = new SessionManager(getApplicationContext());
		db = new SQLiteLoginHandler(getApplicationContext());

		LoggedInUserData = db.getUserDetails();
		Log.d("Hashmap of LoggedIn user", LoggedInUserData.toString());
		Username.setText("" + LoggedInUserData.get("username"));
		Name.setText("" + LoggedInUserData.get("name"));
		Role.setText("" + LoggedInUserData.get("role"));

		LogoutB.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				session.setLogin(false);
				db.deleteUsers();
				Toast.makeText(getApplicationContext(),
						"Successfully Logged Out", Toast.LENGTH_LONG);
				Intent intent = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(intent);
				finishAffinity();
			}
		});

		ScanActivityB.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(getApplicationContext(),
						ScanQRCodeActivity.class);
				startActivity(intent);

			}
		});

	}

	private static final int TIME_INTERVAL = 2000; // # milliseconds, desired
													// time passed between two
													// back presses.
	private long mBackPressed;

	@SuppressLint("NewApi")
	@Override
	public void onBackPressed() {
		if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
			super.onBackPressed();
			session.setLogin(false);
			db.deleteUsers();

			finishAffinity();
			return;
		} else {
			Toast.makeText(getBaseContext(),
					"Tap back button in order to exit", Toast.LENGTH_SHORT)
					.show();
		}

		mBackPressed = System.currentTimeMillis();
	}
}
