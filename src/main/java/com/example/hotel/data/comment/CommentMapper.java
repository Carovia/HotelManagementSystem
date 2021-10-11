package com.example.hotel.data.comment;

import com.example.hotel.po.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {
    int insertComment(Comments comments);

    List<Comments> selectByHotelId(Integer hotelId);
}
