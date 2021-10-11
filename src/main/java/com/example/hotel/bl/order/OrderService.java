package com.example.hotel.bl.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface OrderService {

    /**
     * 预订酒店
     * @param orderVO
     * @return
     */
    ResponseVO addOrder(OrderVO orderVO);

    /**
     * 获得所有订单信息
     * @return
     */
    List<Order> getAllOrders();

    /**
     * 获得异常订单信息
     * @return
     */
    List<Order> getExceptionalOrders();

    /**
     * 获得指定用户的所有订单信息
     * @param userid
     * @return
     */
    List<Order> getUserOrders(int userid);

    /**
     * 获得用户——酒店对的订单信息
     * @param userid
     * @param hotelid
     * @return
     */
    List<Order> getUserHotelOrders(int userid, int hotelid);

    /**
     * 撤销订单
     * @param orderid
     * @return
     */
    ResponseVO annulOrder(int orderid);

    /**
     * 查看酒店的所有订单
     * @param hotelId
     * @return
     */
    List<Order> getHotelOrders(Integer hotelId);

    /**
     * 删除酒店所有订单
     * @param hotelId
     */
    void deleteOrderByHotelId(Integer hotelId);

    /**
     * 办理入住
     * @param orderId
     */
    void checkIn(Integer orderId);

    /**
     * 办理退房
     * @param orderVO
     */
    void checkOut(OrderVO orderVO);

    /**
     * 删除订单
     * @param orderVO
     */
    void deleteOrderById(OrderVO orderVO);


    ResponseVO markOrderAsHandled(Integer orderId);
}
