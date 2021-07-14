package nl.saxion.webapps.collectionofmusic.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Collections;

@EnableSwagger2
@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()

                .apis(RequestHandlerSelectors.basePackage("nl.saxion.webapps.collectionofmusic"))

                .paths(PathSelectors.regex("/api/v1.*"))
                .build()
                .apiInfo(apiInfo());
    }
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Collection Of Music REST API",
                "Collection Of Music REST API",

                "API 1.0",

                "https://www.saxion.nl/over-saxion/disclaimer",
                new Contact("Kalli van den Heuvel", "https://www.saxion.nl","500741@student.saxion.nl"),
                "MIT License", "https://opensource.org/licenses/MIT",
                Collections.emptyList());
    }
}
