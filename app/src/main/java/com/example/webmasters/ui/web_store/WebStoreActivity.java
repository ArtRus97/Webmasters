package com.example.webmasters.ui.web_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.webmasters.R;
import com.example.webmasters.ui.WebStoreSingleton;

import java.util.ArrayList;
import java.util.List;

public class WebStoreActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProducts;
    private ArrayList<String> productNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_store);
        recyclerViewProducts = findViewById(R.id.recyclerViewProducts);

        productNames.add("Title");
        ArrayAdapter apapter = new fillProductList(this, productNames);

        //recyclerViewProducts.setAdapter(apapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.webstore_menu, menu);
        return true;
    }

    public class fillProductList extends ArrayAdapter<String> {
        private Context context;

        public fillProductList(Context context, ArrayList<String> objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View recyclerView;

            if (convertView == null) {
                recyclerView = inflater.inflate(R.layout.activity_product, null);

                TextView desc = findViewById(R.id.textViewDesc);
                WebStoreSingleton singleton = WebStoreSingleton.Singleton();
                desc.setText(singleton.desc);

                TextView title = findViewById(R.id.textViewTitle);
                title.setText(productNames.get(position));
            }
            else
                recyclerView = convertView;

            return recyclerView;
        }
    }
}