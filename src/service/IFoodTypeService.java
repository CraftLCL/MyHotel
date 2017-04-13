package service;

import entity.FoodType;

import java.util.List;

/**
 * Created by lcl on 2017/4/11.
 */
public interface IFoodTypeService {
    /**
     * 添加
     */
    void save(FoodType foodType);
    /**
     * 更新
     */
    void update(FoodType foodType);
    /**
     * 删除
     */
    void  delete(int id);

    /**
     * 通过id查询
     * @return
     */
    FoodType findById(int id);
    /**
     * 查询全部
     */

    List<FoodType> getAll();
    /**
     * 根据菜名查询
     */
    List<FoodType> getAll(String typeName);


}
