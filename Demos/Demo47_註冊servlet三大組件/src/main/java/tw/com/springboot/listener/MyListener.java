package tw.com.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("web應用啟動..");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("當前web項目銷毀..");
        //Idea要按Services的Exit才會起作用(不是Stop項目)
    }
}
