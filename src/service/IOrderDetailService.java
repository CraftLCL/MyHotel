package service;

import entity.OrderDetail;

import java.util.List;

/**
 * Created by lcl on 2017/4/19.
 */
public interface IOrderDetailService {
    void add(OrderDetail od);

    List<OrderDetail> query();

    List<OrderDetail> findByOrderId(int id);

}
