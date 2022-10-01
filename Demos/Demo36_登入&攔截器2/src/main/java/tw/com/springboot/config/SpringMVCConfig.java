package tw.com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import tw.com.springboot.component.LoginHandlerInterceptor;
import tw.com.springboot.component.MyLocaleResolver;

import java.util.Arrays;
import java.util.List;

//擴展SpringMVC功能
//@Configuration
//public class SpringMVCConfig extends WebMvcConfigurerAdapter {
//    //官方在spring5棄用了
//    //WebMvcConfigurerAdapter，因為springboot2.0使用的spring5，所以會出現過時。
//}

//@EnableWebMvc
@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {
    private static final List<String> STATIC_EXCLUDE_PATH = Arrays.asList("/webjars/**", "/asserts/**", "/icon/**");

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
    public WebMvcConfigurer webMvcConfigurer() {
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("login");
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //註冊攔截器(方式二)
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns("/","/index.html","/user/login");
//            }
        };
        return webMvcConfigurer;
    }

    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }

    //註冊攔截器(方式一)
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * ver 1.5.9
         * 靜態資源: *.css、*.js...在SpringMVC框架中需要額外進行靜態資源訪問的排除
         * 在SpringBoot中已經做好靜態資源映射，可以不用處理靜態資源的攔截器排除，靜態資源也能正常訪問
         */
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/", "/index.html", "/user/login").excludePathPatterns(STATIC_EXCLUDE_PATH);
    }
}
