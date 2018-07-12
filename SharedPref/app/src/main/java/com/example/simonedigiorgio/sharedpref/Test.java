package com.example.simonedigiorgio.sharedpref;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Test extends AppCompatActivity {

	private EditText Url, Username, Password;
	private Button go_back;
	private boolean checked = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		Url = (EditText)findViewById(R.id.url);
		Username = (EditText)findViewById(R.id.username);
		Password = (EditText)findViewById(R.id.password);
		go_back = (Button)findViewById(R.id.Rit);

		Intent ottenuto = getIntent();

		String url = ottenuto.getStringExtra("url");
		String username = ottenuto.getStringExtra("username");
		String password = ottenuto.getStringExtra("password");

		//final Intent ritorna = new Intent(Test.this, MainActivity.class);
		//ritorna.putExtra("checked", checked);

		Url.setText(url);
		Username.setText(username);
		Password.setText(password);
		/*
		go_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				startActivity(ritorna);
			}
		});
		*/
	}
}
