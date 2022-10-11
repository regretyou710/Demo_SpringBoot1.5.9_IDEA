package tw.com.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("tw.com.springboot.mapper")
public class Demo65Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo65Application.class, args);
    }

}
