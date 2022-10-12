package tw.com.springboot.entity;

import javax.persistence.*;

//使用JPA注解配置映射關系
@Entity //告訴JPA這是一個實體類（和數據表映射的類）
@Table(name = "springboot_user") //@Table來指定和哪個數據表對應;如果省略默認表名就是user；
public class User {

    @Id //這是一個主鍵
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主鍵
    private Integer id;

    @Column(name = "last_name", length = 50) //這是和數據表對應的一個列
    private String lastName;
    
    @Column //省略默認列名就是屬性名
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
