package tw.com.springboot;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tw.com.springboot.bean.Person;

/**
 * SpringBoot單元測試;
 * <p>
 * 可以在測試期間很方便的類似編碼一樣進行自動注入等容器的功能
 *
 * @RunWith(SpringRunner.class)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class Demo12ApplicationTests {
    @Autowired
    Person person;

    @Test
    void contextLoads() {
        System.out.println(person);
    }

}
