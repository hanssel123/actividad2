package com.unir.orders.service;

import com.unir.orders.data.CustomerRepository;
import com.unir.orders.data.ItemRepository;
import com.unir.orders.data.OrderItemRepository;
import com.unir.orders.data.OrderRepository;
import com.unir.orders.model.pojo.*;
import com.unir.orders.model.request.ItemDetails;
import org.bouncycastle.cms.PasswordRecipient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.unir.orders.facade.ProductsFacade;
import com.unir.orders.model.request.OrderRequest;
import org.springframework.util.StringUtils;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private ProductsFacade productsFacade;

    @Autowired
    private OrderRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public List<Order> getOrders() {
        List<Order> orders = repository.findAll();
        if (orders.size() > 0) {
            return orders;
        } else {
            return null;
        }
    }

    @Override
    public Order createOrder(OrderRequest request) {
        if (request != null && request.getCreatedAt() != null
                && StringUtils.hasLength(request.getCurrency().trim())
                && StringUtils.hasLength(request.getNumber().trim())
                && StringUtils.hasLength(request.getPaymentMethod().trim())
                && request.getTotalAmount() > 0
                && request.getItemDetails().size() > 0) {
            Customer customer = customerRepository.getReferenceById(request.getIdCustomer());

            Order order = Order.builder().createdAt(new Date())
                    .currency(request.getCurrency())
                    .number(request.getNumber())
                    .paymentMethod(request.getPaymentMethod())
                    .status("pending")
                    .totalAmount(request.getTotalAmount())
                    .customer(customer)
                    .build();

            Order orderCreated = repository.save(order);

            /*
             Buscar y registrar items en caso que no existieran a partir de los productos del respectivo microservicio
             */
            List<Item> items = new ArrayList<>();
            for (ItemDetails itemDetails : request.getItemDetails()) {
                long idItem = itemDetails.getIdProduct();
                Item item = itemRepository.findById(idItem).orElse(null);

                if (item == null) {
                    Item itemFromProduct = productsFacade.getItemsFromProducts(String.valueOf(idItem));
                    item = itemRepository.save(itemFromProduct);
                }
                items.add(item);
            }

            /*
            Regitrar los detalles
             */
            for (Item item : items) {
                ItemDetails itemDetails = request.getItemDetails().stream()
                        .filter(i -> i.getIdProduct() == item.getId())
                        .findFirst().orElse(null);

                OrderItem orderItem = OrderItem.builder()
                        .item(item)
                        .order(orderCreated)
                        .billingCycle(itemDetails.getBillingCycle())
                        .unitAmount(itemDetails.getUnitAmount())
                        .build();

                orderCreated.getOrderItems().add(orderItemRepository.save(orderItem));
            }

            return orderCreated;
        } else {
            return null;
        }
    }

}
