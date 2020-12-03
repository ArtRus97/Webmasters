package com.example.webmasters.ui.web_store;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.webmasters.R;
import com.example.webmasters.databinding.ActivityProductBinding;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.ui.WebStoreSingleton;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProductActivity extends AppCompatActivity {
    String productId;
    Product mProduct;
    ActivityProductBinding mBinding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityProductBinding.inflate(getLayoutInflater());
        Intent intent = getIntent();
        productId = Objects.requireNonNull(intent.getExtras()).getString("productId");
        mProduct = WebStoreSingleton.getInstance(this).getProduct(productId);

        mBinding.labelTitle.setText(mProduct.getName());
        mBinding.labelDescription.setText(mProduct.getDescription());
        mBinding.labelPrice.setText(mProduct.getPrice() + "");

        // Display product image from URL if one is available.
        if (!mProduct.getImageUrl().isEmpty())
            Picasso.get().load(mProduct.getImageUrl()).into(mBinding.imageViewPic);

        setContentView(mBinding.getRoot());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webstore_menu, menu);
        return true;
    }

    public void addToCart(View view) {
        int numItems = Integer.parseInt(mBinding.editTextAmount.getText().toString());
        WebStoreSingleton.getInstance(this).addToCartD(mProduct.getId(), numItems);
    }

    public void openCart(MenuItem item) {
        Intent intentStore = new Intent(this, CartActivity.class);
        startActivity(intentStore);
    }

    public void openHome(MenuItem item) {
        Intent intentStore = new Intent(this, WebStoreActivity.class);
        startActivity(intentStore);
    }
}