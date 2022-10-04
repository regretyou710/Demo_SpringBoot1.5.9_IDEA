## 將我們的定制數據攜帶出去；

出現錯誤以後，會來到/error請求，會被BasicErrorController處理，響應出去可以獲取的數據是由getErrorAttributes得到的（是AbstractErrorController（ErrorController）規定的方法）；  
```
因為SpringBoot沒有將
map.put("code", "user.notexist");
map.put("message", e.getMessage());
攜帶出去，表示ErrorMvcAutoConfiguration類中的BasicErrorController的@ConditionalOnMissingBean(value = ErrorController.class, search = SearchStrategy.CURRENT)生效
=>IOC容器中沒有ErrorController.class而使用BasicErrorController默認的getErrorAttributes方法攜帶出去
所以不使用默認方式則可以用下列:
```  
​	1、完全來編寫一個ErrorController的實現類【或者是編寫AbstractErrorController的子類】，放在容器中；

​	2、頁面上能用的數據，或者是json返回能用的數據都是通過errorAttributes.getErrorAttributes得到；

​			容器中DefaultErrorAttributes.getErrorAttributes()；默認進行數據處理的；

自定義ErrorAttributes

```java
//給容器中加入我們自己定義的ErrorAttributes
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
        Map<String, Object> map = super.getErrorAttributes(requestAttributes, includeStackTrace);
        map.put("company","atguigu");
        return map;
    }
}
```

最終的效果：響應是自適應的，可以通過定制ErrorAttributes改變需要返回的內容，

![](images/搜狗截图20180228135513.png)