package tw.com.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication //WEB應用程序才生效自動配置
@EnableConfigurationProperties(HelloProperties.class) //啟用ConfigurationProperties註解(使資源配置文件綁定HelloProperties.class屬性)
public class HelloAutoConfiguration {

    /**
     * 1. 啟用ConfigurationProperties註解並與資源配置文件綁定HelloProperties.class屬性
     * 2. 當前擴展的在自動配置時helloService會被調用並返回HelloService類注入到IOC容器中
     * 3. helloService被調用前，HelloService類需要傳入HelloProperties類，作為HelloService中sayHello方法的返回內容裡面調用
     * 4. 為使自動配置類能夠生效，要在資源文件配置全限定類名
     * 5. 在maven將demo71-spring-boot-starter-autoconfigure執行Lifecycle => install(加到maven倉庫中)
     * 6. 在maven將demo71-spring-boot-starter執行Lifecycle => install(加到maven倉庫中)
     * 7. demo71-3在自定義starter被引用時，就可以使用HelloService中sayHello方法
     */
    @Autowired
    HelloProperties helloProperties;

    @Bean
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        helloService.setHelloProperties(helloProperties);
        return helloService;
    }
}
