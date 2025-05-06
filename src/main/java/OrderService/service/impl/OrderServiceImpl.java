package OrderService.service.impl;

import OrderService.dto.ProductResponse;
import OrderService.dto.ReduceStockRequest;
import OrderService.entity.Order;
import OrderService.repository.OrderRepository;
import OrderService.service.OrderService;
import OrderService.utility.ProductClient;
import ProductService.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

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
        // ✅ Fix: Send ReduceStockRequest as JSON Body
        productClient.reduceStock(productId, new ReduceStockRequest(quantity));
        return repository.save(order);
    }
    public List<Order> getAllOrders() {
        return repository.findAll();
    }
    public Optional<Order> getOrderById(Long id) {
        return repository.findById(id);
    }
    @Override
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
    @Autowired
    private RestTemplate restTemplate;

    public Product getProductById(Long productId) {
        String productServiceUrl = "http://localhost:8083/products/" + productId; // Replace with actual ProductService URL or Eureka name

        ResponseEntity<Product> response = restTemplate.getForEntity(productServiceUrl, Product.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to fetch product with ID: " + productId);
        }
    }
}
