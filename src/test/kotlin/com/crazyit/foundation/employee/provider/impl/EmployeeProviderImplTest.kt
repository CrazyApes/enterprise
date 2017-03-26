package com.crazyit.foundation.employee.provider.impl

import com.crazyit.foundation.employee.provider.EmployeeProvider
import com.crazyit.foundation.employee.query.EmployeeQuery
import com.crazyit.foundation.role.provider.RoleProvider
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.transaction.annotation.Transactional

/**
 * @author CrazyApeDX
 * *         Created on 2017/3/26.
 */
@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
@ActiveProfiles("dev")
@Transactional
@Rollback
class EmployeeProviderImplTest {

	@Autowired
	private var employeeProvider: EmployeeProvider? = null

	@Autowired
	private var roleProvider: RoleProvider? = null

	@Test
	fun create() {
		val username = "MyUsername"
		val password = "123456"
		val name = "开发者"
		val roleId = 1L
		val employee = this.employeeProvider!!.create(username, password, name, roleId)
		assert(employee.name == name)
		assert(employee.role!!.id == roleId)

	}

	@Test
	fun remove() {
		val username = "MyUsername"
		val password = "123456"
		val name = "开发者"
		val roleId = 1L
		val employee = this.employeeProvider!!.create(username, password, name, roleId)
		val id = employee.id
		this.employeeProvider!!.remove(id)
		val queryEmployee = this.employeeProvider!!.load(id)
		assert(null == queryEmployee)
	}

	@Test
	fun modify() {
	}

	@Test
	fun load() {
		val username = "MyUsername"
		val password = "123456"
		val name = "开发者"
		val roleId = 1L
		val employee = this.employeeProvider!!.create(username, password, name, roleId)
		val id = employee.id
		val queryEmployee = this.employeeProvider!!.load(id)!!
		assert(queryEmployee.name == name)
		assert(queryEmployee.role!!.id == roleId)
	}

	@Test
	fun loadPage() {
		val username = "MyUsername"
		val password = "123456"
		val name = "开发者"
		val roleId = 1L
		this.employeeProvider!!.create(username, password, name, roleId)
		val role = this.roleProvider!!.load(roleId)
		val employeePage = this.employeeProvider!!.loadPage(
			EmployeeQuery("开发者", null, null, role, null, null), 1, 10)
		assert(null != employeePage.content)
		assert(employeePage.content.size > 0)
		println(employeePage.content.size)
	}

	@Test
	fun existsByUsername() {
		val username = "MyUsername"
		val password = "123456"
		val name = "开发者"
		val roleId = 1L
		this.employeeProvider!!.create(username, password, name, roleId)
		assert(this.employeeProvider!!.existsByUsername(username))
	}

	@Test
	fun loadAuthByUsername() {
		val username = "MyUsername"
		val password = "123456"
		val name = "开发者"
		val roleId = 1L
		this.employeeProvider!!.create(username, password, name, roleId)
		val auth = this.employeeProvider!!.loadAuthByUsername(username)!!
		assert(auth.username == username)
	}

	@Test
	fun loadAuthByEmployeeId() {
		val username = "MyUsername"
		val password = "123456"
		val name = "开发者"
		val roleId = 1L
		val employee = this.employeeProvider!!.create(username, password, name, roleId)
		val auth = this.employeeProvider!!.loadAuthByEmployeeId(employee.id)!!
		assert(auth.username == username)
	}

}