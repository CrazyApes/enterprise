package com.crazyit.foundation.employee.dao

import com.crazyit.foundation.app.dao.AppRepo
import com.crazyit.foundation.employee.domain.Employee
import com.crazyit.foundation.role.domain.Role
import org.springframework.stereotype.Repository

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
@Repository
interface EmployeeRepo : AppRepo<Employee> {

	fun countByRole(role: Role): Long

	fun countById(id: Long): Long
}