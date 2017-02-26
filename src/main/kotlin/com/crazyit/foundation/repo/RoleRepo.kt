package com.crazyit.foundation.repo

import com.crazyit.foundation.entity.Role
import org.springframework.stereotype.Repository

/**
 * @author CrazyApeDX
 * Created on 2017/2/24.
 */
@Repository
interface RoleRepo : AppRepo<Role> {

	fun countByTitle(title: String): Long
}