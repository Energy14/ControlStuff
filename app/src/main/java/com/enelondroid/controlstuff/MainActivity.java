package com.enelondroid.controlstuff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private ImageButton attentibut;
    private FirebaseDatabase database;
    private DatabaseReference attentinator;

    boolean isOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attentibut = findViewById(R.id.attentibutton);

        database = FirebaseDatabase.getInstance();
        attentinator = database.getReference("attentinator");


        attentinator.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (String.valueOf(dataSnapshot.getValue()).equals("1")) {
                    isOn = false;
                    attentibut.setColorFilter(null);
                } else {
                    isOn = true;
                    attentibut.setColorFilter(Color.rgb(240,255,0));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Synchronization error",
                        Toast.LENGTH_LONG).show();
            }
        });

        attentibut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOn) {
                    attentinator.setValue(1);
                    isOn = false;
                    attentibut.setColorFilter(null);
                } else {
                    attentinator.setValue(2);
                    isOn = true;
                    attentibut.setColorFilter(Color.rgb(240,255,0));
                }
            }
        });
    }
}
