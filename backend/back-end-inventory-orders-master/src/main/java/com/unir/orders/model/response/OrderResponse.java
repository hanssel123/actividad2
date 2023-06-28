package com.unir.orders.model.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.unir.orders.model.pojo.Customer;
import com.unir.orders.model.pojo.OrderProduct;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private long id;
    private Date createdAt;
    private String currency;
    private Customer customer;
    private String number;
    private String paymentMethod;
    private String review;
    private String status;
    private Double totalAmount;
    private List<OrderProduct> orderProducts;
}
