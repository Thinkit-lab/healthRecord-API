package com.olukunle.mkobo_assessment.utils;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "springdoc.swagger-ui.enabled", havingValue = "true", matchIfMissing = true)
public class AppConfig {
    @Value("${api.info.licence.name: Licence 2.0}")
    private String licenceName;
    @Value("${api.info.licence.url: https://www.apache.org/licenses/LICENSE-2.0}")
    private String licenceUrl;

    @Bean
    public OpenAPI productApi() {
        return new OpenAPI()
                .info(getApiInfo());
    }
    private Info getApiInfo() {
        Contact contact = new Contact().name("Olukunle Afolabi").email("afolabikunle2@gmail.com").url("https://github.com/Thinkit-lab");
        License licence = new License().name(licenceName).url(licenceUrl);
        return new Info()
                .title("MKOBO Assessment")
                .description("Complete REST Health record API consumable by web clients")
                .version("V1")
                .contact(contact)
                .license(licence);
    }

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.OAS_30)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(apiInfo());
//    }

//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Mkobo Assessment")
//                .description("Complete REST Health record API consumable by web clients")
//                .license("MIT License")
//                .version("1.1.0")
//                .licenseUrl("https://opensource.org/licenses/MIT")
//                .build();
//    }
}
