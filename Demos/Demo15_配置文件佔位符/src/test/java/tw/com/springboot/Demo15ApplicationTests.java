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
class Demo15ApplicationTests {
    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    void contextLoads() {
        System.out.println(person);//Person{lastName='person19259df20-b757-486a-91ab-b9f02073fcbf', age=949996549, boss=false, birth=Sun Sep 18 00:00:00 CST 2022, maps={k1=v1, k2=v2}, lists=[a, b, c], dog=Dog{name='person12fffc35d-7348-4a08-a7a5-83468bc49915_小狗', age=2}}

        //佔位符獲取之前配置的值，如果没有可以使用冒號指定默认值
        //如果屬性未宣告可以使用冒號指定默認值
        /*
        person.last-name=person1${random.uuid}
        person.age=${random.int}
        person.boss=false
        person.birth=2022/09/18
        person.maps.k1=v1
        person.maps.k2=v2
        person.lists=a,b,c
        person.dog.name=${person.last-name}_小狗
        person.dog.age=${person.weight:5}
        */

        //如果屬性已宣告但未在資源文件賦值情況下使用佔位符則會報錯
        /*
        #person.last-name=person1${random.uuid}
        person.age=${random.int}
        person.boss=false
        person.birth=2022/09/18
        person.maps.k1=v1
        person.maps.k2=v2
        person.lists=a,b,c
        person.dog.name=${person.last-name}_小狗
        */

        //當資源文件存在全局及自定義時，在全局找不到宣告的屬性則會往指定的資源文件中找
        //前提是有指定加載資源文件
    }
}

