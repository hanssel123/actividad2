package com.unir.orders.service;

import com.unir.orders.data.CustomerRepository;
import com.unir.orders.model.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository repository;

    @Override
    public List<Customer> getCustomers() {
        return repository.findAll();
    }

    @Override
    public Customer getCustomer(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Boolean removeCustomer(long id) {
        Customer customer = repository.findById(id).orElse(null);

        if (customer != null) {
            repository.deleteById(id);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }


}
