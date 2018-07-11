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
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String URL = "url";

	private boolean checked = false;


	private String textUser, textPass, textUrl;

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

		String url = Url.getText().toString();
		String username = Username.getText().toString();
		String password = Password.getText().toString();

		Intent ritorno = getIntent();
		checked = ritorno.getBooleanExtra("checked", checked);

		final Intent intent = new Intent(MainActivity.this, Test.class);
		intent.putExtra("url",url);
		intent.putExtra("username",username);
		intent.putExtra("password", password);

		if(checked){
			startActivity(intent);
		}

		Go_Url.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				saveData();
				checked = true;
				startActivity(intent);
			}
		});

	}

	public void saveData(){
		SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();

		editor.putString(URL, Url.getText().toString().trim());
		editor.putString(USERNAME, Username.getText().toString().trim());
		editor.putString(PASSWORD, Password.getText().toString().trim());

		editor.apply();
		Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
	}

	public void loadData(){
		SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
		textUrl = sharedPreferences.getString(URL, "");
		textUser = sharedPreferences.getString(USERNAME, "");
		textPass = sharedPreferences.getString(PASSWORD, "");
	}

	public void updateView(){
		Url.setText(textUrl);
		Username.setText(textUser);
		Password.setText(textPass);
	}
}
