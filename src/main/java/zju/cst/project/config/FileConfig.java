package zju.cst.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: wengyifan
 * @description:
 * @date: 2021/3/3 6:42 下午
 */
@Configuration
public class FileConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/src/main/resources/file/**").addResourceLocations("file:" + System.getProperty("user.dir") + "/src/main/resources/file/");
    }
}
