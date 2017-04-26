package service.impl;

import dao.IDinnerTableDao;
import entity.DinnerTable;
import entity.TableStatus;
import factory.BeanFactory;
import service.IDinnerTableService;

import java.util.Date;
import java.util.List;

/**
 * Created by lcl on 2017/4/14.
 */
public class DinnerTableService implements IDinnerTableService {
    private IDinnerTableDao dinnerTableDao= BeanFactory.getInstance("dinnerTableDao",IDinnerTableDao.class);

    @Override
    public void save(DinnerTable dinnerTable) {
        try {
            dinnerTable.setTableStatus(0);
            dinnerTableDao.save(dinnerTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            dinnerTableDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(DinnerTable dinnerTable) {

        try {
            dinnerTable.setOrderDate(new Date());
            if(dinnerTable.getTableStatus()==0){
                dinnerTable.setTableStatus(1);
            }else {
                dinnerTable.setOrderDate(null);
                dinnerTable.setTableStatus(0);
            }
            dinnerTableDao.update(dinnerTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DinnerTable> getAll() {
        try {
            List<DinnerTable> dinnerTableList = dinnerTableDao.getAll();
            setStatus(dinnerTableList);
            return dinnerTableList;
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    private void setStatus(List<DinnerTable> dinnerTableList) {
        for (DinnerTable dinnerTable:dinnerTableList
             ) {
            if(dinnerTable.getTableStatus()==0){
                dinnerTable.setsTabelStatus("ø’œ–");
            }else {
                dinnerTable.setsTabelStatus("‘§∂®");
            }
        }
    }

    @Override
    public List<DinnerTable> getAll(String tableName) {
        try {
            List<DinnerTable> dinnerTableList = dinnerTableDao.getAll(tableName);
            setStatus(dinnerTableList);
            return dinnerTableList;

        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public DinnerTable findById(int id) {
        try {
            return dinnerTableDao.findById(id);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public List<DinnerTable> findByNoUse() {
        try {
            return dinnerTableDao.findByNoUse();
        } catch (Exception e) {
            throw new RuntimeException();
        }


    }

    @Override
    public void quitTable(int id) {
        dinnerTableDao.quitTable(id);

    }
}
