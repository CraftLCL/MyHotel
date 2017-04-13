package dao;

import entity.FoodType;

import java.util.List;

/**
 * Created by lcl on 2017/4/11.
 * ��ϵģ�飬�ӿ����
 */
public interface IFoodTypeDao {
    /**
     * ���
     */
    void save(FoodType foodType);
    /**
     * ����
     */
    void update(FoodType foodType);
    /**
     * ɾ��
     */
    void  delete(int id);

    /**
     * ͨ��id��ѯ
     * @return
     */
    FoodType findById(int id);
    /**
     * ��ѯȫ��
     */

    List<FoodType> getAll();
    /**
     * ���ݲ�����ѯ
     */
    List<FoodType> getAll(String typeName);

}
