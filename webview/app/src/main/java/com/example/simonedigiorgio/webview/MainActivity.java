package com.example.simonedigiorgio.webview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    public Button but1;
    public EditText url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        url = (EditText)findViewById(R.id.Search_url);
        but1 = (Button)findViewById(R.id.button_search);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(url != null) {
                    String loadurl = url.getText().toString();
                    Intent toy = new Intent(MainActivity.this, second.class);
                    toy.putExtra("Url",loadurl);
                    startActivity(toy);
                } else if(url == null){
                    Toast.makeText(MainActivity.this, "Write url" , Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}
