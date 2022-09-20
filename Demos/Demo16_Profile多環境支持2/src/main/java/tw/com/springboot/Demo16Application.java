package tw.com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Demo16Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo16Application.class, args);

        /**
         * 其實 application.yml 檔案會被優先載入，
         *
         * 而如果同時存在 application.properties 檔案，並且存在相同的配置，
         *
         * 那麼則會用 application.properties 檔案中的配置覆蓋之前的配置；
         *
         * 也就是說哪個檔案被最後載入，哪個才具有最高級別，
         *
         * 因為最後的，會覆蓋前面所有的。
         */
    }
}
