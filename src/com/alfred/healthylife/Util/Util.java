package com.alfred.healthylife.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class Util {

    public Util() {

    }

    /**
     * MD5加密
     * @param pwd
     * @return
     */
    public final static String md5Parse(String pwd) {
        //用于加密的字符
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
            byte[] btInput = pwd.getBytes();

            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("md5Parse");

            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(btInput);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }

            //返回经过加密后的字符串
            return new String(str);

        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从集合中取出值转为int
     * @param list
     * @param key
     * @return
     */
    public static int getIntFromMapList(ArrayList<HashMap<String,Object>> list, String key) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map = list.get(0);
        Object o = map.get(key);
        String s = String.valueOf(o);
        int i = Integer.parseInt(s);
        return i;
    }

    /**
     * 从集合中取出值转为long
     * @param list
     * @param key
     * @return
     */
    public static long getLongFromMapList(ArrayList<HashMap<String,Object>> list, String key) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map = list.get(0);
        Object o = map.get(key);
        String s = String.valueOf(o);
        System.out.println(s);

        return Long.parseLong(s);
    }

    /**
     * 从集合中取出值转化为boolean
     * @param list
     * @param key
     * @return
     */
    public static boolean getBoolFromMapList(ArrayList<HashMap<String,Object>> list, String key) {
        HashMap<String,Object> map = new HashMap<String,Object>();
        map = list.get(0);
        Object o = map.get(key);
        String s = String.valueOf(o);
        boolean l = Boolean.valueOf(s);
        System.out.println("getBoolFromMapList:" + l);
        return l;
    }

    /**
     * 从集合中取出值转化为String
     * @param list
     * @param key
     * @return
     */
    public static String getStringFromMapList(ArrayList<HashMap<String,Object>> list, String key) {
        HashMap<String,Object> map = list.get(0);
        Object o = map.get(key);
        return String.valueOf(o);
    }

    /**
     * 将请求参数解释为long
     * @param req
     * @param param
     * @return
     */
    public static long getLongFromRequest(HttpServletRequest req, String param) {
        String s = req.getParameter(param);
        if (s == null) {
            return 0;
        }
        return Long.valueOf(req.getParameter(param));
    }

    /**
     * 将请求参数解释为int
     * @param req
     * @param param
     * @return
     */
    public static int getIntFromRequest(HttpServletRequest req, String param) {
        String s = req.getParameter(param);
        if (s == null) {
            return 0;
        }
        return Integer.parseInt(s);
    }

    /**
     * 将集合转为json字符串
     * @param collection 要转换的集合
     * @return 转换后的字符串
     */
    public static String transformFromCollection(Collection<?> collection) {
        if (collection != null) {
            JSONArray ja = new JSONArray(collection);
            return ja.toString();
        } else {
            return printInternalError();
        }
    }

    /**
     * 将session值转为long
     * @param session
     * @param key
     * @return
     */
    public static long transformSessionValueToLong(HttpSession session, String key) {
        Object o = session.getAttribute(key);
        return Long.parseLong(String.valueOf(o));
    }

    /**
     * 将session值转为int
     * @param session
     * @param key
     * @return
     */
    public static Integer transformSessionValueToInteger(HttpSession session, String key) {
        Object o = session.getAttribute(key);
        return Integer.parseInt(String.valueOf(o));
    }

    /**
     * 读取文件
     * @param path 文件路径
     * @return 内容
     */
    public static String readTxtFile(String path) {
        String re = "";
        //得到数据文件
        File file = new File(path);
        StringBuilder builder = new StringBuilder();
        try {
            //建立数据通道
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader reader = new InputStreamReader(fileInputStream, "utf-8"); //最后的"GBK"根据文件属性而定，如果不行，改成"UTF-8"试试
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                builder.append( line + "\n" );
            }
            br.close();
            reader.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
     * 获取图标
     * @param icon_name 文件名
     * @return
     */
    public static String readImage(String icon_name, String path) {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(path + icon_name + ".png");
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    /**
     * 写入文件
     * @param path 文件路径
     * @param content 内容
     */
    public static void writeTxtFile(String path, String content) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 转储图标
     *@param imgStr 图片字符串
     *@param filename 图片名
     *
     */
    public static void writeImage(String imgStr, String filename, String path) {
        if (imgStr != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                byte[] b = decoder.decodeBuffer(imgStr);
                for (int i = 0; i < b.length; ++i) {
                    if (b[i] < 0) {
                        b[i] += 256;
                    }
                }
                String imgFilePath = path + filename + ".png";
                OutputStream out = new FileOutputStream(imgFilePath,false);
                out.write(b);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 获得IP地址
     * @param request
     * @return
     */
    public static String getIPAdress(HttpServletRequest request) {
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

    /**
     * 判断字符串是否含有非法字符
     * @param string
     * @return
     */
    public static boolean isContainIllegalCharCheck(String string) {
        if (string.contains(" ") || string.contains("'") || string.contains("&") || string.contains("|")) {
            return true;
        }
        return false;
    }

    /**
     * 内部错误
     *
     * @return
     */
    private static String printInternalError() {
        JSONObject j = null;
        try {
            j = new JSONObject("{\"status\":\"Interal Error\"}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return j.toString();
    }


}
