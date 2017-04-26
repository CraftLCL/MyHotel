package service.impl;

import dao.IFoodDao;
import dao.IFoodTypeDao;
import dao.impl.FoodTypeDao;
import entity.Food;
import entity.FoodType;
import factory.BeanFactory;
import service.IFoodService;
import utils.PageBean;

import java.util.List;

/**
 * Created by lcl on 2017/4/12.
 * ²ËµÄ·þÎñ
 */
public class FoodService implements IFoodService {
    private IFoodDao foodDao= BeanFactory.getInstance("foodDao",IFoodDao.class);
    private IFoodTypeDao foodTypeDao= BeanFactory.getInstance("foodTypeDao",IFoodTypeDao.class);
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
         List<Food> foods=    foodDao.findAll();
         List<FoodType> foodTypeList= foodTypeDao.getAll();
            for (Food food:foods
                 ) {
                FoodType foodType=new FoodType();
                foodType.setId(food.getFoodType_id());
                for (FoodType foodType1:foodTypeList
                     ) {
                    if (foodType1.getId()==food.getFoodType_id()){
                        foodType.setTypeName(foodType1.getTypeName());
                    }
                }
                food.setFoodType(foodType);
            }
            return foods;

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
             Food food= foodDao.findById(id);
             FoodType foodType= foodTypeDao.findById(food.getFoodType_id());
             food.setFoodType(foodType);
             return food;
        }catch (Exception e)
        {
            throw new RuntimeException();
        }

    }

    @Override
    public void getAll(PageBean<Food> pb) {
        try {
            foodDao.getAll(pb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Food findByIdTwo(int id) {
        try {
          return   foodDao.findByIdTwo(id);
        } catch (Exception e) {
           throw new RuntimeException();
        }

    }
}
