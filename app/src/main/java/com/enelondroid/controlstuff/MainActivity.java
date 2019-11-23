package com.enelondroid.controlstuff;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button but1, but2;
    private FirebaseDatabase database;
    private DatabaseReference nodemcu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        but1 = findViewById(R.id.button1);
        but2 = findViewById(R.id.button2);

        database = FirebaseDatabase.getInstance();
        nodemcu = database.getReference("nodemcu");

        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nodemcu.setValue(1);
            }
        });

        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nodemcu.setValue(2);
            }
        });
    }
}
