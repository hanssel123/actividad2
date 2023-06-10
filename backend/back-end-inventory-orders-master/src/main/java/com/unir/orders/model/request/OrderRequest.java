package com.unir.orders.model.request;

import com.unir.orders.model.pojo.Customer;
import com.unir.orders.model.pojo.Item;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class OrderRequest {
    private long id;
    private Date createdAt;
    private String currency;
    private long idCustomer;
    private String number;
    private String paymentMethod;
    private String status;
    private Double totalAmount;
    private List<ItemDetails> itemDetails;
}
