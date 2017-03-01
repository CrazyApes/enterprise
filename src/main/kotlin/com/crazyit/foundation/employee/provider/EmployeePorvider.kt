package com.crazyit.foundation.employee.provider

import com.crazyit.foundation.app.provider.AppProvider
import com.crazyit.foundation.employee.domain.Employee
import com.crazyit.foundation.employee.domain.EmployeeAuth

/**
 * @author CrazyApeDX
 * Created on 2017/2/28.
 */
interface EmployeePorvider: AppProvider {

	fun create(username: String, password: String, name: String, roleId: Long): Employee

	fun remove(id: Long)

	fun modify(employee: Employee): Employee

	fun load(id: Long): Employee?

	fun existsByUsername(username: String): Boolean

	fun loadAuthByUsername(username: String): EmployeeAuth?

	fun loadAuthByEmployeeId(employeeId: Long): EmployeeAuth?
}