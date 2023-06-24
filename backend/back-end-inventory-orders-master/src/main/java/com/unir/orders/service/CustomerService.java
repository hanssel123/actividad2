package com.unir.orders.service;

import com.unir.orders.model.pojo.Customer;

import java.util.List;

public interface CustomerService {
    public List<Customer> getCustomers();

    public Customer getCustomer(long id);

    public Boolean removeCustomer(long id);
}
