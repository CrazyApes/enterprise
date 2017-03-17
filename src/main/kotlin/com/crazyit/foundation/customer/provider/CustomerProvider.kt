package com.crazyit.foundation.customer.provider

import com.crazyit.core.app.AppProvider
import com.crazyit.foundation.customer.domain.Customer
import com.crazyit.foundation.customer.query.CustomerQuery
import org.springframework.data.domain.Page

/**
 * @author CrazyApeDX
 * Created on 2017/3/17.
 */
interface CustomerProvider : AppProvider<Customer> {

	fun loadPage(query: CustomerQuery, page: Int, size: Int): Page<Customer>

	fun existsByMobile(mobile: String): Boolean

	fun existsByFax(fax: String): Boolean
}