package com.example.webmasters.ui;

import android.content.Intent;
import android.os.Bundle;
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

        // Use anonymous sign in when using firebase.
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            mAuth.signInAnonymously();
        }

        // Display logo
        binding.getRoot().post(() -> {
            // Get the boundaries of logo view.
            int xBoundary = binding.logoView.getWidth();
            int yBoundary = binding.logoView.getHeight();

            // Display custom logo in the logo view.
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

    /**
     * onNavigationClick handles the navigation between different parts of the application.
     *
     * @param view (View) navigation button getting clicked.
     */
    public void onNavigationClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.buttonGraphicDesign:
                intent = new Intent(this, GraphicDesignActivity.class);
                break;
            case R.id.buttonWebStore:
                intent = new Intent(this, WebStoreActivity.class);
                break;
            case R.id.buttonGame:
                intent = new Intent(this, gameMenu.class);
                break;
            default:
                return;

        }
        startActivity(intent);
    }


}