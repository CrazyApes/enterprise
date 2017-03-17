package com.crazyit.external.service.role.impl

import com.crazyit.core.constant.enum.OrderType
import com.crazyit.foundation.role.domain.Role
import com.crazyit.foundation.role.provider.RoleProvider
import com.crazyit.foundation.role.query.RoleQuery
import com.crazyit.external.service.role.RoleService
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author CrazyApeDX
 * Created on 2017/3/4.
 */
@Service
@Transactional
open class RoleServiceImpl(
	@Autowired var gson: Gson,
	@Autowired var roleProvider: RoleProvider
) : RoleService {

	override fun create(title: String): ResponseEntity<String> {
		if (!Role.validateTitlePattern(title)) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body("您填写的角色标题格式错误（格式为：位数为4~10位，只能输入中文）")
		} else {
			this.roleProvider.create(title)
			return ResponseEntity.status(HttpStatus.CREATED).body(null)
		}
	}

	override fun remove(id: Long): ResponseEntity<String> {
		this.roleProvider.remove(id)
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null)
	}

	override fun load(id: Long): ResponseEntity<String> {
		val role = this.roleProvider.load(id)
		if (null == role) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("很遗憾...我们没有这条数据")
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(role))
		}
	}

	override fun loadPage(keywords: String?, orderType: OrderType?,
	                      orderProperty: String?, page: Int, size: Int): ResponseEntity<String> {
		val rolePage = this.roleProvider.loadPage(
			RoleQuery(
				keywords = keywords,
				orderType = orderType,
				orderProperty = orderProperty
			), page, size)
		return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(rolePage))
	}
}