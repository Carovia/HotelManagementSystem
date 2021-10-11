package com.example.hotel.controller.order;

import com.example.hotel.bl.order.OrderService;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */


@RestController()
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseVO reserveHotel(@RequestBody OrderVO orderVO){
        return orderService.addOrder(orderVO);
    }

    @GetMapping("/getAllOrders")
    public ResponseVO retrieveAllOrders(){
        return ResponseVO.buildSuccess(orderService.getAllOrders());
    }

    @GetMapping("/getExceptionalOrder")
    public ResponseVO retrieveExceptionalOrders(){ return ResponseVO.buildSuccess(orderService.getExceptionalOrders()); }

    @GetMapping("/{userid}/getUserOrders")
    public  ResponseVO retrieveUserOrders(@PathVariable int userid){
        return ResponseVO.buildSuccess(orderService.getUserOrders(userid));
    }

    @GetMapping("/{userId}, {hotelId}/getUserHotelOrders")
    public ResponseVO getUserHotelOrders(@PathVariable int userId, @PathVariable int hotelId){
        return ResponseVO.buildSuccess(orderService.getUserHotelOrders(userId, hotelId));
    }

    @GetMapping("/{orderid}/annulOrder")
    public ResponseVO annulOrder(@PathVariable int orderid){
        return orderService.annulOrder(orderid);
    }

    @GetMapping("/{hotelId}/allOrders")
    public ResponseVO retrieveHotelOrders(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(orderService.getHotelOrders(hotelId));
    }

    @PostMapping("/checkIn")
    public ResponseVO checkIn(@RequestParam Integer orderId) {
        orderService.checkIn(orderId);
        return ResponseVO.buildSuccess(true);
    }

    @PostMapping("/checkOut")
    public ResponseVO checkOut(@RequestBody OrderVO orderVO) {
        orderService.checkOut(orderVO);
        return ResponseVO.buildSuccess(true);
    }

    @PostMapping("/deleteOrder")
    public ResponseVO deleteOrder(@RequestBody OrderVO order) {
        orderService.deleteOrderById(order);
        return ResponseVO.buildSuccess(true);
    }

    @PostMapping("/markOrderAsHandled")
    public ResponseVO markOrderAsHandled(@RequestBody OrderVO orderVO){
        return orderService.markOrderAsHandled(orderVO.getId());
    }
}
