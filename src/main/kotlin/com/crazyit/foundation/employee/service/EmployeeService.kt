package com.crazyit.foundation.employee.service

import com.crazyit.core.constant.enum.EmployeeStatus
import com.crazyit.core.constant.enum.OrderType
import com.crazyit.core.constant.enum.Sex
import org.springframework.http.ResponseEntity

/**
 * @author CrazyApeDX
 * Created on 2017/2/28.
 */
interface EmployeeService {

	fun login(username: String, password: String): ResponseEntity<String>

	fun register(username: String, password: String, name: String, roleId: Long): ResponseEntity<String>

	fun remove(id: Long): ResponseEntity<String>

	fun load(id: Long): ResponseEntity<String>

	fun loadPage(keywords: String?, orderType: OrderType?,
				 orderProperty: String?, roleId: Long?, sex: Sex?,
				 status: EmployeeStatus?, page: Int, size: Int): ResponseEntity<*>

}