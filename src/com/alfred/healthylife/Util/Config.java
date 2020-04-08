package com.alfred.healthylife.Util;

import java.util.ArrayList;

public class Config {

    public static final int ALPHA_NOVEL = 1;
    public static final int EN_BOOK = 2;

    private static final String remotePassword = "CXYcxy11";
    private static final String localPassword = "cxycxy11";

    public Config () {

    }

    private static int getCurrentSite() {
        return ALPHA_NOVEL;
    }

    /**
     * 是否测试
     * @return
     */
    private static boolean isTest() {
        return true;
    }

    /**
     * 获取小说储存路径
     * @return
     */
    public static String getPath() {
        if (getCurrentSite() == ALPHA_NOVEL) {
            return "/usr/local/Novel/";
        }else{
            return "/usr/local/EnglishNovel/";
        }
    }

    static String getDatabaseName() {
        if (getCurrentSite() == ALPHA_NOVEL) {
            return "AlphaNovel";
        }else {
            return "EnBook";
        }
    }

    static String getDatabasePassword() {
        if (isTest()) {
            return localPassword;
        }else {
            return remotePassword;
        }
    }

    public static ArrayList<String> getCategory() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (getCurrentSite() == ALPHA_NOVEL) {
            arrayList.add("中国文学");
            arrayList.add("外国文学");
            arrayList.add("古籍精选");
            arrayList.add("专业书籍");
            arrayList.add("网文精选");
        }else {
            arrayList.add("Fiction");
        }
        return arrayList;
    }

}
