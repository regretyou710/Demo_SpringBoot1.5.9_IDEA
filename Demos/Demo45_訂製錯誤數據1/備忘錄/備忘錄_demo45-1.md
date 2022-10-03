## 如何定制錯誤的json數據；

​		1）、自定義異常處理&返回定制json數據；

```java
@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(UserNotExistException.class)
    public Map<String,Object> handleException(Exception e){
        Map<String,Object> map = new HashMap<>();
        map.put("code","user.notexist");
        map.put("message",e.getMessage());
        return map;
    }
}
//沒有自適應效果...
```

在新版SpringBoot中，使用模板無法獲取請求域中的exception、message，因為:  
BasicErrorController類建構式中的形參ErrorProperties的類裡面，
- includeException默認false
- includeMessage默認IncludeAttribute.NEVER

因此在資源配置文件進行配置:
- server.error.include-exception=true
- server.error.include-message=ALWAYS
