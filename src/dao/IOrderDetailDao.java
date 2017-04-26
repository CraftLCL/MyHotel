package dao;

import entity.OrderDetail;

import java.util.List;

/**
 * Created by lcl on 2017/4/19.
 */
public interface IOrderDetailDao {
    void add(OrderDetail od);

    List<OrderDetail> query();

    List<OrderDetail> findByOrderid(int id);

}
