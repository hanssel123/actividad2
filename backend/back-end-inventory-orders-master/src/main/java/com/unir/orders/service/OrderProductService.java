package com.unir.orders.service;

import com.unir.orders.model.response.reports.ProductAmountItem;

import java.util.Date;
import java.util.List;

public interface OrderProductService {
    List<ProductAmountItem> reportProductsAmountUntilDate(Date maxDate);
}
