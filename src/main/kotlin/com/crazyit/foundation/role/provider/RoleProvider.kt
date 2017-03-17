package com.crazyit.foundation.role.provider

import com.crazyit.core.app.AppProvider
import com.crazyit.core.app.AppProviderImpl
import com.crazyit.foundation.role.domain.Role
import com.crazyit.foundation.role.query.RoleQuery
import org.springframework.data.domain.Page


/**
 * @author CrazyApeDX
 * Created on 2017/2/28.
 */
interface RoleProvider : AppProvider<Role> {

	fun create(title: String): Role

	fun loadPage(query: RoleQuery, page: Int, size: Int): Page<Role>

	fun existsByTitle(title: String): Boolean
}