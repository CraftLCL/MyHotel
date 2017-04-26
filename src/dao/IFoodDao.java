package dao;

import entity.Food;
import utils.PageBean;

import java.util.List;

/**
 * Created by lcl on 2017/4/12.
 * �˲����ӿ�
 */
public interface IFoodDao {
    void save(Food food);
    void delete(int id);
    void update(Food food);
    List<Food> findAll();
    List<Food> findAll(String foodName);
    Food findById(int id);

    //��ҳ���Ұ�������ѯ
   void getAll(PageBean<Food> pb);
    //������ͳ�Ʋ�Ʒ����Ŀ
    int getTotalCount(PageBean<Food> pd);

    Food findByIdTwo(int id);




}
