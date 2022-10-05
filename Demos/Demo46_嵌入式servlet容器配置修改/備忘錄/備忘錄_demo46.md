## 8、配置嵌入式Servlet容器<SpringBoot ver 1.5.9>

SpringBoot默認使用Tomcat作為嵌入式的Servlet容器；

![](images/搜狗截图20180301142915.png)



問題？

### 1）、如何定制和修改Servlet容器的相關配置；

方式1:修改和server有關的配置（ServerProperties【也是EmbeddedServletContainerCustomizer】）；

```properties
server.port=8081
server.context-path=/crud

server.tomcat.uri-encoding=UTF-8

//通用的Servlet容器設置
server.xxx
//Tomcat的設置
server.tomcat.xxx
```

方式2:編寫一個**EmbeddedServletContainerCustomizer**：嵌入式的Servlet容器的定制器；來修改Servlet容器的配置

```java
@Bean  //一定要將這個定制器加入到容器中
public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
    return new EmbeddedServletContainerCustomizer() {

        //定制嵌入式的Servlet容器相關的規則
        @Override
        public void customize(ConfigurableEmbeddedServletContainer container) {
            container.setPort(8083);
        }
    };
}
```