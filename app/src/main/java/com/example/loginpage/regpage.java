package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginpage.ui.UserData;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class regpage extends AppCompatActivity {
    EditText rname,remail,rcontact,raddress;
    Button rsubmit;
    DatabaseReference registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regpage);

        rname = findViewById(R.id.regname);
        remail =findViewById(R.id.regemail);
        rcontact = findViewById(R.id.regcontact);
        raddress = findViewById(R.id.regaddress);

        rsubmit = findViewById(R.id.regsubmit);

        registration = FirebaseDatabase.getInstance().getReference().child("Volunteers");

        rsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aname = rname.getText().toString();
                String aemail = remail.getText().toString();
                String acontact = rcontact.getText().toString();
                String aaddress = raddress.getText().toString();
                if(aname.isEmpty() || aemail.isEmpty() || acontact.isEmpty() || aaddress.isEmpty()){
                    Toast.makeText(regpage.this, "Please fill all the Details", Toast.LENGTH_SHORT).show();
                }
                else if(acontact.length() != 10){
                    Toast.makeText(regpage.this, "Contact number must be of 10 digits", Toast.LENGTH_SHORT).show();
                }
                else{
                setUserData();
                }

            }
        });

    }
    private  void setUserData(){
        String aname = rname.getText().toString();
        String aemail = remail.getText().toString();
        String acontact = rcontact.getText().toString();
        String aaddress = raddress.getText().toString();

        UserData userData = new UserData(aname,aemail,acontact,aaddress);
        registration.push().setValue(userData);
        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();

    }
}