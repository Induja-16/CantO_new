package com.example.canto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
//import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
  //import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

//import Common.Common;
import Model.User;

public class SIgnIn extends AppCompatActivity {
    EditText edtPhone,edtPassword;
    Button btSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_ign_in);

        edtPassword = (MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone = (MaterialEditText)findViewById(R.id.edtPhone);
        btSignIn = (Button)findViewById(R.id.btsignIn);

         final FirebaseDatabase database = FirebaseDatabase.getInstance();
         final DatabaseReference table_user = database.getReference("User");

        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog mDialog = new ProgressDialog(SIgnIn.this);
                mDialog.setMessage("Please Wait..");
                mDialog.show();


                table_user.addValueEventListener(new ValueEventListener() {
                    //public void setDataSnapshot(DataSnapshot dataSnapshot) {
                      //  this.dataSnapshot = dataSnapshot;
                    //}

                    //private DataSnapshot dataSnapshot;

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot){

                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()){

                            mDialog.dismiss();
                            User user =dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
                            assert user != null;
                            if(user.getPassword().equals(edtPassword.getText().toString()))
                            {
                                Toast.makeText(SIgnIn.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(SIgnIn.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            mDialog.dismiss();
                            Toast.makeText(SIgnIn.this, "User does not exist",Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }

}