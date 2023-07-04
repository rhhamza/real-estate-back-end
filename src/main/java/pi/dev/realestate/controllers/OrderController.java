package pi.dev.realestate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pi.dev.realestate.entities.Order;
import pi.dev.realestate.services.interfaces.IOrderService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
//
//    @Autowired
//    IOrderService iOrderService;
//
//    @PostMapping("/add")
//    public ResponseEntity<Object> addOrder(@RequestBody Order order) {
//        Order addedOrder = iOrderService.addOrder(order);
//        return new ResponseEntity<>(addedOrder, HttpStatus.CREATED);
//    }
//
//
//
//    @PostMapping("/addOrder/{coId}")
//    public Order addOrderAndAssignToCompany(@RequestBody Order order, @PathVariable("coId") int companyId) {
//        return iOrderService.addOrderAndAssignToCompany(order, companyId);
//    }
//
//    @GetMapping("/all")
//    public ResponseEntity<Object> getAllOrders() {
//        List<Order> orders = iOrderService.getAllOrders();
//        return new ResponseEntity<>(orders, HttpStatus.OK);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Object> getOrderById(@PathVariable int id) {
//        Order order = iOrderService.getOrder(id);
//        if (order != null) {
//            return new ResponseEntity<>(order, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Object> updateOrder (@PathVariable int id, @RequestBody Order updatedOrder ) {
//        Order order  = iOrderService.updateOrder (id, updatedOrder );
//        if (order  != null) {
//            return new ResponseEntity<>(order , HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteOrder(@PathVariable int id) {
//        iOrderService.deleteOrder (id);
//        return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
//    }

    // @PutMapping("accept/{id}")
    // public ResponseEntity<Object> acceptOrder (@PathVariable int id ) {
    //     Order order  = iOrderService.acceptOrder (id);
    //     if (order  != null) {
    //         return new ResponseEntity<>(order , HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
    //     }
    // }

    // @PutMapping("reject/{id}")
    // public ResponseEntity<Object> rejectOrder (@PathVariable int id ) {
    //     Order order  = iOrderService.rejectOrder (id);
    //     if (order  != null) {
    //         return new ResponseEntity<>(order , HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
    //     }
    // }

}
