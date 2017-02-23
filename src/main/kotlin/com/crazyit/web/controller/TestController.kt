package com.crazyit.web.controller

import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
 * @author CrazyApeDX
 * Created on 2017/2/22.
 */
@RestController
@RequestMapping(value = "/test", produces = arrayOf("application/json;charset=utf-8"))
open class TestController {

	@ApiOperation(value = "测试的接口", notes = "就这样吧")
	@GetMapping
	fun testGet(request: HttpServletRequest): ResponseEntity<*> {
		return ResponseEntity.ok("OK")
	}
}