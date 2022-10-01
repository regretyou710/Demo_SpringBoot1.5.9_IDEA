## SpringBoot對靜態資源的映射規則；

```
ver 1.5.9
@ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
public class ResourceProperties implements ResourceLoaderAware {
  //可以設置和靜態資源有關的參數，緩存時間等
}
```

```
ver 1.5.9
WebMvcAuotConfiguration：
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!this.resourceProperties.isAddMappings()) {
            logger.debug("Default resource handling disabled");
            return;
        }
        Integer cachePeriod = this.resourceProperties.getCachePeriod();
        if (!registry.hasMappingForPattern("/webjars/**")) {
            customizeResourceHandlerRegistration(
                    registry.addResourceHandler("/webjars/**")
                            .addResourceLocations(
                                    "classpath:/META-INF/resources/webjars/")
                    .setCachePeriod(cachePeriod));
        }
        String staticPathPattern = this.mvcProperties.getStaticPathPattern();
        
        //靜態資源文件夾映射
        if (!registry.hasMappingForPattern(staticPathPattern)) {
            customizeResourceHandlerRegistration(
                    registry.addResourceHandler(staticPathPattern)
                            .addResourceLocations(
                                    this.resourceProperties.getStaticLocations())
                    .setCachePeriod(cachePeriod));
        }
    }

    //配置歡迎頁映射
    @Bean
    public WelcomePageHandlerMapping welcomePageHandlerMapping(
            ResourceProperties resourceProperties) {
        return new WelcomePageHandlerMapping(resourceProperties.getWelcomePage(),
                this.mvcProperties.getStaticPathPattern());
    }
    
    //配置喜歡的圖標
    @Configuration
    @ConditionalOnProperty(value = "spring.mvc.favicon.enabled", matchIfMissing = true)
    public static class FaviconConfiguration {
    
        private final ResourceProperties resourceProperties;
    
        public FaviconConfiguration(ResourceProperties resourceProperties) {
            this.resourceProperties = resourceProperties;
        }
    
        @Bean
        public SimpleUrlHandlerMapping faviconHandlerMapping() {
            SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
            mapping.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
            //所有  **/favicon.ico 
            mapping.setUrlMap(Collections.singletonMap("**/favicon.ico",
                    faviconRequestHandler()));
            return mapping;
        }
    
        @Bean
        public ResourceHttpRequestHandler faviconRequestHandler() {
            ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
            requestHandler
                    .setLocations(this.resourceProperties.getFaviconLocations());
            return requestHandler;
        }
    }
```

==1）、所有 /webjars/** (url) ，都去 classpath:/META-INF/resources/webjars/ 找資源；==

​	webjars：以jar包的方式引入靜態資源；

http://www.webjars.org/

![](../images/搜狗截图20180203181751.png)

localhost:8080/webjars/jquery/3.6.1/jquery.js

```
<!--引入jquery-webjar-->在訪問的時候只需要寫webjars下面資源的名稱即可
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.6.1</version>
</dependency>
```

==2）、"/**" 訪問當前項目的任何資源，都去（靜態資源的文件夾）找映射==

```
下列為經態資源的文件夾(優先級由大到小)
"classpath:/META-INF/resources/", 
"classpath:/resources/",
"classpath:/static/", 
"classpath:/public/" 
"/"：當前項目的根路徑(ver 1.5.9)
```

localhost:8080/abc ===  去靜態資源文件夾底下(不用加靜態資源的目錄名)找abc

==3）、歡迎頁； 靜態資源文件夾下的所有index.html頁面；被"/**"映射；==

​	localhost:8080/   找index頁面

==4）、所有的 **/favicon.ico  都是在靜態資源文件下找；==

可以透過資源配置文件修改默認的靜態資源路徑