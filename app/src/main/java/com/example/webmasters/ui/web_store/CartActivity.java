package com.example.webmasters.ui.web_store;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewCart);


        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        List<CartProduct> cartProducts = WebStoreSingleton.getInstance(this).getCart();
        recyclerViewAdapter = new ProductAdapter(this, cartProducts);
        recyclerViewProducts.setAdapter(recyclerViewAdapter);

        TextView textViewTotal = findViewById(R.id.textViewTotal);

        double total = 0.0;
        for (int position=0; position<WebStoreSingleton.getInstance(this).getCart().size(); position++) {
            total = total + WebStoreSingleton.getInstance(this).getCart().get(position).getPrice();
        }
        textViewTotal.setText(Double.toString(total));
    }

}