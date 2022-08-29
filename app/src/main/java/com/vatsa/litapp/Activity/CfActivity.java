package com.vatsa.litapp.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.vatsa.litapp.Helpers.DBCloseFriendsHelper;
import com.vatsa.litapp.R;

public class CfActivity extends AppCompatActivity {

    EditText name, contact, dob;
    Button insert, update, delete, view;
    DBCloseFriendsHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cf);

        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB = new DBCloseFriendsHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkinsertdata = DB.insertclosefriends(nameTXT, contactTXT, dobTXT);
                if(checkinsertdata == true){
                    Toast.makeText(CfActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CfActivity.this, "Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkupdatedata = DB.updateclosefriends(nameTXT, contactTXT, dobTXT);
                if(checkupdatedata == true){
                    Toast.makeText(CfActivity.this, "New Entry Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CfActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();

                Boolean checkdeletedata = DB.deleteclosefriends(nameTXT);
                if(checkdeletedata == true){
                    Toast.makeText(CfActivity.this, "New Entry Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CfActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(CfActivity.this, "No entry Exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("UserID :"+res.getString(1)+"\n");
                    buffer.append("Description :"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(CfActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
}