package com.crazyit.foundation.customer.provider.impl

import com.crazyit.foundation.customer.domain.Customer
import com.crazyit.foundation.customer.provider.CustomerProvider
import com.crazyit.foundation.customer.query.CustomerQuery
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
@Transactional
@Rollback
@ActiveProfiles("dev")
class CustomerProviderImplTest {

	@Autowired
	private var customerProvider: CustomerProvider? = null

	@Test
	fun loadPage() {
		val customer = Customer(
			name = "经销商",
			mobile = "17774999992",
			address = "我的地盘就是这里",
			fax = "我的传真"
		)
		this.customerProvider!!.create(customer)
		val customerPage = this.customerProvider!!.loadPage(CustomerQuery("经销", null, null), 1, 20)
		assert(null != customerPage.content)
		assert(customerPage.content.size > 0)
	}

	@Test
	fun existsByMobile() {
		val customer = Customer(
			name = "经销商",
			mobile = "17774999992",
			address = "我的地盘就是这里",
			fax = "我的传真"
		)
		this.customerProvider!!.create(customer)
		assert(this.customerProvider!!.existsByMobile("17774999992"))
	}

	@Test
	fun existsByFax() {
		val customer = Customer(
			name = "经销商",
			mobile = "17774999992",
			address = "我的地盘就是这里",
			fax = "我的传真"
		)
		this.customerProvider!!.create(customer)
		assert(this.customerProvider!!.existsByFax("我的传真"))
	}

}