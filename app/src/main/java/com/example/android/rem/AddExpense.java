package com.example.android.rem;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddExpense extends AppCompatActivity {

    Button addexp;
    EditText name,price,details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        addexp = (Button) findViewById(R.id.btn_add_exp);
        name = (EditText) findViewById(R.id.item_name);
        price = (EditText) findViewById(R.id.item_price);
        details = (EditText) findViewById(R.id.item_details);

        addexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String expname,expprice,expdetails;
                expname = name.getText().toString();
                expprice = price.getText().toString();
                expdetails = details.getText().toString();
                insert(expname, expprice, expdetails);
            }
        });
    }

    void insert(String name, String price, String details){
        // Gets the data repository in write mode
        DatabaseHelper mDbHelper = new DatabaseHelper(AddExpense.this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(Database.Expense.COLUMN_NAME, name);
        values.put(Database.Expense.COLUMN_PRICE, price);
        values.put(Database.Expense.COLUMN_DETAILS, details);


// Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(Database.Expense.TABLE_NAME, null, values);
    }
}
