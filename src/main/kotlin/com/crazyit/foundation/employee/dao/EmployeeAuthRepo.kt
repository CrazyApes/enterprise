package com.crazyit.foundation.employee.dao

import com.crazyit.foundation.app.dao.AppRepo
import com.crazyit.foundation.employee.domain.EmployeeAuth
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
@Repository
interface EmployeeAuthRepo : AppRepo<EmployeeAuth> {

	@Query("delete from EmployeeAuth auth where auth.employeeId = :employeeId")
	@Modifying
	fun deleteByEmployeeId(@Param("employeeId") employeeId: Long)

	fun findByEmployeeId(employeeId: Long): EmployeeAuth?

	fun findByUsername(username: String): EmployeeAuth?

	fun countByUsername(username: String): Long
}