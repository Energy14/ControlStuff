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

    private ImageButton laserbut, starsbut;
    private FirebaseDatabase database;
    private DatabaseReference nodemcu, esp82661;

    boolean isOn = false;
    boolean isStarsOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        laserbut = findViewById(R.id.laserbutton);
        starsbut = findViewById(R.id.starsbutton);

        database = FirebaseDatabase.getInstance();
        nodemcu = database.getReference("nodemcu");
        esp82661 = database.getReference("esp82661");


        nodemcu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (String.valueOf(dataSnapshot.getValue()).equals("1")) {
                    isOn = false;
                    laserbut.setColorFilter(null);
                } else {
                    isOn = true;
                    laserbut.setColorFilter(Color.rgb(240,255,0));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Synchronization error",
                        Toast.LENGTH_LONG).show();
            }
        });


        esp82661.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (String.valueOf(dataSnapshot.getValue()).equals("2")) {
                    isStarsOn = false;
                    starsbut.setColorFilter(null);
                } else {
                    isStarsOn = true;
                    starsbut.setColorFilter(Color.rgb(240,255,0));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Synchronization error",
                        Toast.LENGTH_LONG).show();
            }
        });

        laserbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isOn) {
                    nodemcu.setValue(1);
                    isOn = false;
                    laserbut.setColorFilter(null);
                } else {
                    nodemcu.setValue(2);
                    isOn = true;
                    laserbut.setColorFilter(Color.rgb(240,255,0));
                }
            }
        });

        starsbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isStarsOn) {
                    esp82661.setValue(2);
                    isStarsOn = false;
                    starsbut.setColorFilter(null);
                } else {
                    esp82661.setValue(1);
                    isStarsOn = true;
                    starsbut.setColorFilter(Color.rgb(240,255,0));
                }
            }
        });
    }
}
