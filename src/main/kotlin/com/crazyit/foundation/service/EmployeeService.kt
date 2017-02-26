package com.crazyit.foundation.service

import com.crazyit.foundation.entity.Employee
import org.springframework.http.ResponseEntity

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
interface EmployeeService : AppService {

	fun existByUsername(username: String): Boolean

	fun login(username: String, password: String): ResponseEntity<String>

	fun simpleRegister(
		operator: Employee, username: String,
		password: String, name: String,
		roleId: Long): ResponseEntity<String>
}