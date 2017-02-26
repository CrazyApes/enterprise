package com.crazyit.foundation.entity

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
	var name: String,

	// 客户手机号码
	@Column(length = 11,  nullable = false, unique = true)
    var mobile: String,

    // 客户地址
    @Column(length = 80, nullable = false)
    var address: String,

    // 客户传真
    @Column(length = 15, unique = true)
    var fax: String?,

	// 总消费额（单位：分）
	@Column(nullable = false)
	var totalConsume: Long = 0L,

    // 价目表头ID
    var priceHeadId: Long

) : AppEntity()