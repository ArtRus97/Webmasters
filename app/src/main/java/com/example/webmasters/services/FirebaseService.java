package com.example.webmasters.services;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.models.graphic_design.Shape;
import com.example.webmasters.models.graphic_design.utils.ShapeFactory;
import com.example.webmasters.models.webstore.CartProduct;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.types.ILogo;
import com.example.webmasters.types.ShapeType;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class FirebaseService {
    private final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser mAuthCurrentUser = mAuth.getCurrentUser();

    public String getUser() {
        return mAuthCurrentUser.getUid();
    }

    public void addLogo(ILogo logo) {
        mFirestore
                .collection("logos")
                .document(getUser())
                .set(logo);
    }

    public void getLogo(Consumer<Logo> callback) {
        mFirestore.collection("logos").document(getUser()).get()
                .addOnSuccessListener(documentSnapshot -> {
                    Logo logo = documentSnapshot.toObject(Logo.class);
                    if (logo != null) {
                        ShapeType shapeType = logo.getShape().getType();
                        Log.d("ASD", shapeType.toString());
                        Shape shape = ShapeFactory.applyShapeType(logo.getShape(), shapeType);
                        logo.setShape(shape);
                    }
                    callback.accept(logo);
                });
    }

    public void getProducts(Consumer<List<Product>> callback) {
        mFirestore.collection("Products").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Product> productList = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                     Product product = document.toObject(Product.class);
                     productList.add(product);
                }
                callback.accept(productList);
            }
        });
    }

    public void addToCart(HashMap cartItem) {
        mFirestore
                .collection("cart")
                .document(getUser())
                .set(cartItem);
    }

    public void getCart(Consumer<List<Product>> callback) {
    }
}
