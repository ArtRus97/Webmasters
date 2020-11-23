package com.example.webmasters.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.webmasters.databinding.ActivityProductViewBinding;
import com.example.webmasters.models.webstore.CartProduct;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.ui.WebStoreSingleton;
import com.example.webmasters.ui.web_store.CartActivity;
import com.example.webmasters.ui.web_store.ProductActivity;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.stream.Collectors;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private final LayoutInflater mInflater;
    private final List<? extends Product> mProducts;
    private final Context mContext;

    public ProductAdapter(Context context, List<? extends Product> products) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        // Get unique items.
        mProducts = products.stream().distinct().collect(Collectors.toList());
    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ActivityProductViewBinding mBinding = ActivityProductViewBinding.inflate(mInflater, parent, false);
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

    public void OpenProduct(String productId) {
        Intent intent = new Intent(mContext, ProductActivity.class);
        intent.putExtra("productId", productId);
        mContext.startActivity(intent);
    }

    public void removeProduct(String productId) {
        CartActivity.removeProduct(productId);
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

        public void removeClick() {
            removeProduct(binding.getProduct().getId());
        }
    }
}