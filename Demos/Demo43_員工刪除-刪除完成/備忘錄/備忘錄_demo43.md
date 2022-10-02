### RESTFul DELETE不起作用原因

```
在org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration中
    
    @Bean
    //判斷當前需要注入Spring容器中的bean的實現類是否已經含有，有的話不注入，沒有就注入
	@ConditionalOnMissingBean(HiddenHttpMethodFilter.class)	
    @ConditionalOnProperty(prefix = "spring.mvc.hiddenmethod.filter", name = "enabled")
	public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
		return new OrderedHiddenHttpMethodFilter();
	}
其中ConditionalOnProperty的boolean matchIfMissing() default false; 默認false
表示沒有在配置文件中配置的話就默認不生效
```

所以解決辦法有:
- 在SpringBoot的Configuration類中加一個@Bean方法返回一個HiddenHttpMethodFilter 組件
```
public class SpringMVCConfig implements WebMvcConfigurer {
    //註冊HiddenHttpMethodFilter组件
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
因為OrderedHiddenHttpMethodFilter繼承HiddenHttpMethodFilter
當判斷條件的註解都滿足時，會返回OrderedHiddenHttpMethodFilter的bean
所以當判斷條件的註解不滿足時表示容器中沒有OrderedHiddenHttpMethodFilter這個bean
去執行HiddenHttpMethodFilter該做的事，所以直接在配置類中註冊HiddenHttpMethodFilter组件
```
- 在application配置文件中加入: spring.mvc.hiddenmethod.filter.enabled=true, 就生效了, 就可以訪問
```properties
# 啟動hiddenmethod過濾器
#spring.mvc.hiddenmethod.filter.enabled = true
```
