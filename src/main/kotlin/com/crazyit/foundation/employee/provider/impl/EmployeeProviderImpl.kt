package com.crazyit.foundation.employee.provider.impl

import com.crazyit.core.util.Security
import com.crazyit.core.exception.InvalidDataException
import com.crazyit.core.exception.MismatchingDataException
import com.crazyit.core.exception.RepeatDataException
import com.crazyit.foundation.employee.dao.EmployeeAuthRepo
import com.crazyit.foundation.employee.dao.EmployeeRepo
import com.crazyit.foundation.employee.domain.Employee
import com.crazyit.foundation.employee.domain.EmployeeAuth
import com.crazyit.foundation.employee.provider.EmployeePorvider
import com.crazyit.foundation.employee.query.EmployeeQuery
import com.crazyit.foundation.role.dao.RoleRepo
import com.crazyit.foundation.role.domain.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
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
    @Autowired val employeeAuthRepo: EmployeeAuthRepo,
    @Autowired val roleRepo: RoleRepo
) : EmployeePorvider {

	/**
	 * 新建用户的方法，也是快速注册用户的方法
	 * @param username 新用户的登录用户名
	 * @param password 新用户的登录密码（已加密）
	 * @param name 新用户的真实姓名
	 * @param roleId 新用户的角色id
	 * @return 注册后的新用户实体模型对象
	 * @throws InvalidDataException 在以下几种情况下抛出
	 *      ① 用户名未填写
	 *      ② 用户名长度超过20位
	 *      ③ 密码未填写
	 *      ④ 密码长度超过36位
	 *      ⑤ 姓名未填写
	 *      ⑥ 姓名长度超过5位
	 *      ⑦ 未选择角色（roleId = 0L）
	 * @throws RepeatDataException 客户端键入的用户名已经被注册时抛出
	 * @throws MismatchingDataException 指定roleId的角色信息在数据库中不存在时抛出
	 */
	override fun create(username: String, password: String, name: String, roleId: Long): Employee {
		if ("" == username) {
			throw InvalidDataException(
				message = "EmployeeAuth(username = $username)用户名未填写",
				notice = "请填写您的用户名")
		} else if (username.length > 20) {
			throw InvalidDataException(
				message = "EmployeeAuth(username = $username)用户名长度超过20位",
				notice = "用户名不能超过20位")
		} else if ("" == password) {
			throw InvalidDataException(
				message = "EmployeeAuth(password = $password)密码未填写",
				notice = "请填写您的密码")
		} else if (password.length > 36) {
			throw InvalidDataException(
				message = "EmployeeAuth(password = $password)密码长度超过36位",
				notice = "密码不能超过36位")
		} else if ("" == name) {
			throw InvalidDataException(
				message = "Employee(name = $name)姓名未填写",
				notice = "请填写您的姓名")
		} else if (name.length > 5) {
			throw InvalidDataException(
				message = "Employee(name = $name)姓名长度超过5位",
				notice = "姓名不能超过5位")
		} else if (0L == roleId) {
			throw InvalidDataException(
				message = "Employee(role = role(id = $roleId))未选择角色",
				notice = "请选择您的角色")
		} else if (this.existsByUsername(username)) {
			throw RepeatDataException(
				message = "EmployeeAuth(username = $username)数据已经存在",
				notice = "用户名 $username 已经被注册，请更换您的用户名")
		} else {
			val role: Role? = this.roleRepo.findOne(roleId)
			if (null == role) {
				throw MismatchingDataException(
					message = "Role(id = $roleId)数据不存在",
					notice = "加载角色信息失败，请稍后重试或联系管理员"
				)
			} else {
				val employee: Employee = this.employeeRepo.save(Employee(
					name = name,
					role = role
				))
				this.employeeAuthRepo.save(EmployeeAuth(
					employeeId = employee.id,
					username = username,
					password = Security.encodeMD5(password)
				))
				return employee
			}
		}
	}

	/**
	 * 删除指定id的员工的方法
	 * @param id 指定员工的id
	 */
	override fun remove(id: Long) {
		this.employeeRepo.delete(id)
		this.employeeAuthRepo.deleteByEmployeeId(id)
	}

	override fun modify(id: Long, name: String?, roleId: Long?, password: String?): Employee {
		val employee = this.employeeRepo.findOne(id) ?: throw MismatchingDataException(
			message = "Employee(id = $id)数据不存在",
			notice = "加载员工基础信息失败，请稍后重试或联系管理员")
		if (null != name && "" != name) {
			if (name.length > 5) throw InvalidDataException(
					message = "Employee(name = $name)姓名长度超过5位",
					notice = "姓名不能超过5位")
			else employee.name = name
		}
		if (null !== roleId && 0L != roleId) {
			val role = this.roleRepo.findOne(roleId) ?: throw MismatchingDataException(
				message = "Role(id = $roleId)数据不存在",
				notice = "加载角色信息失败，请稍后重试或联系管理员"
			)
			employee.role = role
		}
		if (null != password && "" != password) {
			val auth = this.employeeAuthRepo.findByEmployeeId(employee.id) ?: throw MismatchingDataException(
				message = "EmployeeAuth(employeeId = ${employee.id})数据不存在",
				notice = "加载员工安全信息失败，请稍后重试或联系管理员")
			if (password.length > 36) throw InvalidDataException(
				message = "EmployeeAuth(password = $password)密码长度超过36位",
				notice = "密码不能超过36位")
			else auth.password = password
			this.employeeAuthRepo.save(auth)
		}
		return this.employeeRepo.save(employee)
	}

	/**
	 * 查询指定id的员工数据的方法
	 * @param id 客户端键入的id信息
	 * @return 指定id的员工信息实体模型
	 */
	override fun load(id: Long): Employee? {
		return this.employeeRepo.findOne(id)
	}

	/**
	 * 动态条件查询员工数据并分页的方法
	 * @param query 动态查询条件对象，封装员工实体模型动态查询的所有条件
	 * @param page 当前页码
	 * @param size 每页数据量
	 * @return 指定查询条件下的员工分页对象
	 */
	override fun loadPage(query: EmployeeQuery, page: Int, size: Int): Page<Employee> {
		return this.employeeRepo.findAll(query.getCondition(), this.initPage(page, size))
	}

	/**
	 * 判断客户端键入的用户名是否已经被注册的方法
	 * @param username 客户端键入的用户名
	 * @return 验证结果，如果被注册，返回true，反之返回false
	 */
	override fun existsByUsername(username: String): Boolean {
		return this.employeeAuthRepo.countByUsername(username) > 0L
	}

	/**
	 * 根据用户名查询指定的员工安全信息
	 * @param username 客户端键入的用户名
	 * @return 指定用户名的员工安全实体模型对象，查询结果可能为null
	 */
	override fun loadAuthByUsername(username: String): EmployeeAuth? {
		return this.employeeAuthRepo.findByUsername(username)
	}

	/**
	 * 根据员工id查找指定的用户安全信息
	 * @param employeeId 对应的员工信息的id
	 * @return 指定用户名的员工安全实体模型对象，查询结果可能为null
	 */
	override fun loadAuthByEmployeeId(employeeId: Long): EmployeeAuth? {
		return this.employeeAuthRepo.findByEmployeeId(employeeId)
	}
}