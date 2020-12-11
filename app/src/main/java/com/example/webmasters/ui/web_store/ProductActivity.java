package com.example.webmasters.ui.web_store;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.webmasters.R;
import com.example.webmasters.databinding.ActivityProductBinding;
import com.example.webmasters.models.webstore.Product;
import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ProductActivity extends AppCompatActivity {
    private Product mProduct;
    private ActivityProductBinding mBinding;
    private WebStoreSingleton mWebstoreServices = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        Intent intent = getIntent();
        String productId = Objects.requireNonNull(intent.getExtras()).getString("productId");
        mWebstoreServices = WebStoreSingleton.getInstance();
        mProduct = mWebstoreServices.getProduct(productId);


        // Display product image from URL if one is available.
        if (!mProduct.getImageUrl().isEmpty())
            Picasso.get().load(mProduct.getImageUrl()).into(mBinding.imageViewPic);

        mBinding.setProduct(mProduct);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webstore_menu, menu);
        return true;
    }

    public void onAddToCart(View view) {
        mWebstoreServices.addToCart(mProduct.getId(), getNumberOfItems(), null);
    }

    public void openCart(MenuItem item) {
        Intent intentStore = new Intent(this, CartActivity.class);
        startActivity(intentStore);
    }

    public void openHome(MenuItem item) {
        Intent intentStore = new Intent(this, WebStoreActivity.class);
        startActivity(intentStore);
    }

    private int getNumberOfItems() {
        return Integer.parseInt(mBinding.editTextAmount.getText().toString());
    }
}