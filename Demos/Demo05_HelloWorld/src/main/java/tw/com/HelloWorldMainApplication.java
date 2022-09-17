package tw.com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication 來標注一個主程序類，說明這是一個Spring Boot應用
 */
@SpringBootApplication
public class HelloWorldMainApplication {
    public static void main(String[] args) {
        //Spring應用啟動起來
        SpringApplication.run(HelloWorldMainApplication.class, args);
    }
}
