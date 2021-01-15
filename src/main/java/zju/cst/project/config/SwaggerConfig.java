package zju.cst.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2 // 开启Swagger
public class SwaggerConfig {
    // 配置Swagger的Docket的bean实例
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("项目管理系统", "http://www.zju.com", "wengyfian@zju.edu.cn");

        return new ApiInfo(
                "项目管理系统的SwaggerAPI文档",
                "浙江大学软件学院-项目管理系统",
                "v1.0",
                "http://localhost:8888/",
                contact,
                "Apache 2.0",
                "http://www.zju.com",
                new ArrayList()
        );
    }
}
