package com.example.android.rem;

/**
 * Created by manas on 24-10-2017.
 */

public class Group {

    public String Group_Name;
    public String Group_ID;
    public String Group_Address;
    public Members members;
    public Group(){

    }

    public Group(String Group_Name, String Group_ID, String Group_Address){
        this.Group_Name = Group_Name;
        this.Group_Address = Group_Address;
        this.Group_ID = Group_ID;
        this.members = new Members();
    }




}
