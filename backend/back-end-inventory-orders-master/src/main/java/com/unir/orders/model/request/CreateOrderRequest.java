package com.unir.orders.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    private String currency;
    private long idCustomer;
    private String number;
    private String paymentMethod;
    private List<ProductExtraInfo> productsExtraInfo;
}
