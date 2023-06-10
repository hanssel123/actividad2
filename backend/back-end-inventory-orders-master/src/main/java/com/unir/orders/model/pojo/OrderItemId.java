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
public class OrderItemId {
    @Column(name = "idOrder")
    private Long idOrder;

    @Column(name = "idItem")
    private Long idItem;
}
