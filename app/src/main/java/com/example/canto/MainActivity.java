package com.example.canto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    Button btSignup,btSignIn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btSignup = (Button)findViewById(R.id.btSignup);
        btSignIn = (Button)findViewById(R.id.btsignIn);



        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent SignUp = new Intent(MainActivity.this,SignUp.class);
                startActivity(SignUp);

            }
        });

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signIn = new Intent(MainActivity.this,SIgnIn.class);
                startActivity(signIn);

            }
        });
    }

}