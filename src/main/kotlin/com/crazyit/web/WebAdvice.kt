package com.crazyit.web

import com.crazyit.core.exception.AppException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author CrazyApeDX
 * Created on 2017/3/1.
 */
@RestControllerAdvice
open class WebAdvice {

	@ExceptionHandler
	fun exceptionHandler(ex: Throwable): ResponseEntity<String> {
		if (ex is AppException) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.notice)
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("出现未知错误，请联系管理员")
		}
	}
}