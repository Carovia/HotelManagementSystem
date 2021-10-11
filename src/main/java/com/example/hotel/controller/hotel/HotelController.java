package com.example.hotel.controller.hotel;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.po.Comments;
import com.example.hotel.po.HotelRoom;
import com.example.hotel.util.ServiceException;
import com.example.hotel.vo.CommentsVO;
import com.example.hotel.vo.HotelVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private RoomService roomService;


    @PostMapping("/addHotel")
    public ResponseVO createHotel(@RequestBody HotelVO hotelVO) throws ServiceException {
        hotelService.addHotel(hotelVO);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/all")
    public ResponseVO retrieveAllHotels(){
        return ResponseVO.buildSuccess(hotelService.retrieveHotels());
    }

    @PostMapping("/roomInfo")
    public ResponseVO addRoomInfo(@RequestBody HotelRoom hotelRoom) {
        roomService.insertRoomInfo(hotelRoom);
        return ResponseVO.buildSuccess(true);
    }

    @GetMapping("/{hotelId}/detail")
    public ResponseVO retrieveHotelDetail(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(hotelService.retrieveHotelDetails(hotelId));
    }

    @GetMapping("/{userId}/userorderdhotelList")
    public ResponseVO getuserorderdhotelList(@PathVariable Integer userId) {
        return ResponseVO.buildSuccess(hotelService.getuserorderdhotelList(userId));
    }

    @PostMapping("/deleteHotel")
    public ResponseVO deleteHotel(@RequestParam Integer hotelId) {
        hotelService.deleteHotel(hotelId);
        return ResponseVO.buildSuccess(true);
    }

    @PostMapping("/setHotelidRate")
    public ResponseVO setHotelidRate(@RequestBody HotelVO hotelVO){
        return hotelService.setHotelidRate(hotelVO);
    }


    @GetMapping("/{hotelId}/getCommentsByHotelId")
    public ResponseVO getCommentsByHotelId(@PathVariable Integer hotelId){
        return ResponseVO.buildSuccess(hotelService.getComments(hotelId));
    }
    @PostMapping("/addComment")
    public ResponseVO addComment(@RequestBody CommentsVO commentsVO){
        hotelService.addComment(commentsVO);
        return ResponseVO.buildSuccess(true);
    }
}
