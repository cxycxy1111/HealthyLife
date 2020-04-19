package com.alfred.healthylife.Util;

import java.io.IOException;

public class ElasticsearchEntrance {

    /**
     * 匹配查询
     *
     * @param value
     * @param from
     * @return
     * @throws IOException
     */
    public static String matchQuery(String value, int from) throws IOException {
        return ElasticsearchTool.matchQuery(value, from);
    }

    /**
     * 布尔查询
     *
     * @param value
     * @param from
     * @return
     */
    public static String boolQuery(String value, int from) {
        return ElasticsearchTool.boolQuery(value, from);
    }

}