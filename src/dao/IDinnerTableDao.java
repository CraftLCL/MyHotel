package dao;

import entity.DinnerTable;

import java.util.List;

/**
 * Created by lcl on 2017/4/14.
 * ²Í×À½Ó¿Ú
 */
public interface IDinnerTableDao {
    void save(DinnerTable dinnerTable);
    void delete(int id);
    void update(DinnerTable dinnerTable);
    List<DinnerTable> getAll();
    List<DinnerTable> getAll(String tableName);
    DinnerTable findById(int id);
    List<DinnerTable> findByNoUse();
    void quitTable(int id);


}
