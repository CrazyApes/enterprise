package com.crazyit

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.transaction.annotation.EnableTransactionManagement
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
@EnableTransactionManagement
class EnterpriseApplication

fun main(args: Array<String>) {
    SpringApplication.run(EnterpriseApplication::class.java, *args)
}
