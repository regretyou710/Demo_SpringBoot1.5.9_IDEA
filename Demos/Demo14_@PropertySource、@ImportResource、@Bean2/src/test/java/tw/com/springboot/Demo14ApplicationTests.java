package tw.com.springboot;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import tw.com.springboot.bean.Person;
import tw.com.springboot.service.HelloService;

@RunWith(SpringRunner.class)
@SpringBootTest
class Demo14ApplicationTests {
    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    void contextLoads() {
        System.out.println(person);
    }

    @Test
    void testHelloService() {
        //1. Spring Boot裡面沒有Spring的配置文件，我們自己編寫的配置文件，也不能自動識別；
        System.out.println(ioc);
        System.out.println(ioc.containsBean("helloService"));//false
        System.out.println(ioc.getBean("helloService", HelloService.class));//org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'helloService' available

        //2. 想讓Spring的配置文件生效，加載進來；@ImportResource標注在一個配置類上
        //在Spring Boot主程序加上@ImportResource("classpath:beans.xml")註解(Spring Boot主程序是一種配置類)
        System.out.println(ioc.containsBean("helloService"));//true
        System.out.println(ioc.getBean("helloService", HelloService.class));//tw.com.springboot.service.HelloService@17f2dd85
    }
}

