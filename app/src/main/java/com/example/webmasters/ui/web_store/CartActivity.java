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
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.webmasters.R;
import com.example.webmasters.adapters.ProductAdapter;
import com.example.webmasters.databinding.ActivityProductViewBinding;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.ui.WebStoreSingleton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    ProductAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewCart);

        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        List<Product> cartProducts = WebStoreSingleton.getInstance(this).getCart();
        recyclerViewAdapter = new ProductAdapter(this, cartProducts);
        recyclerViewProducts.setAdapter(recyclerViewAdapter);
    }

}