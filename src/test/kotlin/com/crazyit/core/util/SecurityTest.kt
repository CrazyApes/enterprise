package com.crazyit.core.util

import com.crazyit.EnterpriseApplication
import com.crazyit.core.util.Security
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author CrazyApeDX
 * Created on 2017/2/24.
 */
@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = arrayOf(EnterpriseApplication::class))
@ActiveProfiles("dev")
open class SecurityTest {

	val origin: String = "This is my secret"
	val encodeBase64: String = "VGhpcyBpcyBteSBzZWNyZXQ="

	@Test
	fun encodeBase64() {
		val encode = Security.encodeBase64(origin)
		assert(encodeBase64 == encode)
	}

	@Test
	fun decodeBase64() {
		val encode = Security.decodeBase64(encodeBase64)
		assert(origin == encode)
	}
}