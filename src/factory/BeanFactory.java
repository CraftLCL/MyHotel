package factory;

import java.util.ResourceBundle;

/**
 * Created by lcl on 2017/4/11.
 * ����������dao��serviceʵ��
 */
public class BeanFactory {
    private static ResourceBundle bundle;
    static {
        bundle=ResourceBundle.getBundle("instance");
    }

    /**
     * ����ָ����key����ȡ�����ļ�
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
