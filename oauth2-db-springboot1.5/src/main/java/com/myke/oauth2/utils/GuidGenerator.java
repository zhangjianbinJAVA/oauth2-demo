package com.myke.oauth2.utils;

import java.util.UUID;

/**
 * @author： zhangjianbin <br/>
 * ===============================
 * Created with IDEA.
 * Date： 2018/7/31
 * Time： 18:38
 * ================================
 */
public class GuidGenerator {

    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
