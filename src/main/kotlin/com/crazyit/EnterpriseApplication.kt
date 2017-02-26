package com.crazyit

import com.google.gson.Gson
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
open class EnterpriseApplication {

	@Bean
	open fun gson(): Gson {
		return Gson()
	}
}

fun main(args: Array<String>) {
    SpringApplication.run(EnterpriseApplication::class.java, *args)
}