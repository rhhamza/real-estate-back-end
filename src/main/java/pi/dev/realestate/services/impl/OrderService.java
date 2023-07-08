package pi.dev.realestate.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.Order;
import pi.dev.realestate.entities.StatusType;
import pi.dev.realestate.repositories.CompanyRepository;
import pi.dev.realestate.repositories.OrderRepository;
import pi.dev.realestate.services.interfaces.IOrderService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;


import java.sql.Timestamp;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@EnableScheduling
public class OrderService implements IOrderService {
   @Autowired
   OrderRepository orderRepository;
   @Autowired
   CompanyRepository companyRepository;
   @Autowired
   EmailService emailService;
    @Autowired
    JavaMailSender javaMailSender;
//    @Autowired
//    NotificationRepository notificationRepository;
//    private NotificationService notificationService;

   @Autowired
   public OrderService(EmailService emailService) {
       this.emailService = emailService;
   }

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



   @Override
   public Order addOrderAndAssignToCompany (Order order, int companyId)
   {

       Order savedOrder = orderRepository.save(order);
       savedOrder.setStatus(StatusType.PENDING);
       Company company = companyRepository.findById(companyId).orElse(null);
       if (company != null) {
           savedOrder.setCompany(company);
           orderRepository.save(savedOrder);

           // Send email to the company
           emailService.sendOrderConfirmationEmail(company, savedOrder);
       }

       return savedOrder;
   }


    @Override
    public Order acceptOrder(int id){
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus(StatusType.Accepted);
            orderRepository.save(order);
        }
        return order;
    }

    @Override
    public Order rejectOrder(int id){
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus(StatusType.REJECTED);
            orderRepository.save(order);
        }
        return order;
    }


//    @Override
//    public void sendNotificationBeforeEndDate(int days) {
//        LocalDateTime currentDate = LocalDateTime.now();
//        LocalDateTime endDateThreshold = currentDate.plusDays(days);

//        List<Order> orders = orderRepository.findByEndDateBeforeAndEndDateAfter(currentDate, endDateThreshold);

//        for (Order order : orders) {
//            Company company = order.getCompany();
//            String notificationMessage = "Your order with ID " + order.getId() + " is ending soon.";

//            // Send notification using WebSocket
//            notificationService.sendNotificationToUser(company.getId(), notificationMessage);

//            // Create and save the notification
//            Notification notification = new Notification();
//            notification.setMessage(notificationMessage);
//            notification.setRecipientCompany(company);
//            notification.setTimestamp(LocalDateTime.now());
//            notificationRepository.save(notification);
//        }
//    }



   @Override
   public List<Order> getOrdersByCompany(Integer companyId) {
       return orderRepository.findOrdersByCompanyId(companyId);
   }

//    @Override
//    public OrderReport generateOrderReport(Date startDate, Date endDate) {
//        List<Order> orders = orderRepository.findByStartDateBetween(startDate, endDate);

//        int totalOrders = orders.size();
//        int totalRevenue = 0;
//        int minPrice = Integer.MAX_VALUE;
//        int maxPrice = Integer.MIN_VALUE;

//        for (Order order : orders) {
//            int price = order.getPrice();
//            totalRevenue += price;

//            if (price < minPrice) {
//                minPrice = price;
//            }

//            if (price > maxPrice) {
//                maxPrice = price;
//            }
//        }

//        double averageOrderPrice = (double) totalRevenue / totalOrders;

//        OrderReport orderReport = new OrderReport();
//        orderReport.setStartDate(startDate);
//        orderReport.setEndDate(endDate);
//        orderReport.setTotalOrders(totalOrders);
//        orderReport.setTotalRevenue(totalRevenue);
//        orderReport.setMinPrice(minPrice);
//        orderReport.setMaxPrice(maxPrice);
//        orderReport.setAverageOrderPrice(averageOrderPrice);

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
@Override
@Scheduled(fixedRate = 60000) // Schedule to run every 60 seconds (1 minute)
public List<Order> allExpiredOrders() {
    LocalDate currentLocalDate = LocalDate.now();
    Date currentDate = java.sql.Date.valueOf(currentLocalDate);
    List<Order> allOrders = orderRepository.findAll();

    List<Order> expiredOrders = allOrders.stream()
            .filter(order -> {
                LocalDate endDate = order.getEndDate();
                long daysDifference = ChronoUnit.DAYS.between(currentLocalDate, endDate);
                return daysDifference < 10;
            }).collect(Collectors.toList());

    List<Company> companiesWithExpiredOrders = expiredOrders.stream()
            .map(Order::getCompany)
            .distinct()
            .collect(Collectors.toList());

    companiesWithExpiredOrders.forEach(this::sendOrderConfirmationEmail);

    return expiredOrders;
}
    public void sendOrderConfirmationEmail(Company company) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(company.getEmail());
        message.setSubject("Expiration order");
        message.setText("Dear " + company.getName() + ",\n\nYour order (ID: ) has been expired.\n\nThank you for check your Order.");

        this.javaMailSender.send(message);
    }

}
