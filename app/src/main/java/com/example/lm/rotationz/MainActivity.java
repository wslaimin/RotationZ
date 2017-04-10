package com.example.lm.rotationz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private DrawRotation drawRotation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_rotation);
        drawRotation=(DrawRotation)findViewById(R.id.rotation);
    }

    public void onClick(View view){
        drawRotation.next();
    }
}
