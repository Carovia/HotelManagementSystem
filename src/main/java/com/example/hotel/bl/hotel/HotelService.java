package com.example.hotel.bl.hotel;

import com.example.hotel.po.Comments;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.po.Order;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.CommentsVO;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

public interface HotelService {

    /**
     * 添加酒店
     * @param hotelVO
     * @throws
     */
    int addHotel(HotelVO hotelVO) throws ServiceException;


    /**
     * 预订酒店修改剩余客房信息
     * @param hotelId
     * @param roomType
     * @param rooms
     */
    void updateRoomInfo(Integer hotelId, String roomType,Integer rooms);

    /**
     * 列表获取酒店信息
     * @return
     */
    List<HotelVO> retrieveHotels();

    /**
     * 获取用户预订过的酒店
     * @param userId
     * @return
     */
    List<HotelVO> getuserorderdhotelList(Integer userId);

    /**
     * 获取某家酒店详细信息
     * @param hotelId
     * @return
     */
    HotelVO retrieveHotelDetails(Integer hotelId);

    /**
     * 查看酒店剩余某种房间数量
     * @param hotelId
     * @param roomType
     * @return
     */
    int getRoomCurNum(Integer hotelId,String roomType);

    List<Comments> getComments(Integer hotelId);

    void addComment(CommentsVO commentsVO);

    /**
     * 修改酒店信息
     * @param hotelVO
     */
    void modifyHotel(HotelVO hotelVO);

    /**
     * 删除酒店
     * @param hotelId
     */
    void deleteHotel(Integer hotelId);

    /**
     * 设置制定酒店的评分星级
     * @param hotelVO
     */
    public ResponseVO setHotelidRate(HotelVO hotelVO);
}
