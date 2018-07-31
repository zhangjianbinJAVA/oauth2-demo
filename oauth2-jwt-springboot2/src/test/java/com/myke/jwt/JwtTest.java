package com.myke.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * @author： zhangjianbin <br/>
 * ===============================
 * Created with IDEA.
 * Date： 2018/7/30
 * Time： 17:00
 * ================================
 */
public class JwtTest {
    @Test
    public void jwtBody() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhbGwiXSwiZXhwIjoxNTMzMzAwNDI5LCJibG9nIjoiaHR0cHM6Ly9sb25nZmVpemhlbmcuZ2l0aHViLmlvLyIsImF1dGhvcml0aWVzIjpbIndyaXRlIiwicmVhZCJdLCJqdGkiOiI4MzY2NmI3Yy04MmFiLTRjZTMtOTZlNi02YjBhZTJiZjZiODgiLCJjbGllbnRfaWQiOiJ3ZWIifQ.gBLTG61PLIAxvEKp9I97uMS9nOyLw8orVZOQ0FCd0GY";
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey("merryyou".getBytes("UTF-8")).parseClaimsJws(token).getBody();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String blog = (String) claims.get("blog");
        System.out.println(blog);
    }
}
