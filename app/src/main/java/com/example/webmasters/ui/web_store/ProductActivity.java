package com.example.webmasters.ui.web_store;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.webmasters.R;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.ui.WebStoreSingleton;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProductActivity extends AppCompatActivity {
    Intent intent = getIntent();
    String productId = Objects.requireNonNull(intent.getExtras()).getString("productId");
    Product product = WebStoreSingleton.getInstance(this).getProduct(productId);
    EditText editTextAmount = findViewById(R.id.editTextAmount);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        TextView textViewTitle = findViewById(R.id.labelTitle);
        TextView textViewDesc = findViewById(R.id.labelDescription);
        TextView textViewPrice = findViewById(R.id.labelPrice);
        ImageView imageViewPic = findViewById(R.id.imageViewPic);

        textViewTitle.setText(product.getName());
        textViewDesc.setText(product.getDescription());
        textViewPrice.setText(product.getPrice().toString());
        if (!product.getImageUrl().isEmpty())
            Picasso.get().load(product.getImageUrl()).into(imageViewPic);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webstore_menu, menu);
        return true;
    }

    public void addToCart(View view) {
        WebStoreSingleton.getInstance(this).addToCart(productId, editTextAmount.getText().toString());
    }

    public void openCart(MenuItem item) {
        Intent intentStore = new Intent(this, CartActivity.class);
        startActivity(intentStore);
    }
}