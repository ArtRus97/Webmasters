package com.example.webmasters.services;

import android.util.Log;
import com.example.webmasters.models.graphic_design.Logo;
import com.example.webmasters.types.ILogo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class FirebaseService {
    private final FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser mAuthCurrentUser = mAuth.getCurrentUser();

    public String getUser() {
        return mAuthCurrentUser.getUid();
    }

    public void addLogo(Logo logo) {
        mFirestore
                .collection("logos")
                .document(getUser())
                .set(logo);
    }

    public void getLogo(Consumer<Logo> callback) {
        mFirestore.collection("logos").document(getUser()).get()
        .addOnSuccessListener(documentSnapshot -> {
           callback.accept(documentSnapshot.toObject(Logo.class));
        });
    }
}
