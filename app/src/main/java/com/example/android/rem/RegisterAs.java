package com.example.android.rem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterAs extends AppCompatActivity {

    Button grp,mem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_as);
        grp = (Button) findViewById(R.id.reg_new_group);
        mem = (Button) findViewById(R.id.reg_new_member);

        grp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newgrp = new Intent(RegisterAs.this, GroupRegister.class);
                startActivity(newgrp);
            }
        });

        mem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newmem = new Intent(RegisterAs.this, MemberRegister.class);
                startActivity(newmem);
            }
        });
    }
}
