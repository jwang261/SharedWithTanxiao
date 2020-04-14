package com.jwang261.gourmet.service.impl;

import com.jwang261.gourmet.dao.OrderDetailDao;
import com.jwang261.gourmet.pojo.entity.OrderDetail;
import com.jwang261.gourmet.pojo.entity.Record;
import com.jwang261.gourmet.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    OrderDetailDao orderDetailDao;

    @Override
    public void addOrderDetail(OrderDetail orderDetail) {
        orderDetailDao.insert(orderDetail);
    }

    @Override
    public List<OrderDetail> getOrderDetailByRecordId(int recordId) {

        return new ArrayList<>( orderDetailDao.selectByRecordId(recordId));
    }

    @Override
    public int getRecordIdById(int orderDetailId) {
        return orderDetailDao.selectRecordIdById(orderDetailId);
    }
}
