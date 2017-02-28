package com.crazyit.foundation.employee.dao

import com.crazyit.foundation.AppRepo
import com.crazyit.foundation.employee.domain.Role
import org.springframework.stereotype.Repository

/**
 * @author CrazyApeDX
 * Created on 2017/2/24.
 */
@Repository
interface RoleRepo : AppRepo<Role> {

	fun countByTitle(title: String): Long
}