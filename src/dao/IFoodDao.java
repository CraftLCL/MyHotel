package dao;

import entity.Food;

import java.util.List;

/**
 * Created by lcl on 2017/4/12.
 * ²Ë²Ù×÷½Ó¿Ú
 */
public interface IFoodDao {
    void save(Food food);
    void delete(int id);
    void update(Food food);
    List<Food> findAll();
    List<Food> findAll(String foodName);
    Food findById(int id);
}
