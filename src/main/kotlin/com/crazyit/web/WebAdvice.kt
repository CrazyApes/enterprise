package com.crazyit.web

import com.crazyit.core.app.AppException
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

/**
 * @author CrazyApeDX
 * Created on 2017/3/1.
 */
@RestControllerAdvice
open class WebAdvice(
	@Autowired var gson: Gson
) {

	@ExceptionHandler
	fun exceptionHandler(ex: Throwable): ResponseEntity<String> {
		ex.printStackTrace()
		if (ex is AppException) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson(ex.notice))
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson("出现未知错误，请联系管理员"))
		}
	}
}