package factory;

import java.util.ResourceBundle;

/**
 * Created by lcl on 2017/4/11.
 * 工厂：创建dao或service实例
 */
public class BeanFactory {
    private static ResourceBundle bundle;
    static {
        bundle=ResourceBundle.getBundle("instance");
    }

    /**
     * 根据指定的key，读取配置文件
     * @return
     */
    public static <T> T getInstance(String key,Class<T> tClass){

        String className=bundle.getString(key);
        try {
           return  (T)Class.forName(className).getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }

    }
}
