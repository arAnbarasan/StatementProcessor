package com.rabo.configuration;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket postApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("rabo-api").apiInfo(apiInfo()).select().paths(postPaths()).build();
		
//		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.action")).paths(regex("/home.*"))
//                .build();
	}
	
	private Predicate<String> postPaths() {
		return  or(regex("/validateStatement.*"), regex("/validateStatement.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("RABO API")
				.description("RABO"
						+ " API reference for developers")
				.build();
	}
}