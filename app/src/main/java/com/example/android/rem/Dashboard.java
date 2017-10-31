package com.example.android.rem;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    String grp;
    String mob,name;
    TextView dash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button add = (Button) findViewById(R.id.newexp);
        Button view = (Button) findViewById(R.id.viewexp);

        dash = (TextView) findViewById(R.id.dash);

        grp = getIntent().getStringExtra("GROUP_ID");
        mob = getIntent().getStringExtra("USER_MOB");
        name = getIntent().getStringExtra("USER_NAME");

        dash.setText(name + " - Dashboard");

        Toast.makeText(Dashboard.this,"Welcome, " + name, Toast.LENGTH_SHORT).show();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newexp = new Intent(Dashboard.this, AddExpense.class);
                startActivity(newexp);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewexp = new Intent(Dashboard.this, ViewExpense.class);
                startActivity(viewexp);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                Toast.makeText(getApplicationContext(), "Logging Out...",Toast.LENGTH_SHORT).show();
                Intent login = new Intent(Dashboard.this, MainActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    finishAffinity();
                }
                startActivity(login);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
