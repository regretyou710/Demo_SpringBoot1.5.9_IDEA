### 擴展SpringMVC

```xml
    <mvc:view-controller path="/hello" view-name="success"/>
    <mvc:interceptors>
        <mvc:interceptor>
            <mvc:mapping path="/hello"/>
            <bean></bean>
        </mvc:interceptor>
    </mvc:interceptors>
```

**==編寫一個配置類（@Configuration），是WebMvcConfigurerAdapter類型；不能標注@EnableWebMvc==**;

既保留了所有的自動配置，也能用我們擴展的配置；

```java
//使用WebMvcConfigurerAdapter可以來擴展SpringMVC的功能
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // super.addViewControllers(registry);
        //瀏覽器發送 /atguigu 請求來到 success
        registry.addViewController("/atguigu").setViewName("success");
    }
}
```
原理：

​	1）、WebMvcAutoConfiguration是SpringMVC的自動配置類

​	2）、在做其他自動配置時會導入；@Import(**EnableWebMvcConfiguration**.class)

```java
    @Configuration
	public static class EnableWebMvcConfiguration extends DelegatingWebMvcConfiguration {
      private final WebMvcConfigurerComposite configurers = new WebMvcConfigurerComposite();

	 //從容器中獲取所有的WebMvcConfigurer
      @Autowired(required = false)
      public void setConfigurers(List<WebMvcConfigurer> configurers) {
          if (!CollectionUtils.isEmpty(configurers)) {
              this.configurers.addWebMvcConfigurers(configurers);
            	//一個參考實現；將所有的WebMvcConfigurer相關配置都來一起調用；  
            	@Override
             // public void addViewControllers(ViewControllerRegistry registry) {
              //    for (WebMvcConfigurer delegate : this.delegates) {
               //       delegate.addViewControllers(registry);
               //   }
              }
          }
	}
```

​	3）、容器中所有的WebMvcConfigurer都會一起起作用；

​	4）、我們的配置類也會被調用；

​	效果：SpringMVC的自動配置和我們的擴展配置都會起作用；