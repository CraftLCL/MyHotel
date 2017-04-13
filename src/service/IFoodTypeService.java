package service;

import entity.FoodType;

import java.util.List;

/**
 * Created by lcl on 2017/4/11.
 */
public interface IFoodTypeService {
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
