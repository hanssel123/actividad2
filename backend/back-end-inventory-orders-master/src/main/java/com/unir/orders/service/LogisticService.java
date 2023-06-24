package com.unir.orders.service;

import com.unir.orders.model.response.Logistic;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogisticService {
    List<Logistic> getLogistics();
}
