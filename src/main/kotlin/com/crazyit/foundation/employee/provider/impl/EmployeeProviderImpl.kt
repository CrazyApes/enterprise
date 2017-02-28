package com.crazyit.foundation.employee.provider.impl

import com.crazyit.foundation.employee.dao.EmployeeAuthRepo
import com.crazyit.foundation.employee.dao.EmployeeRepo
import com.crazyit.foundation.employee.domain.EmployeeAuth
import com.crazyit.foundation.employee.provider.EmployeePorvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author CrazyApeDX
 * Created on 2017/2/28.
 */
@Service
@Transactional
open class EmployeeProviderImpl(
	@Autowired val employeeRepo: EmployeeRepo,
    @Autowired val employeeAuthRepo: EmployeeAuthRepo
) : EmployeePorvider {

	override fun existsByUsername(username: String): Boolean {
		throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
	}

	override fun loadByUsername(username: String): EmployeeAuth? {
		return this.employeeAuthRepo.findByUsername(username)
	}

	override fun quicklyRegister(username: String, password: String, name: String, roleId: Long) {
		throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}