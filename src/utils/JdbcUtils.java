package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

/**
 * Created by lcl on 2017/4/11.
 * 封装常用的操作
 */
public class JdbcUtils {
    //初始化连接池
    private static DataSource dataSource;
    static {
        dataSource=new ComboPooledDataSource();
    }

    /**
     * 返回DataSource
     * @return
     */
    public static DataSource getDataSource(){
        return dataSource;
    }
    /**
     * 返回queryRunner
     * @return
     */
    public static QueryRunner getQueryRunner(){
        return new QueryRunner(dataSource);
    }

}
