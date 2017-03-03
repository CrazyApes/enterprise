package com.crazyit.foundation.role.dao

import com.crazyit.core.app.AppRepo
import com.crazyit.foundation.role.domain.Role
import org.springframework.stereotype.Repository

/**
 * @author CrazyApeDX
 * Created on 2017/2/24.
 */
@Repository
interface RoleRepo : AppRepo<Role> {

	fun countByTitle(title: String): Long
}