package tw.com.springboot;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tw.com.springboot.bean.Person;
import tw.com.springboot.bean.Person2;
import tw.com.springboot.bean.Person3;

/**
 * SpringBoot單元測試;
 * <p>
 * 可以在測試期間很方便的類似編碼一樣進行自動注入等容器的功能
 *
 * @RunWith(SpringRunner.class)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class Demo13ApplicationTests {
    @Autowired
    Person person;

    @Autowired
    Person2 person2;

    @Autowired
    Person3 person3;

    @Test
    void contextLoads() {
        //System.out.println(person);//Person{lastName='eason', age=22, boss=true, birth=null, maps=null, lists=null, dog=null}
        //System.out.println(person2);//Failed to bind properties under 'person2' to tw.com.springboot.bean.Person2 failed
        System.out.println(person3);//Could not resolve placeholder 'person3.maps' in value "${person3.maps}"
    }

}
