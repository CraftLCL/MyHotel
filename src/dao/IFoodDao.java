package dao;

import entity.Food;
import utils.PageBean;

import java.util.List;

/**
 * Created by lcl on 2017/4/12.
 * 菜操作接口
 */
public interface IFoodDao {
    void save(Food food);
    void delete(int id);
    void update(Food food);
    List<Food> findAll();
    List<Food> findAll(String foodName);
    Food findById(int id);

    //分页并且按条件查询
   void getAll(PageBean<Food> pb);
    //按条件统计菜品总数目
    int getTotalCount(PageBean<Food> pd);

    Food findByIdTwo(int id);




}
