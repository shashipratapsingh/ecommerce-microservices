package OrderService.service.impl;

import OrderService.dto.ProductResponse;
import OrderService.entity.Order;
import OrderService.repository.OrderRepository;
import OrderService.service.OrderService;
import OrderService.utility.ProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductClient productClient;

    @Transactional
    public Order placeOrder(Long productId, int quantity) {
        ProductResponse product = productClient.getProductById(productId);
        if (product.getStock() < quantity) {
            throw new RuntimeException("Insufficient stock for product ID: " + productId);
        }
        double totalPrice = quantity * product.getPrice();
        Order order = new Order();
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);
        order.setStatus("Pending");
        productClient.reduceStock(productId, quantity);
        return repository.save(order);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return repository.findById(id);
    }

    public List<Order> getOrdersByStatus(String status) {
        return repository.findByStatus(status);
    }

    @Transactional
    public Order updateOrderStatus(Long id, String status) {
        return repository.findById(id).map(order -> {
            order.setStatus(status);
            return repository.save(order);
        }).orElse(null);
    }

    @Transactional
    public void cancelOrder(Long id) {
        repository.findById(id).ifPresent(order -> {
            order.setStatus("Canceled");
            repository.save(order);
        });
    }
}
