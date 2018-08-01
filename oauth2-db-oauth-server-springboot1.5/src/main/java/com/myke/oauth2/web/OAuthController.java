package com.myke.oauth2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/oauth")
public class OAuthController {
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private HttpServletRequest request;


    /**
     * 注销 token
     */
    @RequestMapping(value = "/revoke-token", method = RequestMethod.GET)
    public void logout() {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(accessToken);
        }
    }


    /**
     * 根据 client_id 查询 token
     *
     * @param clientId
     * @return
     */
    @GetMapping("/get-client-token")
    public ResponseEntity getClientToken(@RequestParam("client_id") String clientId) {
        Collection<OAuth2AccessToken> tokensByClientId = tokenStore.findTokensByClientId(clientId);
        if (tokensByClientId.size() > 0) {
            return ResponseEntity.ok(tokensByClientId);
        }
        return ResponseEntity.ok(new ArrayList<>());
    }


}