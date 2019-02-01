package com.example.managorgan;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView greet_text;
    Button name_button;
    EditText name_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name_button = findViewById(R.id.nameButton);
        greet_text = findViewById(R.id.MainTextView);
        name_input = findViewById(R.id.editText);

        final DatabaseReference firebase_db = FirebaseDatabase.getInstance().getReference();
        firebase_db.child("niggz").setValue("Welcome");
        firebase_db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                greet_text.setText(dataSnapshot.child("niggz").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    name_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            final String paad = firebase_db.push().getKey();
            firebase_db.child("user").child(paad).child("name").setValue(name_input.getText().toString());

            firebase_db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    greet_text.setText(dataSnapshot.child("user").child(paad).child("name").getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            Toast.makeText(MainActivity.this, "Data added and Text updated Successfully", Toast.LENGTH_SHORT).show();
        }
    });

    }
}
