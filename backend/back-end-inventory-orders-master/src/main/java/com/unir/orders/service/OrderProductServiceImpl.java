package com.unir.orders.service;

import com.unir.orders.data.OrderProductRepository;
import com.unir.orders.model.response.reports.ProductAmountItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderProductServiceImpl implements OrderProductService {
    @Autowired
    private OrderProductRepository repository;

    @Override
    public List<ProductAmountItem> reportProductsAmountUntilDate(Date maxDate) {
        return repository.reportProductsAmountUntilDate(maxDate);
    }
}
