package service.impl;

import dao.IFoodTypeDao;
import dao.impl.FoodTypeDao;
import entity.FoodType;
import factory.BeanFactory;
import service.IFoodTypeService;

import java.util.List;

/**
 * Created by lcl on 2017/4/11.
 * 菜品业务逻辑层接口实现
 */
public class FoodTypeService implements IFoodTypeService{
   //private IFoodTypeDao foodTypeDao=new FoodTypeDao();
   private IFoodTypeDao foodTypeDao= BeanFactory.getInstance("foodTypeDao",IFoodTypeDao.class);

    @Override
    public void save(FoodType foodType) {
        try {
            foodTypeDao.save(foodType);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void update(FoodType foodType) {
        try {
            foodTypeDao.update(foodType);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        try {
            foodTypeDao.delete(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public FoodType findById(int id) {
        try {
          return   foodTypeDao.findById(id);
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<FoodType> getAll() {
        try {
          return   foodTypeDao.getAll();
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<FoodType> getAll(String typeName) {
        try {
          return   foodTypeDao.getAll(typeName);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
