## 如何定制錯誤的json數據；

		2）、轉發到/error進行自適應響應效果處理

```java
 @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        //傳入我們自己的錯誤狀態碼  4xx 5xx，否則就不會進入定制錯誤頁面的解析流程
        /**
         * Integer statusCode = (Integer) request
         .getAttribute("javax.servlet.error.status_code");
         */
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code","user.notexist");
        map.put("message",e.getMessage());
        //轉發到/error
        return "forward:/error";
    }
```