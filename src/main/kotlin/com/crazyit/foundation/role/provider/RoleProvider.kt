package com.crazyit.foundation.role.provider

import com.crazyit.foundation.role.domain.Role


/**
 * @author CrazyApeDX
 * Created on 2017/2/28.
 */
interface RoleProvider {

	fun create(title: String): Role

	fun remove(id: Long)

	fun modify(role: Role)

	fun loadOne(id: Long): Role

	fun existsByTitle(title: String): Boolean
}