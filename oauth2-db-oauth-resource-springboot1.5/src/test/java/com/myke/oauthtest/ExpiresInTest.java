package com.myke.oauthtest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author： zhangjianbin <br/>
 * ===============================
 * Created with IDEA.
 * Date： 2018/7/27
 * Time： 16:44
 * ================================
 */
@Slf4j
public class ExpiresInTest {
    @Test
    public void longToDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

        Date date = new Date(System.currentTimeMillis() + 360000L);
        date = new Date(358123L);
        String format = simpleDateFormat.format(date);
        log.info("access_token_validity:{}", format);
    }
}
