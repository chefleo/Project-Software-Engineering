package com.example.simonedigiorgio.sharedpref;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

	private EditText Url, Username, Password;
	private Button  Go_Url;

	public static final String SHARED_PREFS = "sharedPref";
	public static final String KEY_USER = "username";
	public static final String KEY_PASS = "password";
	public static final String KEY_URL = "url";
	public static final String CHECK = "check";

	private String textUser, textPass, textUrl;
	private boolean check = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Url = (EditText)findViewById(R.id.url);
		Username = (EditText)findViewById(R.id.username);
		Password = (EditText)findViewById(R.id.password);
		Go_Url = (Button)findViewById(R.id.Go_to_url);

		loadData();
		updateView();

		Intent block = getIntent();
		// check is true until first login
		check = block.getBooleanExtra("checked", true);

		// This is for auto login when open app *****************
		final String url = Url.getText().toString();
		final String username = Username.getText().toString();
		final String password = Password.getText().toString();

		final Intent intent = new Intent(MainActivity.this, web_view.class);
		intent.putExtra("url",url);
		intent.putExtra("username",username);
		intent.putExtra("password", password);

		if(check && !url.equals("") && !username.equals("") && !password.equals("")){
			startActivity(intent);
		}
		// ******************************************************

		Go_Url.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				// This is for first login and when you will change credentials
				if (!Url.getText().toString().equals("") && !Username.getText().toString().equals("") && !Password.getText().toString().equals("")) {
					check = true;

					saveData();
					loadData();
					updateView();

					final String url = Url.getText().toString();
					final String username = Username.getText().toString();
					final String password = Password.getText().toString();

					intent.putExtra("url",url);
					intent.putExtra("username",username);
					intent.putExtra("password", password);

					startActivity(intent);
				} else {
					Toast.makeText(MainActivity.this,"Something wrong", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}

	public void saveData(){
		SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();

		editor.putString(KEY_URL, Url.getText().toString());
		editor.putString(KEY_USER, Username.getText().toString());
		editor.putString(KEY_PASS, Password.getText().toString());
		editor.putBoolean(CHECK, check);

		editor.apply();
		Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
	}

	public void loadData(){
		SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
		textUrl = sharedPreferences.getString(KEY_URL, "");
		textUser = sharedPreferences.getString(KEY_USER, "");
		textPass = sharedPreferences.getString(KEY_PASS, "");
		check = sharedPreferences.getBoolean(CHECK, check);
	}

	public void updateView(){
		Url.setText(textUrl);
		Username.setText(textUser);
		Password.setText(textPass);
	}
}
