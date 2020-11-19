package com.example.webmasters.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.webmasters.R;
import com.example.webmasters.databinding.ActivityMainBinding;
import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.ui.game_activity.gameMenu;
import com.example.webmasters.ui.graphic_design.GraphicDesignActivity;
import com.example.webmasters.ui.web_store.WebStoreActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        // Setup view callbacks.
        binding.buttonGraphicDesign.setOnClickListener(this::onNavigationClick);
        binding.buttonWebStore.setOnClickListener((this::onNavigationClick));
        binding.buttonGame.setOnClickListener(this::onNavigationClick);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            mAuth.signInAnonymously();
        }

        binding.getRoot().post(() -> {
            int xBoundary = binding.logoView.getWidth();
            int yBoundary = binding.logoView.getHeight();

            binding.setLogo(new Logo() {{
                setTextValue("Webmasters");
                setTextX(2 * xBoundary / 3);
                setTextY(yBoundary / 2);
                setShapeX(xBoundary / 4);
                setShapeY(yBoundary / 2);
            }});

            binding.executePendingBindings();
        });

        setContentView(binding.getRoot());
    }

    private void onNavigationClick(View view) {
        switch (view.getId()) {
            // Navigate to graphic design activity.
            case R.id.buttonGraphicDesign:
                Intent intentGraph = new Intent(this, GraphicDesignActivity.class);
                startActivity(intentGraph);
                break;
            case R.id.buttonWebStore:
                Intent intentStore = new Intent(this, WebStoreActivity.class);
                startActivity(intentStore);
                break;
            case R.id.buttonGame:
                Intent intentGame = new Intent(this, gameMenu.class);
                startActivity(intentGame);
                break;
            default:
                Log.e("MainActivity", "Invalid navigation!");
        }
    }


}