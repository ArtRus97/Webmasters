package com.example.webmasters.ui;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webmasters.databinding.ActivityMainBinding;
import com.example.webmasters.databinding.FragmentLogosBinding;
import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.ui.game_activity.gameActivity;
import com.example.webmasters.ui.graphic_design.GraphicDesignActivity;
import com.example.webmasters.R;
import com.example.webmasters.ui.web_store.WebStoreActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());

        // Setup view callbacks.
        binding.buttonGraphicDesign.setOnClickListener(this::onNavigationClick);
        binding.buttonWebStore.setOnClickListener((this::onNavigationClick));
        binding.buttonGame.setOnClickListener(this::onNavigationClick);

        AccountManager accountManager = AccountManager.get(this);

        Account[] list = accountManager.getAccounts();

        for (Account account : list) {
            Log.d("asd", "account = " + account.name);
        }

        binding.getRoot().post(() -> {

            // Update controls based on the size of the logo boundaries.
            Logo logo = new Logo(){{
                    setTextValue("Webmasters");
            }};

            int xBoundary = binding.logoView.getWidth();
            int yBoundary = binding.logoView.getHeight();

            logo.setTextX(2*xBoundary / 3);
            logo.setTextY(yBoundary / 2);
            logo.setShapeX(xBoundary / 4);
            logo.setShapeY(yBoundary / 2);

            binding.setLogo(logo);
            binding.executePendingBindings();
        });

        /*
        Bundle options = new Bundle();
        am.getAccounts();
        am.getAuthToken(
                myAccount_,                     // Account retrieved using getAccountsByType()
                "Manage your tasks",            // Auth scope
                options,                        // Authenticator-specific options
                this,                           // Your activity
                new OnTokenAcquired(),          // Callback called when a token is successfully acquired
                new Handler(new OnError()));    // Callback called if an error occurs
        */
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
                Intent intentGame = new Intent(this, gameActivity.class);
                startActivity(intentGame);
                break;
            default:
                Log.e("MainActivity", "Invalid navigation!");
        }
    }
}