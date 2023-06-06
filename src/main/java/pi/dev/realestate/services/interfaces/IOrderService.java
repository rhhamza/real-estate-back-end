package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.Order;

import java.util.List;

public interface IOrderService {
    Order addOrder(Order order);

    List<Order> getAllOrders();

    Order getOrder(int id);

    Order updateOrder(int id, Order updatedOrder);

    void deleteOrder(int id);
}
