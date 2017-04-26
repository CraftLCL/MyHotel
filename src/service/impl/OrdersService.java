package service.impl;

import dao.IOrdersDao;
import entity.Orders;
import factory.BeanFactory;
import service.IOrdersService;
import utils.PageBean;

import java.util.List;

/**
 * Created by lcl on 2017/4/19.
 */
public class OrdersService implements IOrdersService {
    IOrdersDao dao = BeanFactory.getInstance("ordersDao", IOrdersDao.class);

    @Override
    public void update(Orders orders) {
        dao.update(orders);

    }

    @Override
    public List<Orders> query() {
        return dao.query();

    }

    @Override
    public void add(Orders orders) {
        dao.add(orders);
    }

    @Override
    public int getCount() {
        return dao.getCount();
    }

    @Override
    public void getAll(PageBean<Orders> pb) {
        try {
            dao.getAll(pb);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
