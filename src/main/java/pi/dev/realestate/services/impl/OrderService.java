package pi.dev.realestate.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pi.dev.realestate.entities.Company;
import pi.dev.realestate.entities.Order;
import pi.dev.realestate.entities.StatusType;
import pi.dev.realestate.repositories.CompanyRepository;
import pi.dev.realestate.repositories.OrderRepository;
import pi.dev.realestate.services.interfaces.IOrderService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.time.temporal.*;


import java.sql.Timestamp;

@AllArgsConstructor
@Service
public class OrderService implements IOrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    EmailService emailService;
    List<Order> expiredOrders;


    @Autowired
    private JavaMailSender javaMailSender;

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

        order.setStatus(StatusType.PENDING);
       Order savedOrder = orderRepository.save(order);
        Company company = companyRepository.findById(companyId).orElse(null);
        if (company != null) {

            order.setCompany(company);
            orderRepository.save(savedOrder);

            // Send email to the company
            emailService.sendOrderConfirmationEmail(company, savedOrder);
            emailService.orderConfirmationByAdmin(company, savedOrder);
        }

        return savedOrder;
    }



    @Override
    public List<Order> getOrdersByCompany(Integer companyId) {
        return orderRepository.findOrdersByCompanyId(companyId);
    }

    LocalDate endDateThreshold = LocalDate.now().plusDays(10);
    @Override
    public List<Order> getExpiredOrders(){
        return expiredOrders = orderRepository.getOrdersWithEndDateBefore(endDateThreshold);
    }


    //


    @Override
    public double calculateTotalRevenue(Integer companyId) {
        List<Order> orders = orderRepository.findOrdersByCompanyId(companyId);
        double totalRevenue = 0.0;
        for (Order order : orders) {
            totalRevenue += order.getPrice();
        }
        return totalRevenue;
    }

    @Override
    //@Scheduled(fixedRate = 60000)
    public List<Order> allExpiredOrders(){

        LocalDate currentLocalDate = LocalDate.now();
        Date currentDate = java.sql.Date.valueOf(currentLocalDate);
        List<Order> allOrders = orderRepository.findByStatus(StatusType.ACCEPTED);


        List<Order> expiredOrders = allOrders.stream()
                .filter(order -> {
                    LocalDate endDate = order.getEndDate();
                    long daysDifference = ChronoUnit.DAYS.between(currentLocalDate, endDate);

                    return  daysDifference < 10;
                }).collect(Collectors.toList());

        List<Company> companiesWithExpiredOrders = expiredOrders.stream()
                .map(Order::getCompany)
                .distinct()
                .collect(Collectors.toList());


        companiesWithExpiredOrders.forEach(emailService::sendExpirationEmail);

        return expiredOrders;
    }

    @Override
    public Order acceptOrder(int id){
        Order order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setStatus(StatusType.ACCEPTED);
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

}