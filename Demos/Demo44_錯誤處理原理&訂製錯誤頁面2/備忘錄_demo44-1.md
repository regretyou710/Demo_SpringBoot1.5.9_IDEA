## 錯誤處理機制(有模板引擎的情況下)

### 1）、SpringBoot默認的錯誤處理機制

默認效果：

​		1）、瀏覽器，返回一個默認的錯誤頁面

![](images/搜狗截图20180226173408.png)

  瀏覽器發送請求的請求頭：

![](images/搜狗截图20180226180347.png)

​		2）、如果是其他客戶端，默認響應一個json數據

![](images/搜狗截图20180226173527.png)

​		![](images/搜狗截图20180226180504.png)

原理：

​	可以參照ErrorMvcAutoConfiguration；錯誤處理的自動配置；

  	給容器中添加了以下組件

​	1、DefaultErrorAttributes：

```java
幫我們在頁面共享信息；
@Override
	public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes,
			boolean includeStackTrace) {
		Map<String, Object> errorAttributes = new LinkedHashMap<String, Object>();
		errorAttributes.put("timestamp", new Date());
		addStatus(errorAttributes, requestAttributes);
		addErrorDetails(errorAttributes, requestAttributes, includeStackTrace);
		addPath(errorAttributes, requestAttributes);
		return errorAttributes;
	}
```



​	2、BasicErrorController：處理默認/error請求

```java
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class BasicErrorController extends AbstractErrorController {
    
    @RequestMapping(produces = "text/html")//產生html類型的數據；瀏覽器發送的請求來到這個方法處理
	public ModelAndView errorHtml(HttpServletRequest request,
			HttpServletResponse response) {
		HttpStatus status = getStatus(request);
		Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
				request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
		response.setStatus(status.value());
        
        //去哪個頁面作為錯誤頁面；包含頁面地址和頁面內容
		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
		return (modelAndView == null ? new ModelAndView("static/error", model) : modelAndView);
	}

	@RequestMapping
	@ResponseBody    //產生json數據，其他客戶端來到這個方法處理；
	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request,
				isIncludeStackTrace(request, MediaType.ALL));
		HttpStatus status = getStatus(request);
		return new ResponseEntity<Map<String, Object>>(body, status);
	}
```



​	3、ErrorPageCustomizer：

```java
	@Value("${error.path:/error}")
	private String path = "/error";  系統出現錯誤以後來到error請求進行處理；（web.xml注冊的錯誤頁面規則）
```



​	4、DefaultErrorViewResolver：

```java
@Override
	public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status,
			Map<String, Object> model) {
		ModelAndView modelAndView = resolve(String.valueOf(status), model);
		if (modelAndView == null && SERIES_VIEWS.containsKey(status.series())) {
			modelAndView = resolve(SERIES_VIEWS.get(status.series()), model);
		}
		return modelAndView;
	}

	private ModelAndView resolve(String viewName, Map<String, Object> model) {
        //默認SpringBoot可以去找到一個頁面？  error/404
		String errorViewName = "error/" + viewName;
        
        //模板引擎可以解析這個頁面地址就用模板引擎解析
		TemplateAvailabilityProvider provider = this.templateAvailabilityProviders
				.getProvider(errorViewName, this.applicationContext);
		if (provider != null) {
            //模板引擎可用的情況下返回到errorViewName指定的視圖地址
			return new ModelAndView(errorViewName, model);
		}
        //模板引擎不可用，就在靜態資源文件夾下找errorViewName對應的頁面   error/404.html
		return resolveResource(errorViewName, model);
	}
```



​	步驟：

​		一但系統出現4xx或者5xx之類的錯誤；ErrorPageCustomizer就會生效（定制錯誤的響應規則）；就會來到/error請求；就會被**BasicErrorController**處理；

​		1）響應頁面；去哪個頁面是由**DefaultErrorViewResolver**解析得到的；

```java
protected ModelAndView resolveErrorView(HttpServletRequest request,
      HttpServletResponse response, HttpStatus status, Map<String, Object> model) {
    //所有的ErrorViewResolver得到ModelAndView
   for (ErrorViewResolver resolver : this.errorViewResolvers) {
      ModelAndView modelAndView = resolver.resolveErrorView(request, status, model);
      if (modelAndView != null) {
         return modelAndView;
      }
   }
   return null;
}
```
### 2）、如何定制錯誤響應：
- 如何定制錯誤的頁面；  
```
​			1）、有模板引擎的情況下；error/狀態碼; 【將錯誤頁面命名為  錯誤狀態碼.html 放在模板引擎文件夾里面的 error文件夾下】，發生此狀態碼的錯誤就會來到  對應的頁面；

​			我們可以使用4xx和5xx作為錯誤頁面的文件名來匹配這種類型的所有錯誤，精確優先（優先尋找精確的狀態碼.html）；		

​			頁面能獲取的信息；

​				timestamp：時間戳

​				status：狀態碼

​				error：錯誤提示

​				exception：異常對象

​				message：異常消息

​				errors：JSR303數據校驗的錯誤都在這里

​			2）、沒有模板引擎（模板引擎找不到這個錯誤頁面），靜態資源文件夾下找；

​			3）、以上都沒有錯誤頁面，就是默認來到SpringBoot默認的錯誤提示頁面；
```
​			