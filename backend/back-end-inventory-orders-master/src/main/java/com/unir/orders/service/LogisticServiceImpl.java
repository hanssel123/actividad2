package com.unir.orders.service;

import com.unir.orders.data.LogisticRepository;
import com.unir.orders.model.response.Logistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticServiceImpl implements LogisticService{
    @Autowired
    private LogisticRepository repository;

    @Override
    public List<Logistic> getLogistics() {
        return repository.getLogistics();
    }
}
