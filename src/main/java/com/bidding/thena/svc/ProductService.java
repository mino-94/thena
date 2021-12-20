package com.bidding.thena.svc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProductService {

    @Value("${spring.api.server}")
    private String apiServer;

    @Value("${spring.image.server}")
    private String imgServer;


    public List getBanners() {
        List banners = new ArrayList<>();
        Map banner = new HashMap<>();
        banner.put("url", "/detail/admin");
        banner.put("img", imgServer + "/ftp/img/sample.jpg");
        banners.add(banner);
        banner = new HashMap<>();
        banner.put("url", "/detail/admin");
        banner.put("img", imgServer + "/ftp/img/sample.jpg");
        banners.add(banner);

        return banners;
    }

    public Map getWeekTop(String type) throws Exception {
        Map result = new HashMap<>();
        List tops = new LinkedList();

        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String today = format.format(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(today));

        result.put("week", today.substring(0, today.lastIndexOf(".")) + " " + calendar.get(Calendar.WEEK_OF_MONTH));

        if ("AD".equals(type)) {
            Map data = null;
            for (int i = 0; i < 10; i ++) {
                data = new HashMap<>();
                data.put("title", "광고" + (i + 1));
                data.put("corp", "회사" + (i + 1));
                tops.add(data);
            }
            result.put("tops", tops);
        } else if ("MD".equals(type)) {
            Map data = null;
            for (int i = 0; i < 10; i ++) {
                data = new HashMap<>();
                data.put("title", "매체" + (i + 1));
                data.put("corp", "회사" + (i +  1));
                tops.add(data);
            }
            result.put("tops", tops);
        }
        return result;
    }

}
