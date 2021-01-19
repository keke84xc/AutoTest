package com.keke84.code.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//配置swagger，加载配置文件
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                //自己实现该方法
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                //正则匹配，例如：匹配MyGetMethodServer中的方法
                //此时由于这些方法没有相同的名字前缀，所以写的.*
                .paths(PathSelectors.regex("/.*"))
                .build();
    }
    private ApiInfo apiInfo() {
        //title：生成文档后的title
        return new ApiInfoBuilder().title("我的接口文件")
                .contact(new Contact("keke84xc","","123@qq.com"))
                .description("这是我的swaggerUI生成的文档")
                .version("1.0.0.0")
                .build();
    }
}
