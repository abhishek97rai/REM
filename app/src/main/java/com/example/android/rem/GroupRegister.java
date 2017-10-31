package com.example.android.rem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class GroupRegister extends AppCompatActivity {

    TextView textlog;

    private DatabaseReference mDatabase;

    EditText Gname;
    EditText GID;
    EditText GAddress;

    String groupName;
    String groupID;
    String groupAdd;

    Button register;

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
        mDatabase = FirebaseDatabase.getInstance().getReference();

        register = (Button)findViewById(R.id.btn_reg_grp);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Gname = (EditText) findViewById(R.id.input_grp_name);
                GID = (EditText) findViewById(R.id.input_grp_id);
                GAddress = (EditText) findViewById(R.id.input_address);

                groupName = Gname.getText().toString();
                groupID = GID.getText().toString();
                groupAdd = GAddress.getText().toString();

                Toast.makeText(GroupRegister.this, groupID, Toast.LENGTH_SHORT).show();
               // Log.d("ID:",GID);
                // idExists(groupID);

                //Toast.makeText(GroupRegister.this, "Entered in idExists", Toast.LENGTH_SHORT).show();
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Group");
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild(groupID)) {
                            // run some code
                            Toast.makeText(GroupRegister.this, "Group already exists", Toast.LENGTH_SHORT).show();
                            //retVal =  true;
                        } else {
                            Toast.makeText(GroupRegister.this, "Creating group", Toast.LENGTH_SHORT).show();
                            Group group = new Group(groupName, groupID, groupAdd);
                            mDatabase.child("Group").child(groupID).setValue(group);
                            //retVal = false;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    /*public void regGroup(){
        Toast.makeText(GroupRegister.this,mDatabase.toString(),Toast.LENGTH_SHORT).show();
        //Group group = new Group(Gname,GID,GAddress);
        //Toast.makeText(GroupRegister.this,"Pushing..",Toast.LENGTH_SHORT).show();
        //mDatabase.child("Group").push().setValue(group);
        idExists(groupID);
        //mDatabase.child("Group").child(GID).setValue(group);

    }

    public void idExists(final String ID){
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference().child("Group");
        rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(ID)) {
                    // run some code
                    Toast.makeText(GroupRegister.this,"Group already exists",Toast.LENGTH_SHORT).show();
                    //retVal =  true;
                }
                else{
                    Toast.makeText(GroupRegister.this,"Creating group",Toast.LENGTH_SHORT).show();
                    Group group = new Group(groupName,groupID,groupAdd);
                    mDatabase.child("Group").child(groupID).setValue(group);
                    //retVal = false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
}
