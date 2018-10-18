package br.com.jadler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@SpringBootApplication
public class Application {

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(basePackage("br.com.jadler.controller"))
                .paths(any())
                .build()
                .apiInfo(info());

        return docket;
    }

    private ApiInfo info() {
        ApiInfo info = new ApiInfoBuilder()
                .title("B2W Digital REST API")
                .description("\"B2W Digital REST API for Star Wars\"")
                .version("1.0.0")
                .build();

        return info;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
