package com.unir.orders.service;

import com.unir.orders.data.CustomerRepository;
import com.unir.orders.data.OrderProductRepository;
import com.unir.orders.data.OrderRepository;
import com.unir.orders.data.ProductRepository;
import com.unir.orders.model.pojo.*;
import com.unir.orders.model.request.ProductExtraInfo;
import com.unir.orders.model.request.UpdateInventoryRequest;
import com.unir.orders.model.request.UpdateStatusRequest;
import com.unir.orders.model.response.ProductResponse;
import com.unir.orders.model.response.ReviewOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.unir.orders.facade.ProductsFacade;
import com.unir.orders.model.request.CreateOrderRequest;
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
    private ProductRepository productRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

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
    public Order getOrder(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(CreateOrderRequest request) {
        if (request != null
                && StringUtils.hasLength(request.getCurrency().trim())
                && StringUtils.hasLength(request.getNumber().trim())
                && StringUtils.hasLength(request.getPaymentMethod().trim())
                && request.getProductsExtraInfo().size() > 0) {
            Customer customer = customerRepository.findById(request.getIdCustomer()).orElse(null);

            List<OrderProduct> orderProducts = new ArrayList<>();

            /**
             * Buscar productos existentes o registrar productos consultando al microservicio
             */
            List<Product> products = new ArrayList<>();
            for (ProductExtraInfo productExtraInfo : request.getProductsExtraInfo()) {
                long idItem = productExtraInfo.getIdProduct();
                Product product = productRepository.findById(idItem).orElse(null);

                if (product == null) {
                    // Obtener el producto del microservicio y registrarlo
                    ProductResponse productFromMaster = productsFacade.getProduct(idItem);

                    Product productToSave = Product.builder()
                            .id(productFromMaster.getId())
                            .name(productFromMaster.getName())
                            .category(productFromMaster.getCategory())
                            .currency(productFromMaster.getCurrency())
                            .image(productFromMaster.getImage())
                            .price(productFromMaster.getPrice())
                            .build();

                    product = productRepository.saveAndFlush(productToSave);
                }
                products.add(product);
            }

            /**
             * Calcular el monto total y registrar el pedido
             */
            Double totalAmount = products.stream().map(p -> p.getPrice()).reduce(0.0, Double::sum);

            Order order = Order.builder().createdAt(new Date())
                    .currency(request.getCurrency())
                    .number(request.getNumber())
                    .paymentMethod(request.getPaymentMethod())
                    .status("pending")
                    .totalAmount(totalAmount)
                    .customer(customer)
                    .build();

            Order orderCreated = repository.saveAndFlush(order);

            /**
             * Regitrar los detalles de la orden
             */
            // List<ProductResponse> productsStockUpdated = new ArrayList<>();
            for (Product product : products) {
                ProductExtraInfo productExtraInfo = request.getProductsExtraInfo().stream()
                        .filter(p -> p.getIdProduct() == product.getId())
                        .findFirst().orElse(null);

                OrderProductId orderProductId = new OrderProductId();
                orderProductId.setIdOrder(orderCreated.getId());
                orderProductId.setIdProduct(product.getId());

                OrderProduct orderProduct = OrderProduct.builder()
                        .orderProductId(orderProductId)
                        .order(orderCreated)
                        .product(product)
                        .quantity(productExtraInfo.getQuantity())
                        .build();

                orderProducts.add(orderProductRepository.saveAndFlush(orderProduct));

                // Actualizar stocks (en revisión - usar al microservicio de products para actualizar)
                /*
                UpdateInventoryRequest updateInventoryRequest = new UpdateInventoryRequest();
                updateInventoryRequest.setQuantity(productExtraInfo.getQuantity());
                ProductResponse updatedProductStock = productsFacade.updateProductStock(product.getId(), updateInventoryRequest);

                productsStockUpdated.add(updatedProductStock);

                 */
            }

            if (orderCreated.getId() != 0 && orderProducts.size() > 0) {
                orderCreated.setOrderProducts(orderProducts);
                return orderCreated;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Order updateOrderStatus(long idOrder, UpdateStatusRequest request) {
        if (request != null && request.getStatus() != null) {
            Order order = repository.findById(idOrder).orElse(null);

            if (order != null) {
                String status = request.getStatus();
                order.setStatus(status);

                return repository.save(order);
            } else {
                return null;
            }
        }
        return null;
    }
}
