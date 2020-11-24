package com.example.webmasters.ui.game_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.webmasters.R;

public class gameShop extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_shop);

        Spinner spinner = findViewById(R.id.HeadColorPicker);
        Spinner spinner2 = findViewById(R.id.bodyColorPicker);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Colors, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);
        Button buttonMain = (Button) findViewById(R.id.BackButton);
        buttonMain.setOnClickListener(this::onNavigationClick);
    }

    private void onNavigationClick(View view) {
        Intent intentGraph = new Intent(this, gameMenu.class);
        startActivity(intentGraph);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String text = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}