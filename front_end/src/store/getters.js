const getters = {
  //user
  token: state => state.user.token,
  userId: state => state.user.userId,
  userInfo: state => state.user.userInfo,
  userOrderList: state => state.user.userOrderList,
  userHotelOrderList: state => state.user.userHotelOrderList,
  userorderdhotelList: state => state.user.userorderdhotelList,
  //hotel
  hotelListLoading: state => state.hotel.hotelListLoading,
  hotelList: state => state.hotel.hotelList,
  currentHotelInfo: state => state.hotel.currentHotelInfo,
  currentHotelId: state => state.hotel.currentHotelId,
  orderModalVisible: state => state.hotel.orderModalVisible,
  currentOrderRoom: state => state.hotel.currentOrderRoom,
  orderMatchCouponList: state => state.hotel.orderMatchCouponList,
  orderMatchWebCouponList:state=>state.hotel.orderMatchWebCouponList,
  commentList:state=>state.hotel.commentList,
  addCommentVisible:state=>state.hotel.addCommentVisible,
  //admin
  managerList: state => state.admin.managerList,
  marketerList:state=>state.admin.marketerList,
  addMarketerParams:state=>state.admin.addMarketerParams,
  addMarketerModalVisible:state=>state.admin.addMarketerModalVisible,
  updateinfoModalVisible:state=>state.admin.updateinfoModalVisible,
  updateParams:state=>state.admin.updateParams,
  activeuserId: state => state.admin.activeuserId,
  addManagerModalVisible: state => state.admin.addManagerModalVisible,
  addManagerParams: state => state.admin.addManagerParams,
  //hotelManager
  orderList: state => state.hotelManager.orderList,
  addHotelModalVisible: state => state.hotelManager.addHotelModalVisible,
  addRoomModalVisible: state => state.hotelManager.addRoomModalVisible,
  couponVisible: state => state.hotelManager.couponVisible,
  addCouponVisible: state => state.hotelManager.addCouponVisible,
  activeHotelId: state => state.hotelManager.activeHotelId,
  couponList: state => state.hotelManager.couponList,
  modifyHotelModalVisible: state => state.hotelManager.modifyHotelModalVisible,
  activeHotel: state => state.hotelManager.activeHotel,
  orderDetailVisible: state => state.hotelManager.orderDetailVisible,
  activeOrderId: state => state.hotelManager.activeOrderId,
  activeOrder: state => state.hotelManager.activeOrder,

  //marketer
  addWebCouponVisible:state=>state.marketer.addWebCouponVisible,
  webCouponVisible:state=>state.marketer.webCouponVisible,
  webCouponList:state=>state.marketer.webCouponList,
  exceptionalOrderList:state=>state.marketer.exceptionalOrderList,
  // setLevelRulesModalVisible:state=>state.marketer.setLevelRulesModalVisible,
  chargeCreditModalVisible:state=>state.marketer.chargeCreditModalVisible,
}

export default getters