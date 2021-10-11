package com.example.hotel.blImpl.order;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final static String RESERVE_ERROR = "预订失败";
    private final static String ROOMNUM_LACK = "预订房间数量剩余不足";
    private final static String ANNUL_ERROR = "撤销失败";
    private final static String HANDLE_ERROR = "处理失败";
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    HotelService hotelService;
    @Autowired
    AccountService accountService;
    @Autowired
    OrderService orderService;
    @Autowired
    AccountMapper accountMapper;

    @Override
    public ResponseVO addOrder(OrderVO orderVO) {
        int reserveRoomNum = orderVO.getRoomNum();
        int curNum = hotelService.getRoomCurNum(orderVO.getHotelId(),orderVO.getRoomType());
        if(reserveRoomNum>curNum){
            return ResponseVO.buildFailure(ROOMNUM_LACK);
        }
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String curdate = sf.format(date);
            orderVO.setCreateDate(curdate);
            orderVO.setOrderState("已预订");
            User user = accountService.getUserInfo(orderVO.getUserId());
            orderVO.setClientName(user.getUserName());
            orderVO.setPhoneNumber(user.getPhoneNumber());
            Order order = new Order();
            BeanUtils.copyProperties(orderVO,order);
            orderMapper.addOrder(order);
            hotelService.updateRoomInfo(orderVO.getHotelId(),orderVO.getRoomType(),orderVO.getRoomNum());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(RESERVE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<Order> getAllOrders() {
        checkExceptionOrder();
        return orderMapper.getAllOrders();
    }

    @Override
    public List<Order> getExceptionalOrders() {
        checkExceptionOrder();
        return orderMapper.getExceptionalOrders();
    }

    @Override
    public List<Order> getUserOrders(int userid) {
        checkExceptionOrder();
        return orderMapper.getUserOrders(userid);
    }

    @Override
    public List<Order> getUserHotelOrders(int userid, int hotelid) {
        checkExceptionOrder();
        return orderMapper.getUserHotelOrders(userid, hotelid);
    }

    @Override
    public ResponseVO annulOrder(int orderid) {
        //取消订单逻辑的具体实现（注意可能有和别的业务类之间的交互）
        try{
            Order order = orderMapper.getOrderById(orderid);
            int reserveRoomNum = order.getRoomNum();
            hotelService.updateRoomInfo(order.getHotelId(), order.getRoomType(), -reserveRoomNum);
            orderMapper.annulOrder(orderid);

            //扣除信用值
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Integer userId=order.getUserId();
            User user=accountMapper.getAccountById(userId);
            String checkInDate=order.getCheckInDate();
            Date date = new Date(System.currentTimeMillis());
            Date fromDate2 = sf.parse(checkInDate);
            long from=fromDate2.getTime();
            long to=date.getTime();
            int hours = (int) ((to - from) / (1000 * 60 * 60));
            if(hours<=6){
                double newCredit=user.getCredit() - order.getPrice()/2;
                accountMapper.updateCredit(userId, newCredit);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ANNUL_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    /**
     * @param hotelId
     * @return
     */
    @Override
    public List<Order> getHotelOrders(Integer hotelId) {
        checkExceptionOrder();
        List<Order> orders = orderService.getAllOrders();
        return orders.stream().filter(order -> order.getHotelId().equals(hotelId)).collect(Collectors.toList());
    }

    @Override
    public void deleteOrderByHotelId(Integer hotelId) {
        orderMapper.deleteByHotelId(hotelId);
    }

    @Override
    public void checkIn(Integer orderId) {
        orderMapper.checkIn(orderId);
    }

    @Override
    public void checkOut(OrderVO order) {
        orderMapper.checkOut(order.getId());
        hotelService.updateRoomInfo(order.getHotelId(), order.getRoomType(), -order.getRoomNum());
    }

    @Override
    public void deleteOrderById(OrderVO order) {
        orderMapper.deleteById(order.getId());
        if (!order.getOrderState().equals("已退房") && !order.getOrderState().equals("已撤销")) {
            hotelService.updateRoomInfo(order.getHotelId(), order.getRoomType(), -order.getRoomNum());
        }
    }

    @Override
    public ResponseVO markOrderAsHandled(Integer orderid) {
        try{
            orderMapper.markOrderAsHandled(orderid);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(HANDLE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    public void checkExceptionOrder() {
        LocalDate cur = LocalDate.now();
        List<Order> orders = orderMapper.getAllOrders();
        for (int i=0; i<orders.size(); i++) {
            if (orders.get(i).getOrderState().equals("已预订") && cur.isAfter(LocalDate.parse(orders.get(i).getCheckInDate()))) {
                orderMapper.setException(orders.get(i).getId());
                accountMapper.recoverCredit(-orders.get(i).getPrice(), orders.get(i).getUserId());
            }
        }
    }
}
