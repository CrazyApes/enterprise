package com.crazyit.foundation.employee.query

import com.crazyit.core.app.AppQuery
import com.crazyit.core.constant.enum.EmployeeStatus
import com.crazyit.core.constant.enum.OrderType
import com.crazyit.core.constant.enum.Sex
import com.crazyit.foundation.employee.domain.Employee
import com.crazyit.foundation.role.domain.Role
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.*


/**
 * @author CrazyApeDX
 * Created on 2017/3/3.
 */
open class EmployeeQuery(
	var role: Role? = null,
	var sex: Sex? = null,
	var status: EmployeeStatus? = null
) : AppQuery<Employee>() {

	constructor(keywords: String?, orderType: OrderType?,
	            orderProperty: String?, role: Role?, sex: Sex?,
	            status: EmployeeStatus?) : this() {
		if (null != keywords && "" != keywords) {
			this.keywords = keywords
		}
		if (null != orderType) {
			this.orderType = orderType
		}
		if (null != orderProperty && "" != orderProperty) {
			this.orderProperty = orderProperty
		}
		if (null != role) {
			this.role = role
		}
		if (null != sex) {
			this.sex = sex
		}
		if (null != status && !EmployeeStatus.ALL.equals(status)) {
			this.status = status
		}
	}

	override fun getCondition(): Specification<Employee> {
		return Specification { root, query, builder ->
			if (null != keywords) {
				val path: Path<String> = root.get("name")
				query.where(builder.like(path, "%$keywords%"))
			}
			if (null != role) {
				val path: Path<Role> = root.get("role")
				query.where(builder.equal(path, role))
			}
			if (null != sex) {
				val path: Path<Sex> = root.get("sex")
				query.where(builder.equal(path, sex))
			}
			if (null != status) {
				val path: Path<EmployeeStatus> = root.get("status")
				query.where(builder.equal(path, status))
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