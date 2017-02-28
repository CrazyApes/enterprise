package com.crazyit.foundation.employee.domain

import com.crazyit.foundation.AppEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * @author CrazyApeDX
 * Created on 2017/2/22.
 */
@Entity
@Table(name = "USER_AUTH")
open class EmployeeAuth(

	@Column(nullable = false, updatable = false, unique = true)
	var userId: Long,

	// 用户登录名
	@Column(length = 30, nullable = false, updatable = false, unique = true)
	var username: String,

	// 密码，以MD5加密的方式存储在数据库
	@Column(length = 36, nullable = false)
    var password: String

) : AppEntity() {

	// 用户邮箱（暂未开通）
	@Column(length = 40, unique = true)
	var email: String? = null

	// 用户手机号码（暂未开通）
	@Column(length = 11, unique = true)
	var mobile: String? = null

	// 用户QQ口令（暂未开通）
	@Column(length = 100, unique = true)
	var qqToken: String? = null

	// 用户支付宝口令（暂未开通）
	@Column(length = 100, unique = true)
	var alipayToken: String? = null

	// 用户微信口令（暂未开通）
	@Column(length = 100, unique = true)
	var wechatToken: String? = null

	// 用户微博口令（暂未开通）
	@Column(length = 100, unique = true)
	var sinaToken: String? = null
}