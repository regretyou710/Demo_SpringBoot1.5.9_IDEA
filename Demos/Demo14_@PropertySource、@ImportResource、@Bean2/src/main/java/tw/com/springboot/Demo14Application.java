package tw.com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:beans.xml")
@SpringBootApplication
public class Demo14Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo14Application.class, args);
    }
}
