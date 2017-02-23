package com.crazyit.foundation.repo

import com.crazyit.foundation.entity.UserAuth
import org.springframework.stereotype.Repository

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
@Repository
interface UserAuthRepo : AppRepo<UserAuth> {

	fun findByUsername(username: String): UserAuth?

	fun countByUsername(username: String): Long
	fun countByEmail(email: String): Long
	fun countByMobile(mobile: String): Long
}