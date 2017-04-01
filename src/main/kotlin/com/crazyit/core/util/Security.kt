package com.crazyit.core.util

import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import javax.crypto.Mac
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
open class Security {

	companion object {

		fun encodeBase64(code: String): String {
			val encodeBytes = Base64.encodeBase64(code.toByteArray())
			return String(encodeBytes)
		}

		fun decodeBase64(code: String): String {
			val decodeBytes = Base64.decodeBase64(code.toByteArray())
			return String(decodeBytes)
		}

		fun encodeMD5(code: String): String {
			return DigestUtils.md5Hex(code)
		}

		fun encodeHS256(code: String, secret: ByteArray): String {
			val secretKeySpec: SecretKey = SecretKeySpec(secret, "HmacSHA256")
			val mac: Mac = Mac.getInstance(secretKeySpec.algorithm)
			mac.init(secretKeySpec)
			val encode: ByteArray = mac.doFinal(code.toByteArray())
			return Hex.encodeHexString(encode)
		}
	}
}