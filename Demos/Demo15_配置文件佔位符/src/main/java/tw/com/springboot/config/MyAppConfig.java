package tw.com.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tw.com.springboot.service.HelloService;

/**
 * @Configuration：指明當前類是一個配置類；就是來替代之前的Spring配置文件 在配置文件中用<bean><bean/>標簽添加組件
 */
@Configuration
public class MyAppConfig {
    //將方法的返回值添加到容器中；容器中這個組件默認的id就是方法名
    @Bean
    public HelloService helloService02() {
        System.out.println("配置類@Bean給容器中添加組件了...");
        return new HelloService();
    }
}
