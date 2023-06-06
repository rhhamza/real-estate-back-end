package pi.dev.realestate.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Order;
import pi.dev.realestate.repositories.OrderRepository;
import pi.dev.realestate.services.interfaces.IOrderService;

import java.sql.Timestamp;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Override
    public Order addOrder(Order order){
        return orderRepository.save(order);
    }
    @Override
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    @Override
    public Order getOrder(int id){
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order updateOrder(int id, Order updatedOrder) {
        Order existingOrder = orderRepository.findById(id).orElse(null);
        if (existingOrder != null) {
            existingOrder.setStartDate(updatedOrder.getStartDate());
            existingOrder.setEndDate(updatedOrder.getEndDate());
            existingOrder.setStatus(updatedOrder.getStatus());
            existingOrder.setPrice(updatedOrder.getPrice());
            existingOrder.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
            orderRepository.save(existingOrder);

        }
        return existingOrder;
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}
