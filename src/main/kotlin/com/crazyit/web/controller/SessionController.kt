package com.crazyit.web.controller

import com.crazyit.foundation.employee.data.LoginEmployee
import com.crazyit.service.employee.EmployeeService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author CrazyApeDX
 * Created on 2017/3/1.
 */
@Api(value = "Session API", description = "登录（授权）操作接口")
@RestController
@RequestMapping(value = "/service/session", produces = arrayOf("application/json;charset=utf-8"))
open class SessionController(
	@Autowired var employeeService: EmployeeService
) {

	@ApiOperation(value = "登录（授权）", notes = "登录（授权）接口，返回授权token", response = LoginEmployee::class)
	@ApiImplicitParams(
		ApiImplicitParam(name = "username", required = true, dataType = "String",
			value = "用户名（格式为：位数为10~20位，必须包含大写字母，此外还可以输入小写字母和数字）"),
		ApiImplicitParam(name = "password", required = true, dataType = "String",
			value = "密码（格式为：位数为8~16位，必须包含字母和数字，此外还可以输入~、#、_、.）")
	)
	@PostMapping
	fun sessionPost(username: String, password: String): ResponseEntity<String> {
		return this.employeeService.login(username, password)
	}
}