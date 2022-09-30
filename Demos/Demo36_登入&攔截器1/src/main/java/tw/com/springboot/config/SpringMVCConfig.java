package tw.com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import tw.com.springboot.component.MyLocaleResolver;

//擴展SpringMVC功能
//@Configuration
//public class SpringMVCConfig extends WebMvcConfigurerAdapter {
//    //官方在spring5棄用了
//    //WebMvcConfigurerAdapter，因為springboot2.0使用的spring5，所以會出現過時。
//}

//@EnableWebMvc
@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {
    //配置視圖控制
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/goSuccess").setViewName("success");
    }

    //所有的WebMvcConfigurer都會因為
    //org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
    //當WebMvcAutoConfiguration進行自動配置時WebMvcAutoConfigurationAdapter也進行配置
    //此時WebMvcAutoConfigurationAdapter會根據@Import(EnableWebMvcConfiguration.class)生成EnableWebMvcConfiguration的Bean到IOC容器上下文中
    //類上面註解@Import(EnableWebMvcConfiguration.class)使得SpringMVC的自動配置和我們的擴展配置都起作用
    //在舊版中WebMvcAutoConfigurationAdapter extends WebMvcConfigurerAdapter,WebMvcConfigurerAdapter implements WebMvcConfigurer
    @Bean//將組件註冊在IOC容器中
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };
        return webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
}
