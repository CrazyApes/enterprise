package com.crazyit.foundation.employee.data

import com.crazyit.core.constant.enum.EmployeeStatus
import com.crazyit.core.constant.enum.Sex
import com.crazyit.core.token.Token
import com.crazyit.core.token.TokenPayload
import com.crazyit.foundation.employee.domain.Employee
import java.text.SimpleDateFormat

/**
 * @author CrazyApeDX
 * Created on 2017/3/1.
 */
data class LoginEmployee(
	var token: String,
	var id: Long,
	var roleTitle: String,
	var name: String,
	var headImageUri: String,
	var sex: String,
	var birthday: String,
	var status: String
) {
	companion object {

		val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

		fun init(employee: Employee): LoginEmployee {
			val sex = when (employee.sex) {
				Sex.MALE -> "男"
				Sex.FEMALE -> "女"
				else -> "保密"
			}
			val status = when (employee.status) {
				EmployeeStatus.ACTIVE -> "在职"
				else -> "离职"
			}
			return LoginEmployee(
				token = this.createToken(employee),
				id = employee.id,
				roleTitle = employee.role.title!!,
				name = employee.name,
				headImageUri = employee.headImageUri,
				sex = sex,
				birthday = dateFormat.format(employee.birthday),
				status = status
			)

		}

		private fun createToken(employee: Employee): String {
			return Token(
				payLoad = TokenPayload(
					id = employee.id,
					name = employee.name,
					roleId = employee.role.id,
					status = employee.status
				)
			).toString()
		}
	}
}