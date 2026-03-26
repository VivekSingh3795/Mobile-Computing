package com.yayandroid.locationmanager.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

// We only need this one!
import com.yayandroid.locationmanager.sample.activity.SampleActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void inActivityClick(View view) {
        startActivity(new Intent(this, SampleActivity.class));
    }
}