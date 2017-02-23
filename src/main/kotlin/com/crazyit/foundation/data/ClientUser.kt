package com.crazyit.foundation.data

import com.crazyit.core.constant.enum.Sex
import com.crazyit.core.constant.enum.UserStatus
import com.crazyit.core.token.Token
import com.crazyit.core.token.TokenHead
import com.crazyit.core.token.TokenPayload
import com.crazyit.core.token.TokenSignature
import com.crazyit.foundation.entity.User
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

		fun init(user: User): ClientUser {
			return ClientUser(
				token = this.createToken(user),
				id = user.id,
				roleTitle = user.role.title,
				name = user.name,
				headImageUri = user.headImageUri,
				sex = {
					if (user.sex == Sex.MALE) "男"
					else if (user.sex == Sex.FEMALE) "女"
					else "保密"
				},
				birthday = dateFormat.format(user.birthday),
				status = {
					if (user.status == UserStatus.ACTIVE) "在职"
					else "离职"
				}
			)

		}

		private fun createToken(user: User): String {
			return Token(
				payLoad = TokenPayload(
					id = user.id,
					name = user.name,
					roleId = user.role.id,
					status = user.status
				)
			).toString()
		}
	}
}
