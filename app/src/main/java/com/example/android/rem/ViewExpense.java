package com.example.android.rem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ViewExpense extends AppCompatActivity {

    Button addexp;
    EditText name,price;
    TableLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expense);

        tab = (TableLayout) findViewById(R.id.exptable);

        DatabaseHelper mDbHelper = new DatabaseHelper(ViewExpense.this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                Database.Expense.COLUMN_NAME,
                Database.Expense.COLUMN_PRICE,
                Database.Expense.COLUMN_DETAILS
        };

        //String selection = Database.Expense.COLUMN_NAME_TITLE + " = ?";
        //String[] selectionArgs = { "My Title" };

        Cursor cursor = db.query(Database.Expense.TABLE_NAME, projection, null, null, null, null, null);
        while(cursor.moveToNext()) {
            String expname = cursor.getString(cursor.getColumnIndexOrThrow(Database.Expense.COLUMN_NAME));
            String expprice = cursor.getString(cursor.getColumnIndexOrThrow(Database.Expense.COLUMN_PRICE));
            String expdetails = cursor.getString(cursor.getColumnIndexOrThrow(Database.Expense.COLUMN_DETAILS));
            addRow(expname, expprice, expdetails);
        }
        cursor.close();
    }

    void addRow(String name, String price, String details){
        TableRow row = new TableRow(ViewExpense.this);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
        row.setLayoutParams(lp);
        TextView nametxt = new TextView(ViewExpense.this);
        nametxt.setText(name);
        TextView pricetxt = new TextView(ViewExpense.this);
        pricetxt.setText(price);
        TextView detailstxt = new TextView(ViewExpense.this);
        detailstxt.setText(details);
        row.addView(nametxt);
        row.addView(pricetxt);
        row.addView(detailstxt);
        tab.addView(row);
    }
}
