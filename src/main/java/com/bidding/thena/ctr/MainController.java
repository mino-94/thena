package com.bidding.thena.ctr;

import com.bidding.thena.svc.ProductService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bidding.thena.values.Values.DEFAULT_PATH;

@RestController
@RequestMapping(DEFAULT_PATH)
public class MainController {

    @Autowired
    private ProductService productService;

    @Value("${spring.security.conf.loginPath}")
    private String loginPath;


    @RequestMapping("/")
    public Map getMain() {
        Map result = new HashMap<>();
        result.put("banners", productService.getBanners());

        return result;
    }

    @RequestMapping("/login")
    public Map login() {
        Map result = new HashMap<>();
        result.put("redirect", loginPath);

        return result;
    }

    @RequestMapping("/join")
    public Map join() {
        return null;
    }

    @PostMapping("/join/info")
    public Map joinInfo() {
        return null;
    }

    @RequestMapping("/guide")
    public Map guide() {
        return null;
    }

    @PostMapping("/week/top")
    public Map getAdWeekTop(HttpServletRequest request) throws Exception {
        Map result = new HashMap<>();
        String type = StringUtils.defaultIfEmpty(request.getParameter("type"), "");

        if ("AD".equals(type) || "MD".equals(type)) {
            result = productService.getWeekTop(type);
        }
        return result;
    }

    @PostMapping("/detail/admin")
    public Map admin() {
        Map result = new HashMap<>();
        result.put("test", 1234);

        return result;
    }

}
