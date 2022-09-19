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
        //透過配置類(@Configuration)將組件(@Bean)添加至spring容器中
        System.out.println(ioc);//org.springframework.web.context.support.GenericWebApplicationContext@2b0f373b
        System.out.println(ioc.containsBean("helloService02"));//true
        System.out.println(ioc.getBean("helloService02", HelloService.class));//tw.com.springboot.service.HelloService@4beaf6bd
    }
}

