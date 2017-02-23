package com.crazyit.core.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
@Aspect
@Component
open class WebLogAspect {

	val logger: Logger = LoggerFactory.getLogger("Web请求日志")

	@Pointcut("execution(* com.crazyit.web..*.*(..))")
	fun pointCut() { /* do nothing */ }

	@Before("pointCut()")
	fun doBefore(joinPoint: JoinPoint) {
		val servletAttribute: ServletRequestAttributes =
			RequestContextHolder.getRequestAttributes() as ServletRequestAttributes

		val request = servletAttribute.request
		logger.info("前置通知")

	}

	@AfterReturning(returning = "result", pointcut = "pointCut()")
	fun doAfter(result: Any) {
		logger.info(result.toString())
	}
}