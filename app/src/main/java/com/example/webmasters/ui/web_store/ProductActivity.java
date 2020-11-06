package com.example.webmasters.ui.web_store;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.example.webmasters.R;
import com.example.webmasters.ui.WebStoreSingleton;

import java.util.Objects;

public class ProductActivity extends AppCompatActivity {

    private WebStoreSingleton singleton = WebStoreSingleton.Singleton();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        WebStoreSingleton.Product product = new WebStoreSingleton.Product();

        TextView textViewTitle = findViewById(R.id.textViewTitle);
        TextView textViewDesc = findViewById(R.id.textViewDesc);
        TextView textViewPrice = findViewById(R.id.textViewPrice);

        Intent intent = getIntent();
        String productName = Objects.requireNonNull(intent.getExtras()).getString("productName");

        for (int i=0; i < singleton.products.size(); i++) {
            assert productName != null;
            if (productName.equals(singleton.products.get(i).getTitle())) {
                product = singleton.products.get(i);
            }
        }

        textViewTitle.setText(product.getTitle());
        textViewDesc.setText(product.getDesc());
        textViewPrice.setText(product.getPrice().toString());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webstore_menu, menu);
        return true;
    }
}