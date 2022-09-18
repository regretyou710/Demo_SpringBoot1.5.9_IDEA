package tw.com.springboot.bean;


import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
//@ConfigurationProperties(prefix = "person3")
@Component
//@Validated
public class Person3 {

    /**
     * @Value無法對複雜封裝類型進行注入
     */

    private String lastName;

    private Integer age;

    private Boolean boss;

    private Date birth;

    //@Value("${person3.maps}")
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
