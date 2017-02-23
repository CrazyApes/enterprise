package com.crazyit.core.token

import org.apache.commons.codec.binary.Hex

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
class TokenSignature(
	// 加密密钥
	var secret: ByteArray = Hex.decodeHex(charArrayOf('c','r','z','a','y','i','t'))
)