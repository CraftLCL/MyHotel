package dao.impl;

import dao.IFoodDao;
import entity.Food;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.Condition;
import utils.JdbcUtils;
import utils.PageBean;

import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql="select * from food ";

        try {

            return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Food>(Food.class));

        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Food> findAll(String foodName) {
        String sql="select * from food where foodName like ?";
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

    @Override
    public void getAll(PageBean<Food> pb) {
        Condition condition=pb.getCondition();
        int typeId=-1;
        String foodName=null;
        if(condition!=null){
            typeId=condition.getFoodTypeId();
            foodName=  condition.getFoodName();
        }



        String sql="select " +
                "f.id," +
                "f.foodName," +
                "f.price," +
                "f.mprice," +
                "f.remark," +
                "f.img," +
                "f.foodType_id," +
                "t.typeName" +
                " from" +
                " food f," +
                "foodType t " +
                "where 1=1 and f.foodType_id=t.id";

        List<Object> list=new ArrayList<Object>();

        if (typeId>0){
            sql+=" and f.foodType_id=?";
            list.add(typeId);
        }
        if(foodName!=null&&!"".equals(foodName.trim())){
            sql+=" and f.foodName like ?";
            list.add("%"+foodName+"%");
        }

        sql+=" limit ?,?";

        int totalCount=getTotalCount(pb);

        pb.setTotalCount(totalCount);

        if(pb.getCurrentPage()<1){
            pb.setCurrentPage(1);
        }else if (pb.getCurrentPage()>pb.getTotalPage()){
            pb.setCurrentPage(pb.getTotalPage());
        }
        int index=(pb.getCurrentPage()-1)*pb.getPageCount();
        int count=pb.getPageCount();
        list.add(index);
        list.add(count);

        try {
           List<Food> pageDate= JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Food>(Food.class),list.toArray());
            pb.setPageData(pageDate);
        } catch (Exception e) {
            throw new RuntimeException();
        }


    }

    @Override
    public int getTotalCount(PageBean<Food> pb) {
        Condition condition=pb.getCondition();
        int typeId=-1;
        String foodName=null;
        if(condition!=null){
            typeId=condition.getFoodTypeId();
            foodName=condition.getFoodName();
        }



        String sql="select " +
                " count(*)"+
                " from" +
                " food f," +
                "foodType t " +
                "where 1=1 and f.foodType_id=t.id";

        List<Object> list=new ArrayList<Object>();

        if (typeId>0){
            sql+=" and f.foodType_id=?";
            list.add(typeId);
        }
        if(foodName!=null&&!"".equals(foodName.trim())){
            sql+=" and f.foodName like ?";
            list.add(foodName);
        }


        try {
             Long num= JdbcUtils.getQueryRunner().query(sql,new ScalarHandler<Long>(),list.toArray());
             return num.intValue();
        } catch (Exception e) {
            throw new RuntimeException();
        }


    }

    @Override
    public Food findByIdTwo(int id) {
        String sql="select " +
                "f.id" +
                "f.foodName," +
                "f.price," +
                "f.mprice," +
                "f.remark," +
                "f.img," +
                "f.foodType_id," +
                "t.typeName" +
                " from" +
                " food f," +
                "foodType t " +
                "where 1=1 and f.foodType_id=t.id";

        try {
            return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<Food>(Food.class));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
