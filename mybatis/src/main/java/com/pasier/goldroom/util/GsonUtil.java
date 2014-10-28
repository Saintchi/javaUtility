package com.pasier.goldroom.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.suning.spc.dto.JsonAddress;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author 12072521
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class GsonUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(GsonUtil.class);

    /**
     * Gson实例，日期类型的格式化pattern为yyyy-MM-dd HH:mm:ss
     */
    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping()
            .create();// 关闭特殊字符转义

    public static String object2JsonStr(Object obj) {
        return GSON.toJson(obj);
    }

    public static List<String> jsonStr2ListStr(String jsonString) {
        return GSON.fromJson(jsonString, new TypeToken<List<String>>() {
        }.getType());
    }

    public static JsonAddress jsonStr2Address(String jsonString) {
        return GSON.fromJson(jsonString, new TypeToken<JsonAddress>() {
        }.getType());
    }

    /**
     * 功能描述: 判断字符串是否是有效json对象<br>
     * 〈功能详细描述〉
     * 
     * @param json
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static boolean isGoodJson(String json) {
        if (StringUtils.isBlank(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            LOGGER.error("bad json: " + json);
            return false;
        }
    }

    // public static void main(String[] args) {
    // List<String> arr = new ArrayList<String>();
    // arr.add("283");
    // arr.add("285");
    // String jsonRet = object2JsonStr(arr);
    // System.out.println(" jsonRet = " + jsonRet);
    // }

}
