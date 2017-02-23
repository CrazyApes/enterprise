package com.crazyit.core.token

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
data class TokenHead(
	val alg: String = "HS256",
	val typ: String = "JWT"
)