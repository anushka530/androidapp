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

public class donatePage extends AppCompatActivity {
    DatabaseReference donators;
    EditText name,email,contact,address,products;
    Button dSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_page);

        donators = FirebaseDatabase.getInstance().getReference().child("Donators");

        name = findViewById(R.id.dname);
        email = findViewById(R.id.demail);
        contact = findViewById(R.id.dcontact);
        address = findViewById(R.id.daddress);
        products = findViewById(R.id.dproduct);
        dSubmit = findViewById(R.id.dsubmit);

        dSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String aname = name.getText().toString();
                String aemail = email.getText().toString();
                String acontact = contact.getText().toString();
                String aaddress = address.getText().toString();
                String aproducts = products.getText().toString();
                if(aname.isEmpty() || aemail.isEmpty() || acontact.isEmpty() || aaddress.isEmpty() || aproducts.isEmpty()){
                    Toast.makeText(donatePage.this, "Please fill all the Details", Toast.LENGTH_SHORT).show();
                }
                else if(acontact.length() != 10){
                    Toast.makeText(donatePage.this, "Contact number must be of 10 digits", Toast.LENGTH_SHORT).show();
                }
                else {
                    setUserData();
                }
            }
        });
    }
    private  void setUserData(){
        String aname = name.getText().toString();
        String aemail = email.getText().toString();
        String acontact = contact.getText().toString();
        String aaddress = address.getText().toString();
        String aproducts = products.getText().toString();

        UserData userData = new UserData(aname,aemail,acontact,aaddress,aproducts);
        donators.push().setValue(userData);
        Toast.makeText(this, "Your request has been successfully accepted", Toast.LENGTH_SHORT).show();

    }
}