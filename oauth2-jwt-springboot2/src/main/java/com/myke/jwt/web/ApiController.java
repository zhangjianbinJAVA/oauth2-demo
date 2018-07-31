package com.myke.jwt.web;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * 暴露一个商品查询接口
 */
@RestController
@Slf4j
public class ApiController {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        String header = request.getHeader("Authorization");
        String token = StringUtils.substringAfter(header, "Bearer ");
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey("merryyou".getBytes("UTF-8")).parseClaimsJws(token).getBody();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String blog = (String) claims.get("blog");

        log.info("blog:{}", blog);
        return "order id : " + id;
    }

}