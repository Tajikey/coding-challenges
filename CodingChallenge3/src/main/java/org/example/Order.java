package org.example;

import com.google.gson.annotations.SerializedName;

public record Order(@SerializedName("customer_number") String customerNumber, Invoice invoice) {
}
