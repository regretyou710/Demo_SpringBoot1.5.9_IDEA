package tw.com.springboot.component;


import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

//給容器中加入我們自己定義的ErrorAttributes
@Component
public class MyErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributesMap = super.getErrorAttributes(webRequest, options);

        /**
         * 1. MyExceptionHamdler捕獲異常後轉發到/error(BasicErrorController)
         * 2. BasicErrorController的getErrorAttributes從DefaultErrorAttributes獲取瀏覽器或json的存入在作用域中的值
         */
        errorAttributesMap.put("remark", "這是返回MyErrorAttributes中的錯誤屬性");
        Map<String, Object> extendMap = (Map<String, Object>) webRequest.getAttribute("extend", 0);
        errorAttributesMap.put("extendMap",extendMap);
        return errorAttributesMap;
    }
}
