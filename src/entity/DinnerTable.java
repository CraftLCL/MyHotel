package entity;

import java.util.Date;

/**
 * Created by lcl on 2017/4/14.
 * ²Í×ÀÀà
 */
public class DinnerTable {
    private int id;
    private String tableName;

    public int getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(int tableStatus) {
        this.tableStatus = tableStatus;
    }

    // private TableStatus tableStatus;
    private int tableStatus;
    private Date orderDate;
    private String sTabelStatus;

    public String getsTabelStatus() {
        return sTabelStatus;
    }

    public void setsTabelStatus(String sTabelStatus) {
        this.sTabelStatus = sTabelStatus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

   /* public TableStatus getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus;
    }
*/
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
