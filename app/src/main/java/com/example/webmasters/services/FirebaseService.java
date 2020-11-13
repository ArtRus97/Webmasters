package com.example.webmasters.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseService {
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser mAuthCurrentUser = mAuth.getCurrentUser();

    public String getUser() {
        return mAuthCurrentUser.getUid();
    }
}
