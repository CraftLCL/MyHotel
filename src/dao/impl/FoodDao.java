package dao.impl;

import dao.IFoodDao;
import entity.Food;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lcl on 2017/4/12.
 */
public class FoodDao implements IFoodDao {
    @Override
    public void save(Food food) {
        String sql="INSERT INTO food(foodName, foodType_id, price, mprice, remark, img) VALUES (?,?,?,?,?,?);";
        try {
            JdbcUtils.getQueryRunner().update(sql,
                    food.getFoodName(),
                    food.getFoodType_id(),
                    food.getPrice(),
                    food.getMprice(),
                    food.getRemark(),
                    food.getImg());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        String sql="delete from food where id= ?";
        try {
            JdbcUtils.getQueryRunner().update(sql,id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Food food) {
        String sql="update food set foodName=?," +
                "foodType_id=?," +
                "price=?," +
                "mprice=?," +
                "remark=?," +
                "img=? where id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql,food.getFoodName(),
                    food.getFoodType_id(),
                    food.getPrice(),
                    food.getMprice(),
                    food.getRemark(),
                    food.getImg(),
                    food.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Food> findAll() {
        String sql="select * from food";

        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Food>(Food.class));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Food> findAll(String foodName) {
        String sql="select * from food where foodName like";
        try {
          return   JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Food>(Food.class),
                    "%"+foodName+"%");
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public Food findById(int id) {
        String sql="select * from food where id=?";
        try {
            return  JdbcUtils.getQueryRunner().query(sql,new BeanHandler<Food>(Food.class),id);
        } catch (SQLException e) {
           throw  new RuntimeException();
        }

    }
}
