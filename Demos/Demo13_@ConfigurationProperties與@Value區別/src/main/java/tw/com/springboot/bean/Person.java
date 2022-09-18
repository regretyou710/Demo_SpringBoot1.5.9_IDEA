package tw.com.springboot.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 將配置文件中配置的每一個屬性的值，映射到這個組件中
 *
 * @ConfigurationProperties : 告訴SpringBoot將本類中的所有屬性和配置文件中相關的配置進行綁定； prefix = "person"：配置文件中哪個下面的所有屬性進行一一映射
 * <p>
 * 只有這個組件是容器中的組件，才能使用容器提供的@ConfigurationProperties功能；
 */
//@ConfigurationProperties(prefix = "person")
@Component
public class Person {
    /**
     * 因Person透過註解方式成為spring容器中的組件
     *
     * <bean class="Person">
     * <property name="lastName" value="字面量/${key}從環境變量、配置文件中獲取值/#{SpEL}"></property>
     * <bean/>
     *
     * @Value("?") : 相當於為Spring容器中的組件(bean)的屬性注入值
     * ?可以是
     * 1. 字面量
     * 2. ${key}:從環境變量、配置文件中獲取值
     * 3. #{SpEL}:spring表達式,只能在@Value中使用。若在properties資源文件中的值使用表達式並透過@ConfigurationProperties注入是會報錯
     */

    @Value("${person.last-name}")//表示從appliction.properties資源文件中的person.last-name鍵獲取它的值並注入
    private String lastName;

    @Value("#{11*2}")//表示使用SpEL進行屬性注入
    private Integer age;

    @Value("true")//表示使用字面量進行屬性注入
    private Boolean boss;

    private Date birth;

    private Map<String, Object> maps;

    private List<Object> lists;

    private Dog dog;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
