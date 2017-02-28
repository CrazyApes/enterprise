package com.crazyit.foundation.employee.provider

import com.crazyit.foundation.employee.domain.EmployeeAuth

/**
 * @author CrazyApeDX
 * Created on 2017/2/28.
 */
interface EmployeePorvider {

	fun existsByUsername(username: String): Boolean

	fun loadByUsername(username: String): EmployeeAuth?

	fun quicklyRegister(username: String, password: String, name: String, roleId: Long)

}