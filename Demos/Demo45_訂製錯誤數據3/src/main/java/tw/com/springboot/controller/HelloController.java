package tw.com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tw.com.springboot.exception.UserNotExistException;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(@RequestParam("user") String user) {
        if (user.equals("aaa"))
            throw new UserNotExistException();

        return "hello world";
    }

    //查出用戶數據，在頁面展示
    @RequestMapping("/success")
    public String success(Map<String, Object> map) {
        map.put("hello", "<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
        return "success";
    }

//    @RequestMapping(value = {"/", "/index.html"})
//    public String index() {
//        return "index";
//    }
}
