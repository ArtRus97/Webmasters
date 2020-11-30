package com.example.webmasters.services;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.models.graphic_design.Shape;
import com.example.webmasters.models.graphic_design.Theme;
import com.example.webmasters.models.graphic_design.utils.ShapeFactory;
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
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class FirebaseService {
    final String LOGO_COLLECTION = "logos";
    final String THEME_COLLECTION = "themes";

    private final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser mAuthCurrentUser = mAuth.getCurrentUser();

    public String getUser() {
        return mAuthCurrentUser.getUid();
    }

    final public void addLogo(final ILogo logo) {
        mFirestore
                .collection(LOGO_COLLECTION)
                .document(getUser())
                .set(logo);
    }

    final public void addTheme(Theme theme) {
        mFirestore
                .collection(THEME_COLLECTION)
                .document(getUser())
                .set(theme);
    }

    /**
     * getLogo fetches logo created by the user from the firestore.
     *
     * @param callback (Consumer<Logo>) getting executed after logo has been fetched.
     */
    public void getLogo(Consumer<Logo> callback) {
        mFirestore.collection(LOGO_COLLECTION)
                .document(getUser())
                .get()
                .addOnSuccessListener(document -> {
                    // Convert stored data to Logo.
                    Logo logo = document.toObject(Logo.class);
                    if (logo != null) {
                        // User shape factory to create logo shape based
                        // on the stored parameters.
                        ShapeType shapeType = logo.getShape().getType();
                        Shape shape = ShapeFactory.applyShapeType(logo.getShape(), shapeType);
                        logo.setShape(shape);
                    }
                    // Execute the given callback.
                    callback.accept(logo);
                })
                // In case fetching logo fails for some reason.
                .addOnFailureListener(e -> callback.accept(null));
    }

    public void getTheme(Consumer<Theme> callback) {
        mFirestore.collection(THEME_COLLECTION).document(getUser()).get()
                .addOnSuccessListener(document -> {
                    Theme theme = document.toObject(Theme.class);
                    if (theme != null) {

                    }
                    callback.accept(theme);
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
}
