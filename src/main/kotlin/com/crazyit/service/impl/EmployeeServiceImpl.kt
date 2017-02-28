package com.crazyit.service.impl

import com.crazyit.core.constant.enum.UserStatus
import com.crazyit.core.exception.MismatchingDataException
import com.crazyit.core.exception.RepeatDataException
import com.crazyit.core.client.ClientUser
import com.crazyit.foundation.employee.domain.Role
import com.crazyit.foundation.employee.domain.Employee
import com.crazyit.foundation.employee.domain.EmployeeAuth
import com.crazyit.foundation.employee.dao.RoleRepo
import com.crazyit.foundation.employee.dao.EmployeeAuthRepo
import com.crazyit.foundation.employee.dao.EmployeeRepo
import com.crazyit.service.EmployeeService
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author CrazyApeDX
 * Created on 2017/2/23.
 */
@Service
@Transactional
open class EmployeeServiceImpl(
	// 注入公共gson对象
	@Autowired var gson: Gson,
	// 注入 Employee DAO 代理对象
	@Autowired var employeeRepo: EmployeeRepo,
	// 注入 EmployeeAuth DAO 代理对象
	@Autowired var employeeAuthRepo: EmployeeAuthRepo,
	// 注入 Role 服务对象
	@Autowired var roleRepo: RoleRepo
) : EmployeeService {

	/**
	 * 验证客户端输入的用户名是否已经被注册
	 */
	override fun existByUsername(username: String): Boolean {
		return this.employeeAuthRepo.countByUsername(username) > 0L
	}

	/**
	 * 登录服务:
	 *  1. 根据用户名查找 EmployeeAuth 对象，当查询结果不存在时表示客户端键入的用户名是不存在的
	 *  2. 用查询结果的密码与客户端键入的密码进行比较，如果不一致则表示客户端键入了错误的密码
	 *  3. 根据 EmployeeAuth 对象的 id 属性查询用户信息 Employee，如果查询结果不存在则抛出 MismatchingDataException
	 *  4. 校验用户是否已经失效
	 * @param username 客户端键入的用户名
	 * @param password 客户端键入的密码
	 * @return 包装完毕的响应对象 -> ResponseEntity
	 * 包含 HttpStatus（状态码）和 内容（包含用户基本信息和服务端验证Token的一个JSON对象）
	 * @throws MismatchingDataException 数据不匹配异常 -> 无法通过 EmployeeAuth 查询到对应的 Employee 时抛出
	 */
	override fun login(username: String, password: String): ResponseEntity<String> {

		val employeeAuth: EmployeeAuth? = this.employeeAuthRepo.findByUsername(username)
		if (null == employeeAuth) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("您输入的用户名不存在，请检查")
		} else {
			if (employeeAuth.password != password) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("您输入的密码不正确，请检查")
			}
			else {
				val employee: Employee? = this.employeeRepo.findOne(employeeAuth.userId)
				if (null == employee) {
					throw MismatchingDataException(
						message = "EmployeeAuth(id = ${employeeAuth.id})对应的User数据不存在",
						notice = "加载用户信息失败，请稍后重试或联系管理员")
				} else {
					if (employee.status == UserStatus.INACTIVE) {
						return ResponseEntity.status(HttpStatus.FORBIDDEN).body("指定账户已失效，请联系管理员")
					} else {
						val clientUser: ClientUser = ClientUser.init(employee)
						return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(clientUser))
					}
				}
			}
		}
	}

	/**
	 * 普遍的注册用户的方法 --> 在后台管理系统中添加一个新的用户
	 * @param operator 操作人 --> "谁"在执行添加用户的操作
	 * @param username 用户名 --> 新用户的用户名
	 * @param password 密码 --> 新用户的密码，这是一个已经被MD5加密后的字符串
	 * @param name 姓名 --> 新用户的姓名
	 * @param roleId 角色ID --> 新用户角色的ID {@link EmployeeAuth.id}
	 * @return 包装完毕的响应对象 -> ResponseEntity, 包含 HttpStatus（状态码）和 内容（操作成功时，内容为null）
	 * @throws RepeatDataException username 在数据库中已存在时抛出
	 * @throws MismatchingDataException 无法通过 roleId 查询到 EmployeeAuth 实体时抛出
	 */
	override fun simpleRegister(
		operator: Employee, username: String,
		password: String, name: String,
		roleId: Long): ResponseEntity<String> {

		if (this.existByUsername(username)) {
			throw RepeatDataException(
				message = "EmployeeAuth(username = $username)数据已存在，不能重复添加",
				notice = "用户名 $username 已经被注册，请更换用户名后重试"
			)
		} else {
			val role: Role? = roleRepo.findOne(roleId)
			if (null == role) {
				throw MismatchingDataException(
					message = "Role(id = $roleId)数据不存在",
					notice = "加载角色信息失败，请稍后重试或联系管理员")
			} else {
				val employee: Employee = this.employeeRepo.save(Employee(
					name = name,
					role = role))
				this.employeeAuthRepo.save(EmployeeAuth(
					userId = employee.id,
					username = username,
					password = password))
				return ResponseEntity.status(HttpStatus.OK).body(null)
			}
		}
	}
}