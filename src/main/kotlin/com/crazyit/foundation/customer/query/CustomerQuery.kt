package com.crazyit.foundation.customer.query

import com.crazyit.core.app.AppQuery
import com.crazyit.core.constant.enum.OrderType
import com.crazyit.foundation.customer.domain.Customer
import org.aspectj.weaver.tools.cache.SimpleCacheFactory.path
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.Path

/**
 * @author CrazyApeDX
 * Created on 2017/3/17.
 */
open class CustomerQuery() : AppQuery<Customer>() {

	constructor(keywords: String?, orderType: OrderType?, orderProperty: String?) : this() {
		this.keywords = keywords
		if (null != orderType) this.orderType = orderType
		if (null != orderProperty) this.orderProperty = orderProperty
	}

	override fun getCondition(): Specification<Customer> {
		return Specification { root, query, builder ->
			if (null != keywords) {
				val path1: Path<String> = root.get("name")
				val predicate1 = builder.like(path1, "%$keywords%")

				val path2: Path<String> = root.get("mobile")
				val predicate2 = builder.like(path2, "%$keywords%")

				val path3: Path<String> = root.get("address")
				val predicate3 = builder.like(path3, "%$keywords%")

				val path4: Path<String> = root.get("fax")
				val predicate4 = builder.like(path4, "%$keywords%")

				query.where(builder.or(predicate1, predicate2, predicate3, predicate4))
			}
			val property: Path<Any> = root.get(orderProperty)
			when(orderType) {
				OrderType.ASC -> query.orderBy(builder.asc(property))
				OrderType.DESC -> query.orderBy(builder.desc(property))
				else -> {}
			}
			query.restriction
		}
	}
}