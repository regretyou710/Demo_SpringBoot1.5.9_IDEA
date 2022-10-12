## 整合SpringData JPA

### 1）、SpringData簡介

![](images/搜狗截图20180306105412.png)

### 2）、整合SpringData JPA

JPA:ORM（Object Relational Mapping）；

1）、編寫一個實體類（bean）和數據表進行映射，並且配置好映射關系；

```java
//使用JPA注解配置映射關系
@Entity //告訴JPA這是一個實體類（和數據表映射的類）
@Table(name = "tbl_user") //@Table來指定和哪個數據表對應;如果省略默認表名就是user；
public class User {

    @Id //這是一個主鍵
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增主鍵
    private Integer id;

    @Column(name = "last_name",length = 50) //這是和數據表對應的一個列
    private String lastName;
    @Column //省略默認列名就是屬性名
    private String email;
```

2）、編寫一個Dao接口來操作實體類對應的數據表（Repository）

```java
//繼承JpaRepository來完成對數據庫的操作
public interface UserRepository extends JpaRepository<User,Integer> {
}

```

3）、基本的配置JpaProperties

```yaml
spring:  
 jpa:
    hibernate:
#     更新或者創建數據表結構
      ddl-auto: update
#    控制台顯示SQL
    show-sql: true
```