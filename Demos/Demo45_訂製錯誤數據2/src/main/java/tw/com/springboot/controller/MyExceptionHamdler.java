package tw.com.springboot.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import tw.com.springboot.exception.UserNotExistException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHamdler {

    //使用SpringMVC異常捕獲機制
    //缺點:瀏覽器及呼叫API(ex:postman)返回都是json格式
//    @ResponseBody
//    @ExceptionHandler(UserNotExistException.class)
//    public Map<String, Object> handleException(UserNotExistException e) {
//        Map<String, Object> map = new HashMap<>();
//        map.put("code", "user.notexist");
//        map.put("message", e.getMessage());
//        return map;
//    }

    //透過BasicErrorController的註解映射，自動適應是否來自瀏覽器的請求
    @ExceptionHandler({UserNotExistException.class})
    public String handleException(UserNotExistException e, HttpServletRequest request) {
        //傳入我們自己的錯誤狀態碼  4xx 5xx，否則就不會進入定制錯誤頁面的解析流程(There was an unexpected error (type=OK, status=200))
        //
        /**
         * Integer statusCode = (Integer) request
         .getAttribute("javax.servlet.error.status_code");
         */

        request.setAttribute("javax.servlet.error.status_code", 500);
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());

        //轉發到/error
        return "forward:/error";
    }
}
