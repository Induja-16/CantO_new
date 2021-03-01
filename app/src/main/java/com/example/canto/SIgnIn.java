package com.example.canto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
//import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
  //import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

//import Common.Common;
import Model.User;
import ViewHolder.MenyViewHolder;

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
                final String[] password = new String[1];
                final boolean[] passMatch = {false};

//                    table_user.addValueEventListener(new ValueEventListener() {
//                        //public void setDataSnapshot(DataSnapshot dataSnapshot) {
//                        //  this.dataSnapshot = dataSnapshot;
//                        //}
//
//                        //private DataSnapshot dataSnapshot;
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                            if (dataSnapshot.child(edtPhone.getText().toString()).exists()) {
//
//                                mDialog.dismiss();
//                                User user = dataSnapshot.child("989251365").getValue(User.class);
////                                System.out.println("USER: "+user);
//                                assert user != null;
////                                System.out.println("USER PASSWORD "+ user.getPassword());
////                                System.out.println(("USER NAME: "+ user.getName()));
                                final String passwd = edtPassword.getText().toString().trim();
                                String usname = edtPhone.getText().toString().trim();
////                                System.out.println("INPUT PASSWORD: "+ passwd );
////                                System.out.println("INPUT USER NAME:"+ usname);
////                                System.out.println("COmpare Pass "+ passwd.compareTo(user.getPassword()));
////                                System.out.println("COmpare Name "+ usname.compareTo(user.getName()));
//
//                                if (user.getPassword().equals(edtPassword.getText().toString().trim())) {
//                                    Toast.makeText(SIgnIn.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(SIgnIn.this, "Sign in failed", Toast.LENGTH_SHORT).show();
//                                }
//                            } else {
//                                mDialog.dismiss();
//                                Toast.makeText(SIgnIn.this, "User does not exist", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//
//
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {
//                            System.out.println("CANCELLEDDDD");
//                        }
//                    });

                table_user.child(usname).child("password").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                        else {
                            Log.d("FireBase::::::", String.valueOf(task.getResult().getValue()));
                            password[0] = String.valueOf(task.getResult().getValue());
                            passMatch[0] = password[0].equals(passwd);
                            if(passMatch[0]){
                                Toast.makeText(SIgnIn.this, "Sign in successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), Home.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(SIgnIn.this, "Sign in failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });


            }
        });


    }

}