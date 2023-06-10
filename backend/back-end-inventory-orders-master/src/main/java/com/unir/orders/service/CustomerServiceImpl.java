package com.unir.orders.service;

import com.unir.orders.data.CustomerRepository;
import com.unir.orders.model.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer getCustomer(long id) {
        return repository.findById(id).orElse(null);
    }


}
