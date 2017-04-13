package service.impl;

import dao.IFoodDao;
import entity.Food;
import factory.BeanFactory;
import service.IFoodService;

import java.util.List;

/**
 * Created by lcl on 2017/4/12.
 * ²ËµÄ·þÎñ
 */
public class FoodService implements IFoodService {
    private IFoodDao foodDao= BeanFactory.getInstance("foodDao",IFoodDao.class);
    @Override
    public void save(entity.Food food) {
        try {
            foodDao.save(food);
        }catch (Exception e)
        {
            throw new RuntimeException();
        }

    }

    @Override
    public void delete(int id) {
        try {
            foodDao.delete(id);
        }catch (Exception e)
        {
            throw new RuntimeException();
        }

    }

    @Override
    public void update(Food food) {
        try {
            foodDao.update(food);
        }catch (Exception e)
        {
            throw new RuntimeException();
        }

    }

    @Override
    public List<Food> findAll() {
        try {
         return    foodDao.findAll();
        }catch (Exception e)
        {
            throw new RuntimeException();
        }


    }

    @Override
    public List<Food> findAll(String foodName) {
        try {
          return   foodDao.findAll(foodName);
        }catch (Exception e)
        {
            throw new RuntimeException();
        }


    }

    @Override
    public Food findById(int id) {
        try {
          return   foodDao.findById(id);
        }catch (Exception e)
        {
            throw new RuntimeException();
        }

    }
}
