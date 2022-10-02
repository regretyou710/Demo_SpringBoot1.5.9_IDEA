### CRUD-員工列表

實驗要求：

1）、RestfulCRUD：CRUD滿足Rest風格；

URI：  /資源名稱/資源標識       HTTP請求方式區分對資源CRUD操作

|      | 普通CRUD（uri來區分操作） | RestfulCRUD       |
| ---- | ------------------------- | ----------------- |
| 查詢 | getEmp                    | emp---GET         |
| 添加 | addEmp?xxx                | emp---POST        |
| 修改 | updateEmp?id=xxx&xxx=xx   | emp/{id}---PUT    |
| 刪除 | deleteEmp?id=1            | emp/{id}---DELETE |

2）、實驗的請求架構;

| 實驗功能                             | 請求URI | 請求方式 |
| ------------------------------------ | ------- | -------- |
| 查詢所有員工                         | emps    | GET      |
| 查詢某個員工(來到修改頁面)           | emp/1   | GET      |
| 來到添加頁面                         | emp     | GET      |
| 添加員工                             | emp     | POST     |
| 來到修改頁面（查出員工進行信息回顯） | emp/1   | GET      |
| 修改員工                             | emp     | PUT      |
| 刪除員工                             | emp/1   | DELETE   |

3）、員工列表：

#### thymeleaf公共頁面元素抽取

```html
1、抽取公共片段
<div th:fragment="copy">
&copy; 2011 The Good Thymes Virtual Grocery
</div>

2、引入公共片段
<div th:insert="~{footer :: copy}"></div>
~{templatename::selector}：模板名::選擇器
~{templatename::fragmentname}:模板名::片段名

3、默認效果：
insert的公共片段在div標簽中
如果使用th:insert等屬性進行引入，可以不用寫~{}：
行內寫法可以加上：[[~{}]];[(~{})]；
```



三種引入公共片段的th屬性：

**th:insert**：將公共片段整個插入到聲明引入的元素中

**th:replace**：將聲明引入的元素替換為公共片段

**th:include**：將被引入的片段的內容包含進這個標簽中



```html
<footer th:fragment="copy">
&copy; 2011 The Good Thymes Virtual Grocery
</footer>

引入方式
<div th:insert="footer :: copy"></div>
<div th:replace="footer :: copy"></div>
<div th:include="footer :: copy"></div>

效果
<div>
    <footer>
    &copy; 2011 The Good Thymes Virtual Grocery
    </footer>
</div>

<footer>
&copy; 2011 The Good Thymes Virtual Grocery
</footer>

<div>
&copy; 2011 The Good Thymes Virtual Grocery
</div>
```



引入片段的時候傳入參數： 

```html

<nav class="col-md-2 d-none d-md-block bg-light sidebar" id="sidebar">
    <div class="sidebar-sticky">
        <ul class="nav flex-column">
            <li class="nav-item">
                <a class="nav-link active"
                   th:class="${activeUri=='main.html'?'nav-link active':'nav-link'}"
                   href="#" th:href="@{/main.html}">
                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home">
                        <path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
                        <polyline points="9 22 9 12 15 12 15 22"></polyline>
                    </svg>
                    Dashboard <span class="sr-only">(current)</span>
                </a>
            </li>

<!--引入側邊欄;傳入參數-->
<div th:replace="commons/bar::#sidebar(activeUri='emps')"></div>
```