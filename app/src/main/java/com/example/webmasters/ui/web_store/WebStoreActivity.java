package com.example.webmasters.ui.web_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.webmasters.R;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.services.ProductApi;
import com.example.webmasters.ui.WebStoreSingleton;
import com.example.webmasters.ui.game_activity.gameActivity;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

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
            View view = mInflater.inflate(R.layout.activity_product_view, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Product product = mProducts.get(position);
            String title = product.getName();
            String desc = product.getDescription();
            String price = String.format(Locale.ENGLISH, "%.2f", product.getPrice());

            holder.id = product.getId();
            holder.textViewTitle.setText(title);
            holder.textViewDesc.setText(desc);
            holder.textViewPrice.setText(price);
        }

        @Override
        public int getItemCount() {
            return mProducts.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            String id;
            TextView textViewTitle;
            TextView textViewDesc;
            TextView textViewPrice;

            public MyViewHolder(View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.textViewTitle);
                textViewDesc = itemView.findViewById(R.id.textViewDesc);
                textViewPrice = itemView.findViewById(R.id.textViewPrice);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "position : " + getLayoutPosition() + " text : ", Toast.LENGTH_SHORT).show();;
                OpenProduct(id);
            }
        }
    }
}