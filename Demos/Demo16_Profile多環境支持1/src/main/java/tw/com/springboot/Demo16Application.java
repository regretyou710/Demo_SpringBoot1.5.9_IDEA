package tw.com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Demo16Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo16Application.class, args);
        /*
        2022-09-20 19:13:58.518  INFO 3848 --- [           main] tw.com.springboot.Demo16Application      : The following 1 profile is active: "dev"
        2022-09-20 19:14:00.527  INFO 3848 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8082 (http)
         */
    }
}
