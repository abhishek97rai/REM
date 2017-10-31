package com.example.android.rem;

import android.provider.BaseColumns;

/**
 * Created by admin on 31-Oct-17.
 */

public class Database {

    Database(){

    }

    public static class Expense implements BaseColumns{
        public static final String TABLE_NAME = "exp";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_DETAILS = "details";
    }


}
