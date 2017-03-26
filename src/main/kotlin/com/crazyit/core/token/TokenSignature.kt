package com.crazyit.core.token

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
open class TokenSignature(
	// 加密密钥
	var secret: ByteArray = "crazyit".toByteArray()
)