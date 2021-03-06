package com.crazyit.foundation.customer.domain

import com.crazyit.core.app.AppEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

/**
 * @author CrazyApeDX
 * Created on 2017/2/26.
 */
@Entity
@Table(name = "CUSTOMER")
open class Customer(

    // 客户姓名
	@Column(length = 10,  nullable = false)
	var name: String? = null,

	// 客户手机号码
	@Column(length = 11,  nullable = false, unique = true)
    var mobile: String? = null,

    // 客户地址
    @Column(length = 80, nullable = false)
    var address: String? = null,

    // 客户传真
    @Column(length = 15, unique = true)
    var fax: String? = null,

	// 总消费额（单位：分）
	@Column(nullable = false)
	var totalConsume: Long = 0L,

    // 价目表头ID
    var priceHeadId: Long? = null

) : AppEntity() {

	constructor(): this(null, null, null, null, 0L, null)

	companion object {

		/**
		 * 验证客户端输入的姓名是否符合格式的方法
		 * 格式：位数为2~5位，只能输入中文
		 */
		fun validateNamePattern(name: String): Boolean {
			return name.matches(Regex("^[\\u4e00-\\u9fa5]{2,5}$"))
		}

		/**
		 * 验证客户端输入的手机号码是否符合格式的方法
		 * 格式：以1开头的11位数字
		 */
		fun validateMobilePattern(mobile: String): Boolean {
			return mobile.matches(Regex("^1[0-9]{10}$"))
		}
	}
}