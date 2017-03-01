package com.crazyit.service.employee

import org.springframework.http.ResponseEntity

/**
 * @author CrazyApeDX
 * Created on 2017/2/28.
 */
interface EmployeeService {

	fun login(username: String, password: String): ResponseEntity<String>

	fun register(username: String, password: String, name: String, roleId: Long): ResponseEntity<String>

	fun load(id: Long): ResponseEntity<String>

	fun remove(id: Long): ResponseEntity<String>

}