package mani.itachi.calender.helpers;

public class Constants {

    public static final String DAY_CODE="DAY_CODE";

    public static int getNowSeconds(){
        return (int)(System.currentTimeMillis() / 1000);
    }

    public static boolean isNotEmpty(String s){
        if(s==null || s.equalsIgnoreCase("")) return false;
        return true;
    }

}
