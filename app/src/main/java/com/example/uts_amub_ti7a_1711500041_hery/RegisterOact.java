package com.example.uts_amub_ti7a_1711500041_hery;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class RegisterOact extends AppCompatActivity {
    Button next ;
    EditText ed_usna, ed_passw, ed_email;


    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_oact);
        ed_usna = findViewById(R.id.ed_usna);
        ed_passw = findViewById(R.id.ed_passw);
        ed_email = findViewById(R.id.ed_email);

        next = findViewById(R.id.next);


        //fungsi pindah ke activity lain ke acttvty Register
        next.setOnClickListener (new View.OnClickListener(){
            public void onClick(View view){
                Intent gotonextregister = new Intent(RegisterOact.this, Register2Act.class);
                startActivity(gotonextregister);
            }
        });

        //menyimpan daya pada localstorage (handphone)
        SharedPreferences sharedPreferences= getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(username_key,ed_usna.getText().toString());//ngambil data inputan username ke variable username_key
        editor.apply();

        //Proses SIMPAN ke database
        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(ed_usna.getText().toString());
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSnapshot.getRef().child("username").setValue(ed_usna.getText().toString());
                dataSnapshot.getRef().child("password").setValue(ed_passw.getText().toString());
                dataSnapshot.getRef().child("email").setValue(ed_email.getText().toString());
                dataSnapshot.getRef().child("saldo").setValue(100000);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //fungsi pindah ke activity lain ke acttvty login
    }
}

