package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.comment.CommentMapper;
import com.example.hotel.data.hotel.HotelMapper;
import com.example.hotel.data.hotel.RoomMapper;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.*;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private final static String ACCOUNT_EXIST="修改失败";

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CouponService couponService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int addHotel(HotelVO hotelVO) throws ServiceException {
        User manager = accountService.getUserInfo(hotelVO.getManagerId());
        if(manager == null || !manager.getUserType().equals(UserType.HotelManager)){
            throw new ServiceException("管理员不存在或者无权限！创建酒店失败！");
        }
        Hotel hotel = new Hotel();
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setRate(hotelVO.getRate());
        hotel.setBizRegion(BizRegion.valueOf(hotelVO.getBizRegion()));
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        hotelMapper.insertHotel(hotel);
        return hotel.getId();
    }

    @Override
    public void updateRoomInfo(Integer hotelId, String roomType, Integer rooms) {
        roomService.updateRoomInfo(hotelId,roomType,rooms);
    }

    @Override
    public int getRoomCurNum(Integer hotelId, String roomType) {
        return roomService.getRoomCurNum(hotelId,roomType);
    }

    @Override
    public List<Comments> getComments(Integer hotelId) {
        List<Comments> hotelComments = commentMapper.selectByHotelId(hotelId);
        return hotelComments;
    }

    @Override
    public void addComment(CommentsVO commentsVO) {
        Comments comments = new Comments();
        comments.setContents(commentsVO.getContents());
        comments.setHotelId(commentsVO.getHotelId());
        comments.setUserId(commentsVO.getUserId());
        commentMapper.insertComment(comments);
    }

    @Override
    public List<HotelVO> retrieveHotels() {

        return hotelMapper.selectAllHotel();
    }

    @Override
    public List<HotelVO> getuserorderdhotelList(Integer userId){
        System.out.println("userId : "+userId);
        List<Order> orders=orderMapper.getUserOrders(userId);
        ArrayList<Integer> hotelid=new ArrayList<>();
        ArrayList<HotelVO> hotels=new ArrayList<>();
        System.out.println(orders.size());
        if(orders.size()>0) {
            hotelid.add(orders.get(0).getHotelId());
            for (int i = 1; i < orders.size(); i++) {
                if (hotelid.contains(orders.get(i).getHotelId())) continue;
                else hotelid.add(orders.get(i).getHotelId());
            }
            for(int i=0;i<hotelid.size();i++){
                hotels.add(hotelMapper.selectById(hotelid.get(i)));
            }
        }
        return hotels;
    }

    @Override
    public HotelVO retrieveHotelDetails(Integer hotelId) {
        HotelVO hotelVO = hotelMapper.selectById(hotelId);
        List<HotelRoom> rooms = roomService.retrieveHotelRoomInfo(hotelId);
        List<RoomVO> roomVOS = rooms.stream().map(r -> {
            RoomVO roomVO = new RoomVO();
            roomVO.setId(r.getId());
            roomVO.setPrice(r.getPrice());
            roomVO.setRoomType(r.getRoomType().toString());
            roomVO.setCurNum(r.getCurNum());
            roomVO.setTotal(r.getTotal());
            return roomVO;
        }).collect(Collectors.toList());
        hotelVO.setRooms(roomVOS);

        return hotelVO;
    }

    @Override
    public void modifyHotel(HotelVO hotelVO) {
        Hotel hotel = new Hotel();
        hotel.setId(hotelVO.getId());
        hotel.setDescription(hotelVO.getDescription());
        hotel.setAddress(hotelVO.getAddress());
        hotel.setHotelName(hotelVO.getName());
        hotel.setPhoneNum(hotelVO.getPhoneNum());
        hotel.setManagerId(hotelVO.getManagerId());
        hotel.setHotelStar(HotelStar.valueOf(hotelVO.getHotelStar()));
        hotelMapper.updateHotel(hotel);
    }

    @Override
    public void deleteHotel(Integer hotelId) {
        hotelMapper.deleteHotel(hotelId);
        couponService.deleteCouponByHotelId(hotelId);
        orderService.deleteOrderByHotelId(hotelId);
        roomService.deleteRoomByHotelId(hotelId);
    }

    @Override
    public ResponseVO setHotelidRate(HotelVO hotelVO){
        HotelVO hotelvo=hotelMapper.selectById(hotelVO.getId());
        try{
            if(hotelvo.getRate() < hotelVO.getRate()){
                //用户评价高
                hotelMapper.addRateById(hotelVO.getId());
            }
            if(hotelvo.getRate() > hotelVO.getRate()){
                //用户评价低
                hotelMapper.subRateById(hotelVO.getId());
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }
}
