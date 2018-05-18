package com.example.demo.core.configurer;


import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author yangr
 * @Description swagger2配置
 * @date 2018/5/18
 */
@Configuration
@EnableSwagger2
public class SwaggerConfigurer {

    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("使用swagger2构建restful APIs")
                .description("更多介绍请登陆spring.io")
                .termsOfServiceUrl("www.baidu.com")
                .contact(new Contact("yangrh","https://github.com/Ruuuuu/springboot18","yangruhang521@qq.com"))
                .version("1.0")
                .build();
    }

}
