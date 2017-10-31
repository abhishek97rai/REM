package com.example.android.rem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText GroupId,userMob,password;
    String grp,mob,pass;
    TextView textreg;
    Button loginButton;

    private ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        loginButton = (Button) findViewById(R.id.btn_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GroupId = (EditText)findViewById(R.id.login_input_grp_id);
                userMob = (EditText) findViewById(R.id.login_input_mobile);
                password = (EditText)findViewById(R.id.login_input_password);
                textreg = (TextView)findViewById(R.id.link_signup);

                grp = GroupId.getText().toString();
                mob = userMob.getText().toString();
                pass = password.getText().toString();

                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Group");
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild(grp)) {
                            Log.e("Entered : ","in Snapshot has child grp");
                            // run some code
                            if(snapshot.child(grp).hasChild(mob)){
                                Log.e("Entered : ","in Snapshot has child grp.mobile");
                                String toMatch = snapshot.child(grp).child(mob).child("Password").getValue().toString();
                                Log.e("password val in db is: ",toMatch);
                                if(toMatch.equals(pass)){
                                    Log.e("Entered : ","in Snapshot has child grp.mobile and password");
                                    //Toast.makeText(MainActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
                                    String uName = snapshot.child(grp).child(mob).child("Name").getValue().toString();
                                    Intent intent = new Intent(MainActivity.this,Dashboard.class);
                                    intent.putExtra("GROUP_ID", grp);
                                    intent.putExtra("USER_MOB",mob);
                                    intent.putExtra("USER_NAME",uName);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                            //Toast.makeText(MainActivity.this, "Group exists", Toast.LENGTH_SHORT).show();
                            //retVal =  true;
                        } else {
                            Toast.makeText(MainActivity.this, "FAILED!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
            });
            }
        });

        textreg = (TextView)findViewById(R.id.link_signup);

        progressBar = new ProgressBar(this);
        textreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent register = new Intent(MainActivity.this, RegisterAs.class);
                startActivity(register);
            }
        });
    }
}
