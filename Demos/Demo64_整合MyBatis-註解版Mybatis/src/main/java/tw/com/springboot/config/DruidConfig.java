package tw.com.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    //透過擴展配置將druid的屬性注入到ioc容器
    @ConfigurationProperties(prefix = "spring.datasource")//告訴SpringBoot將本類(DruidDataSource)中的所有屬性和配置文件中相關的配置進行綁定；
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    //配置Druid的監控
    //1、配置一個管理後台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();

        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        initParams.put("allow", "");//默認就是允許所有訪問
        //initParams.put("allow", "localhost");//表示只有localhost能訪問
        initParams.put("deny", "192.168.50.103");//表示拒絕訪問

        bean.setInitParameters(initParams);
        return bean;
    }

    //2、配置一個web監控的filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");//表示不過濾的路徑

        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));

        return bean;
    }
}
