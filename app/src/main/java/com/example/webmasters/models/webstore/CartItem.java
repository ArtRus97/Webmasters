package com.example.webmasters.models.webstore;

public class CartItem {
    private String mId;
    private Integer mAmount;

    final public void setId(final String id) {
        this.mId = id;
    }

    final public void setAmount(final Integer amount) {
        this.mAmount = amount;
    }

    final public String getId() {
        return mId;
    }

    final public Integer getAmount() {
        return mAmount;
    }
}
