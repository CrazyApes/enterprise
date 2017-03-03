package com.crazyit.foundation.employee.provider

import com.crazyit.core.app.AppProvider
import com.crazyit.foundation.employee.domain.Employee
import com.crazyit.foundation.employee.domain.EmployeeAuth
import com.crazyit.foundation.employee.query.EmployeeQuery
import org.springframework.data.domain.Page

/**
 * @author CrazyApeDX
 * Created on 2017/2/28.
 */
interface EmployeePorvider: AppProvider {

	fun create(username: String, password: String, name: String, roleId: Long): Employee

	fun remove(id: Long)

	fun modify(employee: Employee): Employee

	fun load(id: Long): Employee?

	fun loadAll(query: EmployeeQuery, page: Int, size: Int): Page<Employee>

	fun existsByUsername(username: String): Boolean

	fun loadAuthByUsername(username: String): EmployeeAuth?

	fun loadAuthByEmployeeId(employeeId: Long): EmployeeAuth?
}