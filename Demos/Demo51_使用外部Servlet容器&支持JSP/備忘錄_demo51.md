## 使用外置的Servlet容器

嵌入式Servlet容器：應用打成可執行的jar

​		優點：簡單、便攜；

​		缺點：默認不支持JSP、優化定制比較覆雜（使用定制器【ServerProperties、自定義EmbeddedServletContainerCustomizer】，自己編寫嵌入式Servlet容器的創建工廠【EmbeddedServletContainerFactory】）；



外置的Servlet容器：外面安裝Tomcat---應用war包的方式打包；

### 步驟

1）、必須創建一個war項目；（利用idea創建好目錄結構）

2）、將嵌入式的Tomcat指定為provided；

```xml
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-tomcat</artifactId>
   <scope>provided</scope>
</dependency>
```

3）、必須編寫一個**SpringBootServletInitializer**的子類，並調用configure方法

```java
public class ServletInitializer extends SpringBootServletInitializer {

   @Override
   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
       //傳入SpringBoot應用的主程序
      return application.sources(SpringBoot04WebJspApplication.class);
   }

}
```

4）、啟動服務器就可以使用；