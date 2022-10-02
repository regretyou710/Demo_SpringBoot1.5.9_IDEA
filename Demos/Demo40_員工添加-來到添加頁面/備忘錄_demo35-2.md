### 點擊超連結國際化
- 國際化有一個重要的對象Locale(區域訊息)，SpringMVC提供一個LocaleResolver來獲取Locale對象
- 在WebMvcAutoConfiguration類中自動配置了LocaleResolver組件
- 根據源碼，如果資源配置文件中有配置則使用，否則根據請求頭獲取
- 而在LocaleResolver組件上註解了@ConditionalOnMissingBean(name = DispatcherServlet.LOCALE_RESOLVER_BEAN_NAME)
- 如果IOC容器中沒有LocaleResolver的時候才自動配置
- 所以不使用默認的LocaleResolver，可自定義LocaleResolver類並在配置類將組件註冊(@Bean)
