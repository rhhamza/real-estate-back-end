package pi.dev.realestate.services.interfaces;

import pi.dev.realestate.entities.Order;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {
    Order addOrder(Order order);

    List<Order> getAllOrders();

    Order getOrder(int id);

    Order updateOrder(int id, Order updatedOrder);

    void deleteOrder(int id);

    Order addOrderAndAssignToCompany(Order order, int companyId);

    List<Order> getOrdersByCompany(Integer companyId);

    List<Order> getExpiredOrders();

    double calculateTotalRevenue(Integer companyId);

    List<Order> allExpiredOrders();

    Order acceptOrder(int id);


    Order rejectOrder(int id);
}
