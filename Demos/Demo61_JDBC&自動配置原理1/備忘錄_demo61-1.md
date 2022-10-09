# SpringBoot與數據訪問

## 1、JDBC

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```



```yaml
spring:
  datasource:
    username: root
    password: 123456
    url: jdbc:mysql://192.168.15.22:3306/jdbc
    driver-class-name: com.mysql.jdbc.Driver
```

效果：

​	默認是用org.apache.tomcat.jdbc.pool.DataSource作為數據源；

​	數據源的相關配置都在DataSourceProperties里面；

自動配置原理：

org.springframework.boot.autoconfigure.jdbc：

1、參考DataSourceConfiguration，根據配置創建數據源，默認使用Tomcat連接池；可以使用spring.datasource.type指定自定義的數據源類型；

2、SpringBoot默認可以支持；

```
org.apache.tomcat.jdbc.pool.DataSource、HikariDataSource、BasicDataSource、
```

3、自定義數據源類型

```java
/**
 * Generic DataSource configuration.
 */
@ConditionalOnMissingBean(DataSource.class)
@ConditionalOnProperty(name = "spring.datasource.type")
static class Generic {

   @Bean
   public DataSource dataSource(DataSourceProperties properties) {
       //使用DataSourceBuilder創建數據源，利用反射創建響應type的數據源，並且綁定相關屬性
      return properties.initializeDataSourceBuilder().build();
   }

}
```