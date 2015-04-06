package aaap.uid_project.Activities;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import aaap.uid_project.R;
import aaap.uid_project.DAOs.GetRoles;
import aaap.uid_project.DAOs.GetUserLoginDetails;
import aaap.uid_project.DTOs.Roles;
import aaap.uid_project.Utils.SessionManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private Button LoginB;
	private EditText UserNameET;
	private EditText PasswordET;
	private Spinner DropDownSpinner;
	private SessionManager session;
	private Intent intent;
	private String ROLE;
	public static String URL_LOGIN;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		//intent = new Intent(LoginActivity.this, SecondPageActivity.class);
		//startActivity(intent);
		//finish();
		
		URL_LOGIN="http://10.0.2.2/LoginHandler.php";
		LoginB = (Button) findViewById(R.id.Button_Login);
		UserNameET = (EditText) findViewById(R.id.ET_Username);
		PasswordET = (EditText) findViewById(R.id.ET_Password);
		DropDownSpinner = (Spinner) findViewById(R.id.DropDown_Roles);

		session = new SessionManager(getApplicationContext());

		// Check if user is already logged in or not
		if (session.isLoggedIn()) {
			// User is already logged in. Take him to main activity
			intent = new Intent(LoginActivity.this, SecondPageActivity.class);
			startActivity(intent);
			finish();
		}
		
		
		
		GetRoles a = new GetRoles(LoginActivity.this, DropDownSpinner);
		a.execute();

		// Login button Click Event
		LoginB.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				String username = UserNameET.getText().toString();
				String password = PasswordET.getText().toString();

				// Check for empty data in the form
				if (username.trim().length() > 0 && password.trim().length() > 0) {
					// login user
					
					List<NameValuePair> params = new ArrayList<NameValuePair>(4);
					params.add(new BasicNameValuePair("tag", "login"));
					params.add(new BasicNameValuePair("role", ROLE));
					params.add(new BasicNameValuePair("password", username));
					params.add(new BasicNameValuePair("username", password));
					
					Log.d("Tag : ", params.toString());
					
					GetUserLoginDetails getUser = new GetUserLoginDetails(getApplicationContext(), params);
					getUser.execute();
					
					
					
				} else {
					// Prompt user to enter credentials
					Toast.makeText(getApplicationContext(),
							"Please enter the credentials!", Toast.LENGTH_LONG)
							.show();
				}
			}

		});

		

		try {
			DropDownSpinner
					.setOnItemSelectedListener(new OnItemSelectedListener() {

						@Override
						public void onItemSelected(AdapterView<?> parent,
								View view, int position, long id) {
							// TODO Auto-generated method stub
							ROLE = Roles.getRoles().get(position);
							Log.i("ROLE : ", ROLE);

						}

						@Override
						public void onNothingSelected(AdapterView<?> parent) {
							// TODO Auto-generated method stub
							Toast.makeText(LoginActivity.this,
									"Your Selected : Nothing",
									Toast.LENGTH_SHORT).show();
						}
					});
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}

	

	/*
	 * @Override public boolean onCreateOptionsMenu(Menu menu) { // Inflate the
	 * menu; this adds items to the action bar if it is present.
	 * getMenuInflater().inflate(R.menu.login, menu); return true; }
	 */
	/*
	 * @Override public boolean onOptionsItemSelected(MenuItem item) { // Handle
	 * action bar item clicks here. The action bar will // automatically handle
	 * clicks on the Home/Up button, so long // as you specify a parent activity
	 * in AndroidManifest.xml. int id = item.getItemId(); if (id ==
	 * R.id.action_settings) { return true; } return
	 * super.onOptionsItemSelected(item); }
	 */

}
