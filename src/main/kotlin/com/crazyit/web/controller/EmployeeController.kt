package com.crazyit.web.controller

import com.crazyit.core.constant.enum.EmployeeStatus
import com.crazyit.core.constant.enum.OrderType
import com.crazyit.core.constant.enum.Sex
import com.crazyit.foundation.employee.data.ClientEmployee
import com.crazyit.foundation.employee.domain.Employee
import com.crazyit.service.employee.EmployeeService
import com.crazyit.service.pricetemplatenode.PriceTemplateNodeService
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
@Api(value = "Employee API", description = "员工相关API")
@RestController
@RequestMapping(value = "/employees", produces = arrayOf("application/json;charset=utf-8"))
open class EmployeeController(
	@Autowired var employeeService: EmployeeService
) {

	/**
	 *
	 */
	@ApiOperation(value = "条件查询员工（分页）", notes = "根据查询条件查询符合条件的员工列表，并且分页",
		responseContainer = "Page", response = Employee::class)
	@ApiImplicitParams(
		ApiImplicitParam(name = "keywords", required = false, dataType = "String",
			value = "关键词，会以模糊查询的方式去匹配用户的姓名，可以不填写"),
		ApiImplicitParam(name = "orderType", required = false, dataType = "String",
			value = "排序规则，由于在服务端对应的类型是enum，所以只能输入ASC（正序）和DESC（倒序），默认为ASC"),
		ApiImplicitParam(name = "orderProperty", required = false, dataType = "String",
			value = "排序字段，可以不填写，默认是id"),
		ApiImplicitParam(name = "roleId", required = false, dataType = "Long",
			value = "角色ID，在不限定的情况下可以不填写（传递null）或填写0"),
		ApiImplicitParam(name = "sex", required = false, dataType = "String",
			value = "性别，由于在服务器端对应的类型是enum，所以只能输入MALE（男）和FEMALE（女）和SECRET（保密），可以不填"),
		ApiImplicitParam(name = "status", required = false, dataType = "String",
			value = "用户状态，由于在服务器端对应的类型是enum，所以只能输入ACTIVE（在职）和INACTIVE（离职）"),
		ApiImplicitParam(name = "page", required = true, dataType = "Int",
			value = "分页页码，必须填写"),
		ApiImplicitParam(name = "size", required = true, dataType = "Int",
			value = "每页数据量，必须填写"))
	@GetMapping
	fun employeesGet(keywords: String?, orderType: OrderType?,
	                 orderProperty: String?, roleId: Long?, sex: Sex?,
	                 status: EmployeeStatus?, page: Int, size: Int ): ResponseEntity<String> {
		return this.employeeService.loadPage(
			keywords = keywords, orderType = orderType, orderProperty = orderProperty,
			roleId = roleId, sex = sex, status = status, page = page, size = size)
	}

	/**
	 *
	 */
	@ApiOperation(value = "查询指定ID的员工信息", notes = "查询指定ID的员工信息", response = ClientEmployee::class)
	@ApiImplicitParam(name = "id", required = true, dataType = "Long",
		value = "员工ID（任何正整数）")
	@GetMapping(value = "/{id}")
	fun employeesIdGet(@PathVariable("id") id: Long): ResponseEntity<String> {
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
	fun employeesPost(username: String, password: String, name: String, roleId: Long): ResponseEntity<String> {
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
	fun employeesIdDelete(@PathVariable("id") id: Long): ResponseEntity<String> {
		return this.employeeService.remove(id)
	}

	/**
	 *
	 */
//	@PutMapping
//	fun employeePut() {}
}