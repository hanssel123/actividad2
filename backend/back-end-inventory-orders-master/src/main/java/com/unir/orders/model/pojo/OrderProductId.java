package com.unir.orders.model.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductId {
    @Column(name = "idOrder")
    private long idOrder;

    @Column(name = "idProduct")
    private long idProduct;
}
