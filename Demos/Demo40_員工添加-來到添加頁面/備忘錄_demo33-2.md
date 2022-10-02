### 3、全面接管SpringMVC；

SpringBoot對SpringMVC的自動配置不需要了，所有都是我們自己配置；所有的SpringMVC的自動配置都失效了

**我們需要在配置類中添加@EnableWebMvc即可；**

```java
//使用WebMvcConfigurerAdapter可以來擴展SpringMVC的功能
@EnableWebMvc
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

為什麽@EnableWebMvc自動配置就失效了；

1）@EnableWebMvc的核心

```java
@Import(DelegatingWebMvcConfiguration.class)
public @interface EnableWebMvc {
```

2）、

```java
@Configuration
public class DelegatingWebMvcConfiguration extends WebMvcConfigurationSupport {
```

3）、

```java
@Configuration
@ConditionalOnWebApplication
@ConditionalOnClass({ Servlet.class, DispatcherServlet.class,
		WebMvcConfigurerAdapter.class })
//容器中沒有這個組件的時候，這個自動配置類才生效
//(生效後才再WebMvcAutoConfigurationAdapter類@Import(EnableWebMvcConfiguration.class)導入)
//與配置類@EnableWebMvc註解後的底層先導入再進行@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)不同
@ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
@AutoConfigureAfter({ DispatcherServletAutoConfiguration.class,
		ValidationAutoConfiguration.class })
public class WebMvcAutoConfiguration {
```

4）、@EnableWebMvc將WebMvcConfigurationSupport組件導入進來；

5）、導入的WebMvcConfigurationSupport只是SpringMVC最基本的功能；

## 如何修改SpringBoot的默認配置
模式：

​	1）、SpringBoot在自動配置很多組件的時候，先看容器中有沒有用戶自己配置的（@Bean、@Component）如果有就用用戶配置的，如果沒有，才自動配置；如果有些組件可以有多個（ViewResolver）將用戶配置的和自己默認的組合起來；

​	2）、在SpringBoot中會有非常多的xxxConfigurer幫助我們進行擴展配置

​	3）、在SpringBoot中會有很多的xxxCustomizer幫助我們進行定制配置