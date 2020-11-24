package com.example.webmasters.ui.web_store;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.webmasters.R;
import com.example.webmasters.adapters.ProductAdapter;
import com.example.webmasters.models.webstore.CartProduct;
import com.example.webmasters.ui.WebStoreSingleton;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    ProductAdapter recyclerViewAdapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewCart);

        context = this;

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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webstore_menu, menu);
        return true;
    }

    public void openCart(MenuItem item) {
        Intent intentStore = new Intent(this, CartActivity.class);
        startActivity(intentStore);
    }

    public void openHome(MenuItem item) {
        Intent intentStore = new Intent(this, WebStoreActivity.class);
        startActivity(intentStore);
    }

    public void refresh() {
        finish();
        startActivity(getIntent());
    }

    public static void removeProduct(String productId) {
        Toast toast = Toast.makeText(context, "Removed: " + WebStoreSingleton.getInstance(context).mCart.get(productId).getName(),Toast.LENGTH_SHORT);
        WebStoreSingleton.getInstance(context).mCart.remove(productId);
        toast.show();

    }
}