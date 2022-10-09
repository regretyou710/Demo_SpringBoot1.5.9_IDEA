## 整合Druid數據源

```java
導入druid數據源
@Configuration
public class DruidConfig {
     @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid(){
       return  new DruidDataSource();
    }
}

```