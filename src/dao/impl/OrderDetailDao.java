package dao.impl;

import dao.IOrderDetailDao;
import entity.OrderDetail;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.util.List;

/**
 * Created by lcl on 2017/4/19.
 */
public class OrderDetailDao implements IOrderDetailDao {
    private QueryRunner qr = JdbcUtils.getQueryRunner();

    @Override
    public void add(OrderDetail od) {
        String sql =" INSERT orderdetail(orderId,food_id,foodCount) VALUES(?,?,?)";
        try {
            qr.update(sql,od.getOrderId(),od.getFood_id(),od.getFoodCount());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<OrderDetail> query() {
        try {
            String sql ="SELECT * FROM orderdetail";
            return  qr.query(sql,new BeanListHandler<OrderDetail>(OrderDetail.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<OrderDetail> findByOrderid(int id) {
        try {
            String sql ="SELECT * FROM orderdetail where orderId=?";
            return  qr.query(sql,new BeanListHandler<OrderDetail>(OrderDetail.class),id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
