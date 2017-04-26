package service;

import entity.Orders;
import utils.PageBean;

import java.util.List;

/**
 * Created by lcl on 2017/4/19.
 */
public interface IOrdersService {
    void update(Orders orders);

    List<Orders> query();

    void add(Orders orders);

    int getCount();

    public void getAll(PageBean<Orders> pb);

}
