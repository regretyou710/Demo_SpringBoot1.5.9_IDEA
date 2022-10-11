package tw.com.springboot.config;

import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
    /**
     * 由MybatisAutoConfiguration類中的applyConfiguration方法得知
     * 在自動配置時會注入sqlSessionFactory，而sqlSessionFactory又會調用applyConfiguration
     * applyConfiguration又會將擴展的配置起作用
     */

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);//開啟駝峰命名法
            }
        };
    }
}
