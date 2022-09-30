package tw.com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LoginController {

    @PostMapping("/user/login")
    public String login(@RequestParam("username") String name, @RequestParam("password") String pwd, Map<String, Object> map) {
        if (StringUtils.hasText(name) && "0000".equals(pwd)) {
            //return "dashboard";

            //防止表單重複提交，可以重定向到主頁
            //在配置檔中的webMvcConfigurer添加視圖映射
            return "redirect:/main.html";
        } else {
            map.put("msg", "用戶或密碼錯誤!");
            return "login";
        }
    }
}
