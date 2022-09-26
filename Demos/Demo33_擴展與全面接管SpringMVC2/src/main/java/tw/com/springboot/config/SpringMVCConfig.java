package tw.com.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class SpringMVCConfig extends WebMvcConfigurerAdapter {
//    //官方在spring5棄用了
//    //WebMvcConfigurerAdapter，因為springboot2.0使用的spring5，所以會出現過時。
//}

@EnableWebMvc
@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {
    //配置視圖控制
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("goSuccess").setViewName("success");
    }
}
