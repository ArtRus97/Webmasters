package com.example.webmasters.ui.web_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.webmasters.R;
import com.example.webmasters.adapters.ProductAdapter;
import com.example.webmasters.databinding.ActivityProductViewBinding;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.ui.WebStoreSingleton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WebStoreActivity extends AppCompatActivity {

    ProductAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_store);
        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewProducts);

        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        WebStoreSingleton.getInstance(this).getProducts(products -> {
            recyclerViewAdapter = new ProductAdapter(this, products);
            recyclerViewProducts.setAdapter(recyclerViewAdapter);
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webstore_menu, menu);
        return true;
    }

    public void openCart(MenuItem item) {
        Intent intentStore = new Intent(this, CartActivity.class);
        startActivity(intentStore);
    }

}