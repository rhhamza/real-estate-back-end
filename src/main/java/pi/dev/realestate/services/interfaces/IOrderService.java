package pi.dev.realestate.services.interfaces;

import org.springframework.scheduling.annotation.Scheduled;
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

   // void sendNotificationBeforeEndDate(int days);

    Order acceptOrder(int id);

    Order rejectOrder(int id);

    List<Order> getOrdersByCompany(Integer companyId);

    //        return orderReport;
    //    }
    /*
       @Override
       public double calculateTotalRevenue(Integer companyId) {
           List<Order> orders = orderRepository.findOrdersByCompanyId(companyId);
           double totalRevenue = 0.0;
           for (Order order : orders) {
               totalRevenue += order.getPrice();
           }
           return totalRevenue;
       }*/
    @Scheduled(fixedRate = 60000) // Schedule to run every 60 seconds (1 minute)
    List<Order> allExpiredOrders();

    //OrderReport generateOrderReport(Date startDate, Date endDate);

   // double calculateTotalRevenue(Integer companyId);
}
