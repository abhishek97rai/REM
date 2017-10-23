package com.example.android.rem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    TextView textreg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText)findViewById(R.id.input_email);
        password = (EditText)findViewById(R.id.input_password);
        textreg = (TextView)findViewById(R.id.link_signup);

        textreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(MainActivity.this, RegisterAs.class);
                startActivity(register);
            }
        });
    }
}
