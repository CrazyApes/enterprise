package com.crazyit.foundation.employee.provider

import com.crazyit.core.app.AppProvider
import com.crazyit.core.app.AppProviderImpl
import com.crazyit.core.constant.enum.EmployeeStatus
import com.crazyit.core.constant.enum.Sex
import com.crazyit.foundation.employee.domain.Employee
import com.crazyit.foundation.employee.domain.EmployeeAuth
import com.crazyit.foundation.employee.query.EmployeeQuery
import org.springframework.data.domain.Page
import java.util.*

/**
 * @author CrazyApeDX
 * Created on 2017/2/28.
 */
interface EmployeeProvider : AppProvider<Employee> {

	fun create(username: String, password: String, name: String, roleId: Long): Employee

	fun modify(id: Long, name: String?, roleId: Long?, password: String?, headImageUri: String?,
	           sex: Sex?, birthday: Date?, status: EmployeeStatus?): Employee

	fun loadPage(query: EmployeeQuery, page: Int, size: Int): Page<Employee>

	fun existsByUsername(username: String): Boolean

	fun loadAuthByUsername(username: String): EmployeeAuth?

	fun loadAuthByEmployeeId(employeeId: Long): EmployeeAuth?
}