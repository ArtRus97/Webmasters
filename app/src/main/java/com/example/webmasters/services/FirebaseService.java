package com.example.webmasters.services;

import android.util.Log;
import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.models.graphic_design.Shape;
import com.example.webmasters.models.graphic_design.Theme;
import com.example.webmasters.models.graphic_design.utils.ShapeFactory;
import com.example.webmasters.models.webstore.CartProduct;
import com.example.webmasters.models.webstore.Product;
import com.example.webmasters.types.ILogo;
import com.example.webmasters.types.ShapeType;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class FirebaseService {
    final String TAG = "FirebaseService";
    final String PRODUCT_COLLECTION = "Products";
    final String CART_COLLECTION = "carts";
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


    /****************************************
     * PRODUCT-SPECIFIC DATABASE OPERATIONS *
     ****************************************/

    /**
     * getProducts returns all available products from a database.
     *
     * @param callback
     */
    public void getProducts(Consumer<List<Product>> callback) {
        mFirestore
                .collection(PRODUCT_COLLECTION)
                .get()
                .addOnCompleteListener(task -> {
                    List<Product> productList = new ArrayList<>();
                    for (DocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        Product product = document.toObject(Product.class);
                        if (product != null) {
                            product.setId(document.getId());
                            productList.add(product);
                        }
                    }
                    callback.accept(productList);
                });
    }


    /**************************************
     * CART-SPECIFIC DATABASE OPERATIONS *
     *************************************/

    /**
     * getUserCarDocument configures firestore to
     * return the user-specific cart document.
     *
     * @return user-specific cart document
     */
    public DocumentReference getUserCartDocument() {
        return mFirestore
                // From cart collection.
                .collection(CART_COLLECTION)
                // Get the user specific cart document reference.
                .document(getUser());
    }

    /**
     * getCart returns the user's shopping cart from a database.
     *
     * @param callback
     */
    public void getCart(Consumer<List<CartProduct>> callback) {
        Log.d(TAG, "Fetching products from shopping cart...");

        getUserCartDocument()
                .collection(PRODUCT_COLLECTION)
                .get()
                .addOnCompleteListener(task -> {
                    Log.d(TAG, "Successfully fetched the products from shopping cart!");
                    List<CartProduct> cartProductList = new ArrayList<>();
                    for (DocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        CartProduct product = document.toObject(CartProduct.class);
                        cartProductList.add(product);
                    }
                    callback.accept(cartProductList);
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Failed to fetch products from shopping cart...");
                    e.printStackTrace();
                });
    }

    /**
     * addToCart stores user's new cart product in the database.
     *
     * @param cartProduct (CartProduct)
     */
    public void addToCart(CartProduct cartProduct, Consumer<CartProduct> callback) {
        Log.d(TAG, "Adding a product to cart..");

        getUserCartDocument()
                // To product collection.
                .collection(PRODUCT_COLLECTION)
                // Add a new product.
                .document(cartProduct.getId())
                .set(cartProduct)
                .addOnSuccessListener(documentReference -> {
                    Log.d(TAG, "Successfully added a product to cart!");
                    callback.accept(cartProduct);
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Failed to add a product to cart!");
                    e.printStackTrace();
                });
    }

    public void removeFromCart(CartProduct cartProduct, Consumer<CartProduct> callback) {
        getUserCartDocument()
                // From product collection.
                .collection(PRODUCT_COLLECTION)
                // Get the removed product.
                .document(cartProduct.getId())
                // Delete it from the collection.
                .delete()
                .addOnSuccessListener(unused -> {
                    Log.d(TAG, "Successfully removed a product from cart!");
                    callback.accept(cartProduct);
                })
                .addOnFailureListener(e -> {
                    Log.e(TAG, "Failed to remove a product from cart!");
                    e.printStackTrace();
                });
    }

}
