package com.example.webmasters.ui.web_store;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.webmasters.R;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.ui.WebStoreSingleton;

import java.util.Objects;

public class ProductActivity extends AppCompatActivity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        TextView textViewTitle = findViewById(R.id.labelTitle);
        TextView textViewDesc = findViewById(R.id.labelDescription);
        TextView textViewPrice = findViewById(R.id.labelPrice);

        Intent intent = getIntent();
        String productName = Objects.requireNonNull(intent.getExtras()).getString("productId");

        Product product = WebStoreSingleton.getInstance(this).getProduct(productName);

        textViewTitle.setText(product.getName());
        textViewDesc.setText(product.getDescription());
        textViewPrice.setText(product.getPrice().toString());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webstore_menu, menu);
        return true;
    }
}