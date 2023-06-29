package com.unir.orders.controller;

import com.unir.orders.model.response.ReportSalesGeneral;
import com.unir.orders.model.response.reports.ProductAmountItem;
import com.unir.orders.service.OrderProductServiceImpl;
import com.unir.orders.service.OrdersService;
import com.unir.orders.service.OrdersServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("reports")
@RequiredArgsConstructor
@Slf4j
public class ReportsController {
    private final OrderProductServiceImpl orderProductService;
    private final OrdersServiceImpl ordersService;

    @GetMapping("/products-ordered/{maxDate}")
    public ReportSalesGeneral getReportSalesGeneralUntilDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date maxDate) {
        List<ProductAmountItem> productsAmount = orderProductService.reportProductsAmountUntilDate(maxDate);
        double totalAmount = ordersService.totalAmountUntilDate(maxDate);

        ReportSalesGeneral report = ReportSalesGeneral.builder()
                .totalAmount(totalAmount)
                .productAmounts(productsAmount)
                .build();

        return report;
    }
}
