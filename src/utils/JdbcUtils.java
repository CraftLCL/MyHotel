package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;

/**
 * Created by lcl on 2017/4/11.
 * ��װ���õĲ���
 */
public class JdbcUtils {
    //��ʼ�����ӳ�
    private static DataSource dataSource;
    static {
        dataSource=new ComboPooledDataSource();
    }

    /**
     * ����DataSource
     * @return
     */
    public static DataSource getDataSource(){
        return dataSource;
    }
    /**
     * ����queryRunner
     * @return
     */
    public static QueryRunner getQueryRunner(){
        return new QueryRunner(dataSource);
    }

}
