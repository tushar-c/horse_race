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

    TextView text_display;
    EditText text_input;
    Button update_button;
    FirebaseDatabase firebase_db;
    String display_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_display = findViewById(R.id.MainTextView);
        text_input = findViewById(R.id.editText);
        update_button = findViewById(R.id.nameButton);

        firebase_db = FirebaseDatabase.getInstance();
        final DatabaseReference firebase_db_ref = firebase_db.getReference();

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display_text = text_input.getText().toString();
                firebase_db_ref.child("chigga").setValue(display_text);
                text_display.setText(display_text);
                Toast.makeText(MainActivity.this, "sent data", Toast.LENGTH_LONG).show();

                firebase_db_ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String data = dataSnapshot.child("chigga").getValue().toString() + "z";
                        text_display.setText(data);
                        Toast.makeText(MainActivity.this, "got data", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }
}
