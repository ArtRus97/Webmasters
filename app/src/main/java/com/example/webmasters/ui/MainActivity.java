package com.example.webmasters.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.webmasters.databinding.ActivityMainBinding;
import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.ui.game_activity.gameActivity;
import com.example.webmasters.ui.game_activity.gameMenu;
import com.example.webmasters.ui.graphic_design.GraphicDesignActivity;
import com.example.webmasters.R;
import com.example.webmasters.ui.web_store.WebStoreActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
            mAuth.signInAnonymously()
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                // If sign in fails, display a message to the user.
                                Log.w("ASD", "signInAnonymously:failure", task.getException());
                                Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                            } else {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("ASD", "signInAnonymously:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Log.d("ASD", user.getUid());
                            }

                            // ...
                        }
                    });
        }


        binding.getRoot().post(() -> {

            // Update controls based on the size of the logo boundaries.
            Logo logo = new Logo();
            logo.setTextValue("Webmasters");

            int xBoundary = binding.logoView.getWidth();
            int yBoundary = binding.logoView.getHeight();

            logo.setTextX(2*xBoundary / 3);
            logo.setTextY(yBoundary / 2);
            logo.setShapeX(xBoundary / 4);
            logo.setShapeY(yBoundary / 2);

            binding.setLogo(logo);
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