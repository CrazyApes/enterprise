package com.crazyit.core.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

/**
 * @author CrazyApeDX
 * Created on 2017/2/22.
 */
@Configuration
@ConfigurationProperties("swagger.config")
open class SwaggerConfig(
	var title: String = "",
    var author: String = "",
    var version: String = "",
    var host: String = "",
    var apiLocation: String = ""
) {

	@Bean
	open fun apiInfo(): ApiInfo {
		return ApiInfoBuilder()
			.title(this.title)
			.description(this.author)
			.version(this.version)
			.build()
	}

	@Bean
	open fun docket(): Docket {
		return Docket(DocumentationType.SWAGGER_2)
			.host(this.host)
			.apiInfo(this.apiInfo())
			.select()
			.apis(RequestHandlerSelectors.basePackage(this.apiLocation))
			.paths(PathSelectors.any())
			.build()
	}
}