package com.crazyit.foundation.customer.dao

import com.crazyit.core.app.AppRepo
import com.crazyit.foundation.customer.domain.Customer
import org.springframework.stereotype.Repository

/**
 * @author CrazyApeDX
 * Created on 2017/3/17.
 */
@Repository
interface CustomerRepo : AppRepo<Customer> {

	fun findByMobile(mobile: String): Customer

	fun countByMobile(mobile: String): Int

	fun findByFax(fax: String): Customer

	fun countByFax(fax: String): Int
}