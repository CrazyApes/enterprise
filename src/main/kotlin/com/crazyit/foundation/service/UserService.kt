package com.crazyit.foundation.service

import com.crazyit.foundation.entity.User
import org.springframework.http.ResponseEntity

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
interface UserService : AppService<User> {

	fun login(username: String, password: String): ResponseEntity<String>
}