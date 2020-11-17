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
import com.example.webmasters.databinding.ActivityProductViewBinding;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.ui.WebStoreSingleton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WebStoreActivity extends AppCompatActivity {

    FillProductList recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_store);
        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewProducts);

        recyclerViewProducts.setLayoutManager(new LinearLayoutManager(this));
        WebStoreSingleton.getInstance(this).getProducts(products -> {
            recyclerViewAdapter = new FillProductList(this, products);
            recyclerViewProducts.setAdapter(recyclerViewAdapter);
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webstore_menu, menu);
        return true;
    }

    public void OpenProduct(String productId) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra("productId", productId);
        startActivity(intent);
    }

    public void openCart(MenuItem item) {
        Intent intentStore = new Intent(this, CartActivity.class);
        startActivity(intentStore);
    }


    public class FillProductList extends RecyclerView.Adapter<FillProductList.MyViewHolder> {
        private LayoutInflater mInflater;
        private List<Product> mProducts;

        public FillProductList(Context context, List<Product> products) {
            mInflater = LayoutInflater.from(context);
            mProducts = products;
        }

        @NonNull
        @Override
        public FillProductList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ActivityProductViewBinding mBinding = ActivityProductViewBinding.inflate(getLayoutInflater(), parent, false);
            return new MyViewHolder(mBinding);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Product product = mProducts.get(position);
            holder.binding.setHolder(holder);
            holder.binding.setProduct(product);
            if (!product.getImageUrl().isEmpty())
                Picasso.get().load(product.getImageUrl()).into(holder.image);
        }

        @Override
        public int getItemCount() {
            return mProducts.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ActivityProductViewBinding binding;
            ImageView image;

            public MyViewHolder(ActivityProductViewBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
                image = binding.imageProduct;
            }

            public void handleClick() {
                OpenProduct(binding.getProduct().getId());
            }
        }
    }
}