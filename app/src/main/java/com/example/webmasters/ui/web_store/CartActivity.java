package com.example.webmasters.ui.web_store;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.webmasters.R;
import com.example.webmasters.adapters.ProductAdapter;
import com.example.webmasters.models.webstore.CartProduct;
import com.example.webmasters.ui.WebStoreSingleton;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    ProductAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewCart);


        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        List<CartProduct> cartProducts = WebStoreSingleton.getInstance(this).getCart();
        recyclerViewAdapter = new ProductAdapter(this, cartProducts);
        recyclerViewProducts.setAdapter(recyclerViewAdapter);
    }

}