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
```

==1）、所有 /webjars/** (url) ，都去 classpath:/META-INF/resources/webjars/ 找資源；==

​	webjars：以jar包的方式引入靜態資源；

http://www.webjars.org/

![](./images/搜狗截图20180203181751.png)

localhost:8080/webjars/jquery/3.6.1/jquery.js

```
<!--引入jquery-webjar-->在訪問的時候只需要寫webjars下面資源的名稱即可
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.6.1</version>
</dependency>
```