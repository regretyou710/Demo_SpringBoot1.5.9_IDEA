package tw.com.springboot.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tw.com.springboot.exception.UserNotExistException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHamdler {

    //使用SpringMVC異常捕獲機制
    //缺點:瀏覽器及呼叫API(ex:postman)返回都是json格式
    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String, Object> handleException(UserNotExistException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());
        return map;
    }
}
