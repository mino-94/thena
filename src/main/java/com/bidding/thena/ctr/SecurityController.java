package com.bidding.thena.ctr;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.bidding.thena.values.Values.DEFAULT_PATH;

@RestController
@RequestMapping(DEFAULT_PATH)
public class SecurityController {


    @Value("${spring.security.conf.loginPath}")
    private String loginPath;


    @RequestMapping("/expired")
    public Map expireSession(HttpServletResponse response) {
        Map result = new HashMap<>();
        result.put("msg", "Session Expired");
        result.put("redirect", loginPath);

        response.setStatus(HttpStatus.REQUEST_TIMEOUT.value());

        return result;
    }

}
