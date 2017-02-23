package com.crazyit.core.config

import com.alibaba.druid.pool.DruidDataSource
import com.alibaba.druid.support.http.StatViewServlet
import com.alibaba.druid.support.http.WebStatFilter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

/**
 * @author CrazyApeDX
 * Created on 2017/2/8.
 */
@Configuration
@ConfigurationProperties("spring.datasource")
open class DruidConfig(
    var driverClassName: String = "",
    var url: String = "",
    var username: String = "",
    var password: String = "",
    var initialSize: Int = 0,
    var minIdle: Int = 0,
    var maxActive: Int = 0,
    var maxWait: Long = 0L,
    var timeBetweenEvictionRunsMillis: Long = 0L,
    var validationQuery: String = "",
    var testWhileIdle: Boolean = false,
    var testOnBorrow: Boolean = false,
    var testOnReturn: Boolean = false,
    var filters: String = "",
    var connectionProperties: String = "",
    var servletUsername: String = "",
    var servletPassword: String = "",
    var servletResetEnable: String = "",
    var servletUrl: String = "",
    var filterPattern: String = "",
    var filterExclusions: String = ""
) {

    @Bean(initMethod = "init", destroyMethod = "close")
    open fun dataSource(): DataSource {
        val dataSource: DruidDataSource = DruidDataSource()
        dataSource.driverClassName = driverClassName
        dataSource.url = url
        dataSource.username = username
        dataSource.password = password
        dataSource.initialSize = initialSize
        dataSource.minIdle = minIdle
        dataSource.maxActive = maxActive
        dataSource.maxWait = maxWait
        dataSource.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis
        dataSource.validationQuery = validationQuery
        dataSource.isTestWhileIdle = testWhileIdle
        dataSource.isTestOnBorrow = testOnBorrow
        dataSource.isTestOnReturn = testOnReturn

        dataSource.setFilters(filters)
        dataSource.setConnectionProperties(connectionProperties)
        return dataSource
    }

    @Bean
    open fun druidServlet(): ServletRegistrationBean {
        val register: ServletRegistrationBean = ServletRegistrationBean(StatViewServlet())
        register.addUrlMappings(servletUrl)
        register.addInitParameter("loginUsername", servletUsername)
        register.addInitParameter("loginPassword", servletPassword)
        register.addInitParameter("resetEnable", servletResetEnable)
        return register
    }

    @Bean
    open fun druidFilter(): FilterRegistrationBean {
        val register: FilterRegistrationBean = FilterRegistrationBean(WebStatFilter())
        register.addUrlPatterns(filterPattern)
        register.addInitParameter("exclusions", filterExclusions)
        return register
    }
}
