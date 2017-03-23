package com.crazyit.foundation.employee.data

import com.crazyit.foundation.employee.domain.Employee
import com.crazyit.foundation.employee.domain.EmployeeAuth
import java.text.SimpleDateFormat

/**
 * @author CrazyApeDX
 * Created on 2017/3/1.
 */
data class ClientEmployee(
	var id: Long,
	var username: String,
	var name: String,
    var roleTitle: String,
    var headImageUri: String,
    var sex: String,
    var birthday: String,
    var status: String
) {

	companion object {

		val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

		fun init(employee: Employee, auth: EmployeeAuth): ClientEmployee {
			return ClientEmployee(
				id = employee.id,
				username = auth.username,
				name = employee.name,
				roleTitle = employee.role.title!!,
				headImageUri = employee.headImageUri,
				sex = employee.sex.toString(),
				birthday = dateFormat.format(employee.birthday),
				status = employee.status.toString()
			)
		}
	}
}