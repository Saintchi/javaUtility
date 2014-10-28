package com.pasier.goldroom.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉 模拟http请求的工具类
 * 
 * @author
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class HttpUtil {

    private static final String urlPath = ConfigCache.getConfig("salPath");
    // ConfigCache.getConfig("acsUrlPath")
    // http://10.22.7.73:8080/cloudconsole-web/
    private static final String acsUrlPath = ConfigCache.getConfig("acsUrlPath");

    public static String doGet(String params) {
        // String path = "http://cloudsit.cnsuning.com:8080/sal-api/autocs/" + params;
        String path = urlPath + params;
        StringBuffer sb = new StringBuffer();
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            int code = con.getResponseCode();

            if (code == 200) {
                InputStream in = con.getInputStream();
                byte[] b = new byte[1024];
                int len = 0;
                while ((len = in.read(b)) != -1) {
                    String s = new String(b, 0, len, "UTF-8");
                    sb.append(new StringBuffer(s));
                }
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String doPost(String param1, String params) {
        // String path = "http://cloudsit.cnsuning.com:8080/sal-api/autocs/" + param1;
        String path = urlPath + param1;
        StringBuffer sb = new StringBuffer();
        URL url = null;
        ;
        try {
            url = new URL(path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setDoInput(true);
            con.setDoOutput(true);
            OutputStream out = con.getOutputStream();
            out.write(params.getBytes("UTF-8"));
            int code = con.getResponseCode();
            if (code == 200) {
                InputStream in = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String ss = null;
                while ((ss = br.readLine()) != null) {
                    sb.append(ss);
                }
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 访问autoCS请求获取邮件信息页面<br>
     * 〈功能详细描述〉 模拟http请求的工具类
     * 
     * @param xxx/xx.htm
     * @author
     * @see [相关类/方法]（可选）
     * @since [产品/模块版本] （可选）
     */
    public static String doAcsGet(String params) {
        String path = acsUrlPath + params;
        URL url = null;
        String result = "";
        try {
            url = new URL(path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setDoInput(true);
            con.setDoOutput(true);
            int code = con.getResponseCode();

            if (code == 200) {
                InputStream in = con.getInputStream();
                result = IOUtils.toString(in, "UTF-8");
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 访问autoCS请求获取邮件信息页面<br>
     * 〈功能详细描述〉 模拟http请求的工具类
     * 
     * @param param1 xxx/xx.htm
     * @param params "data1=" + data1 + "&data2=" + data2
     * @author
     * @see [相关类/方法]（可选）
     * @since [产品/模块版本] （可选）
     */
    public static String doAcsPost(String param1, String params) {
        String path = acsUrlPath + param1;
        StringBuffer sb = new StringBuffer();
        URL url = null;
        ;
        try {
            url = new URL(path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setDoInput(true);
            con.setDoOutput(true);
            OutputStream out = con.getOutputStream();
            out.write(params.getBytes("UTF-8"));
            int code = con.getResponseCode();
            if (code == 200) {
                InputStream in = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                String ss = null;
                while ((ss = br.readLine()) != null) {
                    sb.append(ss);
                }
            }
            con.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
