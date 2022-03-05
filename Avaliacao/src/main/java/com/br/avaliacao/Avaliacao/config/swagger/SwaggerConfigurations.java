package com.br.avaliacao.Avaliacao.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfigurations {

	@Bean
	public Docket statesApi() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.br.avaliacao.Avaliacao")).paths(PathSelectors.ant("/**"))
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {

		ApiInfo apiInfo = new ApiInfoBuilder().title("API for evaluation")
				.description("It's an API developed for a evaluation.").license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0").termsOfServiceUrl("/service.html")
				.version("1.0.0").contact(new Contact("Dev Support", "fictitious.site.com.br", "fictitious@gmail.com"))
				.build();

		return apiInfo;
	}

}
