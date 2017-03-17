package com.crazyit.foundation.customer.provider.impl

import com.crazyit.core.app.AppProviderImpl
import com.crazyit.foundation.customer.dao.CustomerRepo
import com.crazyit.foundation.customer.domain.Customer
import com.crazyit.foundation.customer.provider.CustomerProvider
import com.crazyit.foundation.customer.query.CustomerQuery
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @author CrazyApeDX
 * Created on 2017/3/17.
 */
@Service
@Transactional
open class CustomerProviderImpl(
	@Autowired var customerRepo: CustomerRepo
) : AppProviderImpl<Customer>(customerRepo), CustomerProvider {

	override fun loadPage(query: CustomerQuery, page: Int, size: Int): Page<Customer> {
		return this.customerRepo.findAll(query.getCondition(), this.initPage(page, size))
	}

	override fun existsByMobile(mobile: String): Boolean {
		return this.customerRepo.countByMobile(mobile) > 0
	}

	override fun existsByFax(fax: String): Boolean {
		return this.customerRepo.countByFax(fax) > 0
	}
}