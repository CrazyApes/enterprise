package com.crazyit.core.token

import com.crazyit.core.constant.enum.UserStatus
import java.util.*

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
data class TokenPayload(

	// 用户的id
	var id: Long,

	// 用户的姓名
	var name: String,

	// 用户角色id
	var roleId: Long,

	// 用户状态
	var status: UserStatus,

	// 过期时间（默认为1天后）
	var expired: () -> Long = {
		val instance: Calendar = Calendar.getInstance()
		instance.add(Calendar.DAY_OF_MONTH, 1)
		instance.time.time
	}
)