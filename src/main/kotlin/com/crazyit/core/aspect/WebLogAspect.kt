package com.crazyit.core.aspect

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
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

	@Pointcut("execution(* com.crazyit.foundation.*.controller..*.*(..))")
	fun pointCut() { /* do nothing */ }

	@Before("pointCut()")
	fun doBefore(joinPoint: JoinPoint) {
		val servletAttribute: ServletRequestAttributes =
			RequestContextHolder.getRequestAttributes() as ServletRequestAttributes

		val request = servletAttribute.request
		val url: String = request.requestURI.toString()
		val requestMethod: String = request.method
		val clazzMethod: String = "${joinPoint.signature.declaringTypeName}.${joinPoint.signature.name}()"
		val args: Array<out Any>? = joinPoint.args

		logger.info("请求 -> $url, 方法 -> $requestMethod")
		logger.info("进入控制器 -> $clazzMethod" )
		logger.info("参数: ${this.argsToString(args)}")
	}

	@AfterReturning(returning = "result", pointcut = "pointCut()")
	fun doAfter(result: Any) {
		val responseEntity: ResponseEntity<*> = result as ResponseEntity<*>
		logger.info("响应 -> 状态码: ${responseEntity.statusCode} 内容: ${responseEntity.body}")
	}

	private fun argsToString(args: Array<out Any>?): String {
		val buffer: StringBuffer = StringBuffer()
		if (null != args && args.isNotEmpty()) {
			for (arg: Any? in args) {
				if (null == arg) {
					buffer.append("null").append(',')
				} else {
					buffer.append(arg).append(',')
				}
			}
		}
		return buffer.deleteCharAt(buffer.length - 1).toString()
	}
}