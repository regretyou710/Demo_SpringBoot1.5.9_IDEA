### 國際化

SpringMVC作法:
- **1）、編寫國際化配置文件；**

- 2）、使用ResourceBundleMessageSource管理國際化資源文件

- 3）、在頁面使用fmt:message取出國際化內容


SpringBoot作法:  
步驟：
- 1）、編寫國際化配置文件，抽取頁面需要顯示的國際化消息

- 2）、SpringBoot自動配置好了管理國際化資源文件的組件；
    ```
    ver 1.5.9
    @ConfigurationProperties(prefix = "spring.messages")
    public class MessageSourceAutoConfiguration {
        
        /**
         * Comma-separated list of basenames (essentially a fully-qualified classpath
         * location), each following the ResourceBundle convention with relaxed support for
         * slash based locations. If it doesn't contain a package qualifier (such as
         * "org.mypackage"), it will be resolved from the classpath root.
         */
        private String basename = "messages";  
        //我們的配置文件可以直接放在類路徑下叫messages.properties；
        
        @Bean
        public MessageSource messageSource() {
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            if (StringUtils.hasText(this.basename)) {
                //設置國際化資源文件的基礎名（去掉語言國家代碼的）
                messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(
                        StringUtils.trimAllWhitespace(this.basename)));
            }
            if (this.encoding != null) {
                messageSource.setDefaultEncoding(this.encoding.name());
            }
            messageSource.setFallbackToSystemLocale(this.fallbackToSystemLocale);
            messageSource.setCacheSeconds(this.cacheSeconds);
            messageSource.setAlwaysUseMessageFormat(this.alwaysUseMessageFormat);
            return messageSource;
        }
    ```

- 3）、去頁面獲取國際化的值；  
    效果：根據瀏覽器語言設置的信息切換了國際化；