### 登入

開發期間模板引擎頁面修改以後，要實時生效

1）、禁用模板引擎的緩存

```
# 禁用緩存
spring.thymeleaf.cache=false 
```

2）、頁面修改完成以後ctrl+f9：重新編譯；

- 登入錯誤消息的顯示
    ```html
    <p style="color: red" th:text="${msg}" th:if="${not #strings.isEmpty(msg)}"></p>
    ```
 - 造成表單重複提交的因素  
    當表單成功提交後，在當前頁面重新刷新時還是提交上一次的請求(路徑)  
    為了防止表單重複提交，可透過重定向(redirect)方式解決
