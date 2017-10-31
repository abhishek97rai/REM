package com.example.android.rem;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
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
        nametxt.setGravity(Gravity.CENTER_HORIZONTAL);
        nametxt.setTextSize(18);
        nametxt.setTypeface(null, Typeface.BOLD);
        nametxt.setTextColor(getResources().getColor(R.color.white));
        TextView pricetxt = new TextView(ViewExpense.this);
        pricetxt.setText(price);
        pricetxt.setGravity(Gravity.CENTER_HORIZONTAL);
        pricetxt.setTextSize(18);
        pricetxt.setTypeface(null, Typeface.BOLD);
        pricetxt.setTextColor(getResources().getColor(R.color.white));
        TextView detailstxt = new TextView(ViewExpense.this);
        detailstxt.setText(details);
        detailstxt.setGravity(Gravity.CENTER_HORIZONTAL);
        detailstxt.setTextSize(18);
        detailstxt.setTypeface(null, Typeface.BOLD);
        detailstxt.setTextColor(getResources().getColor(R.color.white));
        row.addView(nametxt);
        row.addView(pricetxt);
        row.addView(detailstxt);
        tab.addView(row);
    }
}
