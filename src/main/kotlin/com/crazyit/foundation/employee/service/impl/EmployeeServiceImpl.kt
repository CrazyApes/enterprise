package com.crazyit.foundation.employee.service.impl

import com.crazyit.core.constant.enum.EmployeeStatus
import com.crazyit.core.constant.enum.OrderType
import com.crazyit.core.constant.enum.Sex
import com.crazyit.core.util.Security
import com.crazyit.foundation.employee.data.ClientEmployee
import com.crazyit.foundation.employee.data.LoginEmployee
import com.crazyit.foundation.employee.domain.Employee
import com.crazyit.foundation.employee.provider.EmployeeProvider
import com.crazyit.foundation.employee.query.EmployeeQuery
import com.crazyit.foundation.role.domain.Role
import com.crazyit.foundation.role.provider.RoleProvider
import com.crazyit.foundation.employee.service.EmployeeService
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author CrazyApeDX
 * Created on 2017/3/1.
 */
@Service
@Transactional
open class EmployeeServiceImpl(
		@Autowired var gson: Gson,
		@Autowired var roleProvider: RoleProvider,
		@Autowired var employeeProvider: EmployeeProvider
) : EmployeeService {

	override fun login(username: String, password: String): ResponseEntity<String> {
		val auth = this.employeeProvider.loadAuthByUsername(username)
		if (null == auth) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(gson.toJson("该用户名未注册"))
		} else if (auth.password != Security.encodeMD5(password)) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(gson.toJson("您输入的密码不正确"))
		} else {
			val employee = this.employeeProvider.load(auth.employeeId)
			if (null == employee) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(gson.toJson("加载账号信息失败，请稍后重试或联系管理员"))
			} else {
				if (EmployeeStatus.INACTIVE == employee.status) {
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body(gson.toJson("该账号的用户已经离职，无法继续使用"))
				} else {
					return ResponseEntity.status(HttpStatus.CREATED).body(gson.toJson(LoginEmployee.init(employee)))
				}
			}
		}
	}

	override fun register(username: String, password: String, name: String, roleId: Long): ResponseEntity<String> {
		if (!Employee.validateUsernamePattern(username)) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body("您填写的用户名格式错误（格式为：位数为10~20位，必须包含大写字母，此外还可以输入小写字母和数字）")
		} else if (!Employee.validatePasswordPattern(password)) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body("您填写的密码格式错误（格式为：位数为8~16位，必须包含字母和数字，此外还可以输入~、#、_、.）")
		} else if (!Employee.validateNamePattern(name)) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body("您填写的姓名格式错误（格式为：位数为2~5位，只能输入中文）")
		} else if (0L == roleId) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body("请选择员工角色")
		} else if (this.employeeProvider.existsByUsername(username)) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body("用户名 $username 已经被注册，请更换用户名后重试")
		} else {
			this.employeeProvider.create(
				username = username,
				password = password,
				name = name,
				roleId = roleId
			)
			return ResponseEntity.status(HttpStatus.CREATED).body(null)
		}
	}

	override fun remove(id: Long): ResponseEntity<String> {
		this.employeeProvider.remove(id)
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
	}

	override fun load(id: Long): ResponseEntity<String> {
		val employee = this.employeeProvider.load(id)
		val employeeAuth = this.employeeProvider.loadAuthByEmployeeId(id)
		if (null == employee || null == employeeAuth) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gson.toJson("很遗憾...我们没有这条数据"))
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(
				gson.toJson(ClientEmployee.init(
					employee = employee,
					auth = employeeAuth
				))
			)
		}
	}

	override fun loadPage(keywords: String?, orderType: OrderType?,
						  orderProperty: String?, roleId: Long?, sex: Sex?,
						  status: EmployeeStatus?, page: Int, size: Int): ResponseEntity<String> {
		val role: Role?
		if (null == roleId || roleId == 0L) {
			role = null
		} else {
			role = this.roleProvider.load(roleId)
		}
		val query = EmployeeQuery(
			keywords = keywords, orderType = orderType,
			orderProperty = orderProperty, role = role, sex = sex,
			status = status)
		val employeePage: Page<Employee> = this.employeeProvider.loadPage(query = query, page = page, size = size)
		return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(employeePage))
	}

}