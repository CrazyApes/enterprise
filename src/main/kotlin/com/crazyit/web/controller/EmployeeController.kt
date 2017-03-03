package com.crazyit.web.controller

import com.crazyit.foundation.employee.data.ClientEmployee
import com.crazyit.service.employee.EmployeeService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * 员工相关CRUD Restful控制器
 * @author CrazyApeDX
 * Created on 2017/3/1.
 */
@Api(value = "Employee API", description = "员工相关接口控制器")
@RestController
@RequestMapping(value = "/employees", produces = arrayOf("application/json;charset=utf-8"))
open class EmployeeController(
	@Autowired var employeeService: EmployeeService
) {

	/**
	 *
	 */
	@ApiOperation(value = "查询指定ID的员工信息", notes = "查询指定ID的员工信息的接口", response = ClientEmployee::class)
	@ApiImplicitParam(name = "id", required = true, dataType = "Long",
		value = "员工ID（任何非负整数）")
	@GetMapping(value = "/{id}")
	fun employeeIdGet(@PathVariable("id") id: Long): ResponseEntity<String> {
		return this.employeeService.load(id)
	}

	/**
	 *
	 */
	@ApiOperation(value = "新增员工", notes = "提供用户名、密码、姓名和角色ID来快速注册员工")
	@ApiImplicitParams(
		ApiImplicitParam(
			name = "username", required = true, dataType = "String",
			value = "用户名（格式为：位数为10~20位，必须包含大写字母，此外还可以输入小写字母和数字）"),
		ApiImplicitParam(
			name = "password", required = true, dataType = "String",
			value = "密码（格式为：位数为8~16位，必须包含字母和数字，此外还可以输入~、#、_、.）"),
		ApiImplicitParam(name = "name", required = true, dataType = "String",
			value = "姓名（格式为：位数为2~5位，只能输入中文）"),
		ApiImplicitParam(name = "roleId", required = true, dataType = "Long",
			value = "角色ID"))
	@PostMapping
	fun employeePost(username: String, password: String, name: String, roleId: Long): ResponseEntity<String> {
		return this.employeeService.register(
			username = username,
			password = password,
			name = name,
			roleId = roleId
		)
	}

	/**
	 *
	 */
	@ApiOperation(value = "删除员工", notes = "删除指定ID的员工")
	@ApiImplicitParam(name = "id", required = true, dataType = "Long", value = "员工ID")
	@DeleteMapping(value = "/{id}")
	fun employeeIdDelete(@PathVariable("id") id: Long): ResponseEntity<String> {
		return this.employeeService.remove(id)
	}

	/**
	 *
	 */
//	@PutMapping
//	fun employeePut() {}
}