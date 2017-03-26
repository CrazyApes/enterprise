package com.crazyit.foundation.customer.service

import com.crazyit.core.constant.enum.OrderType
import com.crazyit.foundation.customer.domain.Customer
import org.springframework.http.ResponseEntity

/**
 * @author CrazyApeDX
 * Created on 2017/3/17.
 */
interface CustomerService {

	fun create(name: String, mobile: String, address: String, fax: String?): ResponseEntity<String>

	fun loadOne(id: Long): ResponseEntity<String>

	fun loadPage(keywords: String?, orderType: OrderType?, orderProperty: String?,
                 page: Int, size: Int): ResponseEntity<*>
}