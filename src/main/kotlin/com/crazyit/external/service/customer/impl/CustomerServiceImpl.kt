package com.crazyit.external.service.customer.impl

import com.crazyit.core.constant.enum.OrderType
import com.crazyit.external.service.customer.CustomerService
import com.crazyit.foundation.customer.domain.Customer
import com.crazyit.foundation.customer.provider.CustomerProvider
import com.crazyit.foundation.customer.query.CustomerQuery
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author CrazyApeDX
 * Created on 2017/3/17.
 */
@Service
@Transactional
open class CustomerServiceImpl(
	@Autowired var gson: Gson,
	@Autowired var customerProvider: CustomerProvider
) : CustomerService {

	override fun create(name: String, mobile: String, address: String, fax: String?): ResponseEntity<String> {
		if(!Customer.validateNamePattern(name)) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body("您填写的姓名格式错误（格式为：位数为2~5位，只能输入中文）")
		} else if (!Customer.validateMobilePattern(mobile)) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body("您填写的手机号码格式不正确")
		} else if (address.length > 80) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body("地址的长度不能超过80位")
		} else if (null != fax && fax.length > 15) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
				.body("您填写的传真不正确")
		} else {
			this.customerProvider.create(Customer(
				name = name,
				mobile = mobile,
				address = address,
				fax = fax
			))
			return ResponseEntity.status(HttpStatus.OK).body(null)
		}
	}

	override fun loadOne(id: Long): ResponseEntity<String> {
		val customer = this.customerProvider.load(id)
		if (null == customer) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("很遗憾...我们没有这条数据")
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(customer))
		}
	}

	override fun loadPage(keywords: String?, orderType: OrderType?, orderProperty: String?, page: Int, size: Int): ResponseEntity<String> {
		val customerPage: Page<Customer>
		if (null == keywords && null == orderType && null == orderProperty) {
			customerPage = this.customerProvider.loadAll(page, size)
		} else {
			customerPage = this.customerProvider.loadPage(CustomerQuery(
				keywords = keywords,
				orderType = orderType,
				orderProperty = orderProperty
			), page, size)
		}
		return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(customerPage))
	}
}