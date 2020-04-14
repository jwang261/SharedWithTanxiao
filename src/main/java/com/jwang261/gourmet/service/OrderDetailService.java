package com.jwang261.gourmet.service;

import com.jwang261.gourmet.pojo.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    void addOrderDetail(OrderDetail orderDetail);

    List<OrderDetail> getOrderDetailByRecordId(int recordId);

    int getRecordIdById(int orderDetailId);
}
