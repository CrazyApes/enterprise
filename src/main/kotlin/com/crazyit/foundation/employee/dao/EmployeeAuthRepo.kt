package com.crazyit.foundation.employee.dao

import com.crazyit.foundation.AppRepo
import com.crazyit.foundation.employee.domain.EmployeeAuth
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