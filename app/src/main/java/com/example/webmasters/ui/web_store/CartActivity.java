package com.example.webmasters.ui.web_store;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.webmasters.R;
import com.example.webmasters.adapters.ProductAdapter;
import com.example.webmasters.databinding.ActivityCartBinding;
import com.example.webmasters.models.webstore.CartProduct;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.ui.WebStoreSingleton;

import java.math.BigDecimal;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    WebStoreSingleton webstoreServices;
    ProductAdapter recyclerViewAdapter;
    ActivityCartBinding mBinding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        webstoreServices = WebStoreSingleton.getInstance();

        mBinding.recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));

        webstoreServices.getCart(cart -> {
            recyclerViewAdapter = new ProductAdapter(this, cart);
            recyclerViewAdapter.setRemoveHandler(this::removeProduct);
            mBinding.recyclerViewCart.setAdapter(recyclerViewAdapter);
            calculateTotal();
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

    public void openHome(MenuItem item) {
        Intent intentStore = new Intent(this, WebStoreActivity.class);
        startActivity(intentStore);
    }

    public void removeProduct(Product product) {
        webstoreServices.removeFromCart(product, (Integer position) -> {
            recyclerViewAdapter.notifyDataSetChanged();
            calculateTotal();
        });
    }

    public void onPurchase(View view) {
        webstoreServices.clearCart(unused -> {
            recyclerViewAdapter.notifyDataSetChanged();
            calculateTotal();
        });
    }

    private void calculateTotal() {
        webstoreServices.getCart(cartProducts -> {
            double total = cartProducts.stream()
                    .mapToDouble(CartProduct::getPrice)
                    .reduce(Double::sum)
                    .orElse(0);

            BigDecimal totalD = BigDecimal.valueOf(total);
            totalD = totalD.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            mBinding.textViewTotal.setText(totalD.toString());
        });
    }
}