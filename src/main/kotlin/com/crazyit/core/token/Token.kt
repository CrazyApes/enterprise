package com.crazyit.core.token

import com.crazyit.core.constant.Security
import com.google.gson.Gson

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
class Token(
	var head: TokenHead = TokenHead(),
	var payLoad: TokenPayload,
	var signature: TokenSignature = TokenSignature()
) {

	override fun toString(): String {
		val buffer: StringBuffer = StringBuffer()
		val gson: Gson = Gson()

		val headJson = gson.toJson(this.head)
		val payLoadJson = gson.toJson(this.payLoad)

		buffer.append(Security.encodeBase64(headJson))
			.append('.')
			.append(Security.encodeBase64(payLoadJson))
			.append('.')
			.append(Security.encodeHS256(
				code = "${Security.encodeBase64(headJson)}.${Security.encodeBase64(payLoadJson)}",
				secret = this.signature.secret
			))

		return buffer.toString()
	}
}