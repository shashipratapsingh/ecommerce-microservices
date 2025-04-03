package OrderService.service;
import OrderService.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {


    public Order placeOrder(Long productId, int quantity);

    public List<Order> getAllOrders();

    public Optional<Order> getOrderById(Long id);

    public List<Order> getOrdersByStatus(String status);

    public Order updateOrderStatus(Long id, String status);

    public void cancelOrder(Long id);
}
