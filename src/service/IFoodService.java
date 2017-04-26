package service;

import entity.Food;
import utils.PageBean;

import java.util.List;

/**
 * Created by lcl on 2017/4/12.
 * ²ËµÄ·þÎñ
 */
public interface IFoodService {
    void save(Food food);
    void delete(int id);
    void update(Food food);
    List<Food> findAll();
    List<Food> findAll(String foodName);
    Food findById(int id);


    void getAll(PageBean<Food> pb);
    Food findByIdTwo(int id);


}
