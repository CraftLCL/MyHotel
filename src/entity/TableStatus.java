package entity;

/**
 * Created by lcl on 2017/4/14.
 */
public enum TableStatus {
    Free{
        public String getDesc(){
            return "¿ÕÏĞ";
        }
    },Busy{
        public String getDesc(){
            return "Ô¤¶¨";
        }
    };
    public static int getNumber(TableStatus tableStatus){
        switch (tableStatus){
            case Busy:
                return 1;

            case Free:
                return 0;

            default:
                return -1;

        }
    }
    public static String getString(TableStatus tableStatus){
        switch (tableStatus){
            case Free:
                return "¿ÕÏĞ";
            case Busy:
                return "Ô¤¶¨";
            default:
                return "Î´Öª";
        }
    }




}
