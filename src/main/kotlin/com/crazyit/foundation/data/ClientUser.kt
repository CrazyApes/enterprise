package com.crazyit.foundation.data

import com.crazyit.core.constant.enum.Sex
import com.crazyit.core.constant.enum.UserStatus
import com.crazyit.core.token.Token
import com.crazyit.core.token.TokenHead
import com.crazyit.core.token.TokenPayload
import com.crazyit.core.token.TokenSignature
import com.crazyit.foundation.entity.Employee
import java.text.SimpleDateFormat

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
data class ClientUser(
	var token: String,
	var id: Long,
	var roleTitle: String,
	var name: String,
	var headImageUri: String,
	var sex: () -> String,
	var birthday: String,
	var status: () -> String
) {
	companion object {

		val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

		fun init(employee: Employee): ClientUser {
			return ClientUser(
				token = this.createToken(employee),
				id = employee.id,
				roleTitle = employee.role.title,
				name = employee.name,
				headImageUri = employee.headImageUri,
				sex = {
					if (employee.sex == Sex.MALE) "男"
					else if (employee.sex == Sex.FEMALE) "女"
					else "保密"
				},
				birthday = dateFormat.format(employee.birthday),
				status = {
					if (employee.status == UserStatus.ACTIVE) "在职"
					else "离职"
				}
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
