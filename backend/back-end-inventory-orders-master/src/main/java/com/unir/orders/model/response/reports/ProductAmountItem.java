package com.unir.orders.model.response.reports;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAmountItem {
    private double amount;
    private String productName;
    private String productCategory;
}
