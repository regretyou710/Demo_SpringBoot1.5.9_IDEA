package tw.com.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {

    //透過擴展配置將druid的屬性注入到ioc容器
    @ConfigurationProperties(prefix = "spring.datasource")//告訴SpringBoot將本類(DruidDataSource)中的所有屬性和配置文件中相關的配置進行綁定；
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }
}
