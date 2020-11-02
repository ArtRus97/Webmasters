package com.example.webmasters.ui.web_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.webmasters.R;
import com.example.webmasters.ui.WebStoreSingleton;

import java.util.ArrayList;
import java.util.List;

public class WebStoreActivity extends AppCompatActivity {

    private ArrayList<String> productNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_store);
        RecyclerView recyclerViewProducts = findViewById(R.id.recyclerViewProducts);

        //productNames.add("Title");
        //ArrayAdapter apapter = new fillProductList(this, productNames);

        productNames.add("Title1");
        productNames.add("Title2");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewProducts.setLayoutManager(layoutManager);

        RecyclerView.Adapter recyclerViewAdapter = new FillProductList(this, productNames);
        recyclerViewProducts.setAdapter(recyclerViewAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webstore_menu, menu);
        return true;
    }



    public static class FillProductList extends RecyclerView.Adapter<FillProductList.MyViewHolder> {
        private List<String> productNames, productDesc;
        private LayoutInflater mInflater;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView textViewTitle;
            TextView textViewDesc;

            public MyViewHolder(View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.textViewTitle);
                textViewDesc = itemView.findViewById(R.id.textViewDesc);
            }
        }

        public FillProductList(Context context, List<String> productNames) {
            this.productNames = productNames;
            this.mInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public FillProductList.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.activity_product, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            String title = productNames.get(position);

            String desc;
            WebStoreSingleton singleton = WebStoreSingleton.Singleton();
            desc = singleton.desc;

            holder.textViewTitle.setText(title);
            holder.textViewDesc.setText(desc);
        }

        @Override
        public int getItemCount() {
            return productNames.size();
        }
    }
}