package com.crazyit.foundation.role.query

import com.crazyit.core.app.AppQuery
import com.crazyit.core.constant.enum.OrderType
import com.crazyit.foundation.role.domain.Role
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.Path

/**
 * @author CrazyApeDX
 * Created on 2017/3/4.
 */
open class RoleQuery() : AppQuery<Role>() {

	constructor(keywords: String?) : this() {
		if (null != keywords && "" != keywords) {
			this.keywords = keywords
		}
	}

	constructor(keywords: String?, orderType: OrderType?, orderProperty: String?) : this() {
		if (null != keywords && "" != keywords) {
			this.keywords = keywords
		}
		if (null != orderType) {
			this.orderType = orderType
		}
		if (null != orderProperty && "" != orderProperty) {
			this.orderProperty = orderProperty
		}
	}

	override fun getCondition(): Specification<Role> {
		return Specification { root, query, builder ->
			if (null != keywords) {
				val path: Path<String> = root.get("title")
				query.where(builder.like(path, "%$keywords%"))
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