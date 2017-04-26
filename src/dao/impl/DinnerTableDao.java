package dao.impl;

import dao.IDinnerTableDao;
import entity.DinnerTable;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by lcl on 2017/4/14.
 */
public class DinnerTableDao implements IDinnerTableDao {
    @Override
    public void save(DinnerTable dinnerTable) {
        String sql = "insert into dinnerTable (tableName,tableStatus)" +
                "values (?,?)";
        try {
            JdbcUtils.getQueryRunner().update(sql, dinnerTable.getTableName(), dinnerTable.getTableStatus()
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        String sql = "delete from dinnerTable where id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(DinnerTable dinnerTable) {
        String sql = "update dinnerTable set tableName=?,tableStatus=?,orderDate=? where id=?";
        try {
            JdbcUtils.getQueryRunner().update(sql, dinnerTable.getTableName(),
                    dinnerTable.getTableStatus(),
                    dinnerTable.getOrderDate(),
                    dinnerTable.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DinnerTable> getAll() {
        String sql = "select * from dinnerTable";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public List<DinnerTable> getAll(String tableName) {
        String sql = "select * from dinnerTable where tableName like ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class), "%" + tableName + "%");
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public DinnerTable findById(int id) {
        String sql = "select * from dinnerTable where id = ?";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<DinnerTable>(DinnerTable.class), id);
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public List<DinnerTable> findByNoUse() {

        String sql="select * from dinnerTable where tableStatus=0";
        try {
            return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class));
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }

    @Override
    public void quitTable(int id) {
        String sql = "UPDATE dinnertable SET tableStatus=?,orderDate=? WHERE id=?";

        try {
            JdbcUtils.getQueryRunner().update(sql,0,null,id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
