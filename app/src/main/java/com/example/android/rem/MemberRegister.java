package com.example.android.rem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Member;
import java.util.HashMap;
import java.util.Map;

public class MemberRegister extends AppCompatActivity {

    EditText groupName;
    EditText groupId;
    EditText Name;
    EditText mobile;
    EditText Email;
    EditText password;

    String gName;
    String gID;
    String uName;
    String uMob;
    String uMail;
    String pass;

    Button register;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_register);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        register = (Button) findViewById(R.id.btn_reg_mem);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                groupName = (EditText) findViewById(R.id.grp_name);
                Name = (EditText) findViewById(R.id.mem_name);
                groupId = (EditText) findViewById(R.id.grp_id);
                mobile = (EditText) findViewById(R.id.mem_mobile);
                Email = (EditText) findViewById(R.id.mem_email);
                password = (EditText) findViewById(R.id.mem_pass);

                gName = groupName.getText().toString();
                gID = groupId.getText().toString();
                uName = Name.getText().toString();
                uMob = mobile.getText().toString();
                uMail = Email.getText().toString();
                pass = password.getText().toString();

                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Group");
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild(gID)) {
                            // run some code
                            if(snapshot.child(gID).hasChild(uMob)){
                                Toast.makeText(MemberRegister.this, "Number already registered with group!", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MemberRegister.this, "Adding you to group..", Toast.LENGTH_SHORT).show();
                                Members member = new Members(new Phone(uMail, uName, pass), uMob);
                                Map<String, Object> groupUpdates = new HashMap<>();
                                groupUpdates.put(uMob, new Phone(uMail, uName, pass));
                                mDatabase.child("Group").child(gID).updateChildren(groupUpdates);
                            }
                        } else {
                            Toast.makeText(MemberRegister.this, "Group doesn't exist!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
        });
    }
}
