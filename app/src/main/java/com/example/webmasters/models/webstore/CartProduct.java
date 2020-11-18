package com.example.webmasters.models.webstore;

public class CartProduct extends Product {
    private Integer amount;
    private Float totalPrice;

    public final void setAmount(final Integer amount) {
        this.amount = amount;
    }

    public final void setTotalPrice(final Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public final Integer getAmount() {
        return amount;
    }

    public final Float getTotalPrice() {
        return totalPrice;
    }
}
