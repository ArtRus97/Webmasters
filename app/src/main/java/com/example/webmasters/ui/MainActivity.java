package com.example.webmasters.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webmasters.ui.graphic_design.GraphicDesignActivity;
import com.example.webmasters.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fetch views from layout.
        Button buttonGraphicDesign = (Button) findViewById(R.id.buttonGraphicDesign);

        // Setup view callbacks.
        buttonGraphicDesign.setOnClickListener(this::onNavigationClick);
    }

    private void onNavigationClick(View view) {
        switch (view.getId()) {
            // Navigate to graphic design activity.
            case R.id.buttonGraphicDesign:
                Intent intent = new Intent(this, GraphicDesignActivity.class);
                startActivity(intent);
                break;

            default:
                Log.e("MainActivity", "Invalid navigation!");
        }
    }
}