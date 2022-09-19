package tw.com.springboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${person.last-name}")//表示從appliction.properties資源文件中的person.last-name鍵獲取它的值並注入
    private String name;

    @RequestMapping("/sayHello")
    public String sayHello() {
        return name + " hello!";
    }
}
