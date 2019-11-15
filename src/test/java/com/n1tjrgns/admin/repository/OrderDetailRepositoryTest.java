package com.n1tjrgns.admin.repository;

import com.n1tjrgns.admin.AdminApplicationTests;
import com.n1tjrgns.admin.model.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends AdminApplicationTests {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setCreatedAt(LocalDateTime.now());

        //어떤사람?
        orderDetail.setUserId(3L);

        //어떤 상품?
        orderDetail.setItemId(2L);

        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(newOrderDetail);
    }
}
