package com.example.webmasters.models.webstore;

public class CartProduct extends Product {
    private int mAmount = 0;

    @Override
    public float getPrice() {
        return super.getPrice() * mAmount;
    }

    public final int getAmount() {
        return mAmount;
    }

    public final void setAmount(final int amount) {
        mAmount = amount;
    }
}
