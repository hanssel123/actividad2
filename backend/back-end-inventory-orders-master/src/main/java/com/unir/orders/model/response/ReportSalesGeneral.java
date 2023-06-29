package com.unir.orders.model.response;

import com.unir.orders.model.response.reports.ProductAmountItem;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReportSalesGeneral {
    private double totalAmount;
    private List<ProductAmountItem> productAmounts;
}
