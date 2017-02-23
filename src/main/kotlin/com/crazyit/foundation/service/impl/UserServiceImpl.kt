package com.crazyit.foundation.service.impl

import com.crazyit.core.constant.enum.UserStatus
import com.crazyit.core.exception.MismatchingDataException
import com.crazyit.foundation.data.ClientUser
import com.crazyit.foundation.entity.User
import com.crazyit.foundation.entity.UserAuth
import com.crazyit.foundation.repo.UserAuthRepo
import com.crazyit.foundation.repo.UserRepo
import com.crazyit.foundation.service.UserService
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
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
open class UserServiceImpl(
	// 注入公共gson对象
	@Autowired var gson: Gson,
	// 注入 User DAO 代理对象
	@Autowired var userRepo: UserRepo,
	// 注入 UserAuth DAO 代理对象
    @Autowired var userAuthRepo: UserAuthRepo
) : UserService {

	override fun create(entity: User): User {
		if (0L == entity.id) {
			return this.userRepo.save(entity)
		} else {
			throw Exception()
		}
	}

	override fun remove(id: Long) {
		this.userRepo.delete(id)
	}

	override fun modify(entity: User): User {
		if (0L != entity.id) {
			return this.userRepo.save(entity)
		} else {
			throw Exception()
		}
	}

	override fun query(id: Long): User {
		return this.userRepo.findOne(id)
	}

	override fun queryAll(): List<User> {
		return this.userRepo.findAll()
	}

	override fun queryAll(page: Int, size: Int): Page<User> {
		return this.userRepo.findAll(this.initPage(page, size))
	}

	override fun queryAll(page: Int, size: Int, sort: Sort): Page<User> {
		return this.userRepo.findAll(this.initPage(page, size, sort))
	}

	override fun queryAll(page: Int, size: Int, direction: Sort.Direction, properties: String): Page<User> {
		return this.userRepo.findAll(this.initPage(page, size, direction, properties))
	}

	override fun countAll(): Long {
		return this.userRepo.count()
	}

	/**
	 * 登录服务:
	 *  1. 根据用户名查找 UserAuth 对象，当查询结果不存在时表示客户端键入的用户名是不存在的
	 *  2. 用查询结果的密码与客户端键入的密码进行比较，如果不一致则表示客户端键入了错误的密码
	 *  3. 根据 UserAuth 对象的 id 属性查询用户信息 User，如果查询结果不存在则抛出 MismatchingDataException
	 *  4. 校验用户是否已经失效
	 * @param username 客户端键入的用户名
	 * @param password 客户端键入的密码
	 * @return 包装完毕的响应对象 -> ResponseEntity
	 *      包含 HttpStatus（状态码）和 内容
	 *          内容为包含用户基本信息和服务端验证Token的一个JSON对象
	 * @throws MismatchingDataException 数据不匹配异常 -> 无法通过 UserAuth 查询到对应的 User 时抛出
	 */
	override fun login(username: String, password: String): ResponseEntity<String> {
		val userAuth: UserAuth? = this.userAuthRepo.findByUsername(username)
		if (null == userAuth) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("您输入的用户名不存在，请检查")
		} else {
			if (userAuth.password != password) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("您输入的密码不正确，请检查")
			}
			else {
				val user: User? = this.userRepo.findOne(userAuth.userId)
				if (null == user) {
					throw MismatchingDataException(
						message = "UserAuth(id = ${userAuth.id})对应的User数据不存在",
						notice = "加载用户信息失败，请稍后重试或联系管理员")
				} else {
					if (user.status == UserStatus.INACTIVE) {
						return ResponseEntity.status(HttpStatus.FORBIDDEN).body("指定账户已失效，请联系管理员")
					} else {
						val clientUser: ClientUser = ClientUser.init(user)
						return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(clientUser))
					}
				}
			}
		}
	}
}