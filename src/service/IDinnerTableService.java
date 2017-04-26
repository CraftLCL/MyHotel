package service;

import entity.DinnerTable;

import java.util.List;

/**
 * Created by lcl on 2017/4/14.
 */
public interface IDinnerTableService {
    void save(DinnerTable dinnerTable);
    void delete(int id);
    void update(DinnerTable dinnerTable);
    List<DinnerTable> getAll();
    List<DinnerTable> getAll(String tableName);
    DinnerTable findById(int id);
    List<DinnerTable> findByNoUse();
    void quitTable(int id);

}
