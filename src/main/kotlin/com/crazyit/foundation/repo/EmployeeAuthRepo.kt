package com.crazyit.foundation.repo

import com.crazyit.foundation.entity.EmployeeAuth
import org.springframework.stereotype.Repository

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
@Repository
interface EmployeeAuthRepo : AppRepo<EmployeeAuth> {

	fun findByUsername(username: String): EmployeeAuth?

	fun countByUsername(username: String): Long
}