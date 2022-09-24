package tw.com.springboot;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Demo26ApplicationTests {
    //記錄器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {
        /**
         * 日誌的級別，由低到高: trace < debug < info < warn < error
         * 可以調整輸出的日誌級別，日誌就只會在這個級別及以後的高級別生效
         * SpringBoot默認使用info級別，可以在配置文件進行調整
         * 沒有指定級別的就使用SpringBoot默認規定的級別:root級別(root規定成了info級別)
         */
        logger.trace("這是trace日誌");
        logger.debug("這是debug日誌");
        logger.info("這是info日誌");
        logger.warn("這是warn日誌");
        logger.error("這是error日誌");
    }

}
