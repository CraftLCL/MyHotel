import entity.DinnerTable;
import entity.Food;
import entity.TableStatus;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import service.impl.DinnerTableService;
import utils.JdbcUtils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by lcl on 2017/4/13.
 */
public class Test {
    @org.junit.Test
    public void Test(){
       /* String sql="select * from food ";
        try {
            List<Food> foodList= JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<Food>(Food.class));
            for (Food food:foodList
                 ) {
                System.out.println(food.getId());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
       /* TableStatus tableStatus=TableStatus.Busy;
        System.out.println(tableStatus);*/

        /*DinnerTable dinnerTable=new DinnerTable();
        dinnerTable.setTableName("ÃÀ¹ú");
        dinnerTableService.save(dinnerTable);*/


    }

    public static void main(String[] args) {
  /*      DinnerTableService dinnerTableService=new DinnerTableService();
        List<DinnerTable> dinnerTableList = dinnerTableService.getAll();*/
        System.out.println(new Date());
        System.out.println(TableStatus.Busy.name());
    }
}
