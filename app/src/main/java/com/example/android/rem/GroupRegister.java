package com.example.android.rem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class GroupRegister extends AppCompatActivity {

    TextView textlog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_register);

        textlog = (TextView)findViewById(R.id.link_login);

        textlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(GroupRegister.this, MainActivity.class);
                startActivity(login);
            }
        });
    }
}
