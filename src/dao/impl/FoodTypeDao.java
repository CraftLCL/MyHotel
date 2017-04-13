package dao.impl;

import dao.IFoodTypeDao;
import entity.FoodType;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcl on 2017/4/11.
 * ²ËÏµµÄ²Ù×÷
 */
public class FoodTypeDao implements IFoodTypeDao {

    @Override
    public void save(FoodType foodType) {
        String sql="INSERT INTO foodType(typeName) VALUES (?);";
        try{
            JdbcUtils.getQueryRunner().update(sql,foodType.getTypeName());

        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @Override
    public void update(FoodType foodType) {
        String sql="update foodType set typeName = ? where id= ?";
        try{
            JdbcUtils.getQueryRunner().update(sql,foodType.getTypeName(),foodType.getId());


        }catch (Exception e){
            throw new RuntimeException();
        }

    }

    @Override
    public void delete(int id) {
        String sql="delete from foodType where id=?";
        try{
            JdbcUtils.getQueryRunner().update(sql,id);

        }catch (Exception e){
            throw new RuntimeException();
        }

    }

    @Override
    public FoodType findById(int id) {
        FoodType foodType=null;
        String sql="select * from foodType where id=?";
        try{

            foodType = JdbcUtils.getQueryRunner().query(sql,new BeanHandler<FoodType>(FoodType.class),id);

        }catch (Exception e){
            throw new RuntimeException();
        }

        return foodType;
    }

    @Override
    public List<FoodType> getAll() {
        List<FoodType> foodTypeList=new ArrayList<>();
        String sql="select * from foodType";
        try{
           return foodTypeList= JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<FoodType>(FoodType.class));

        }catch (Exception e){
            throw new RuntimeException();
        }
/*        FoodType foodType=new FoodType();
        foodType.setId(1);
        foodType.setTypeName("123");
        foodTypeList.add(foodType);*/

    }

    @Override
    public List<FoodType> getAll(String typeName) {
        List<FoodType> foodTypeList=null;
        String sql="select * from foodType where typeName like ?";
        try{
            foodTypeList= JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<FoodType>(FoodType.class),"%"+typeName+"%");

        }catch (Exception e){
            throw new RuntimeException();
        }

        return foodTypeList;
    }
}
