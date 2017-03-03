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
	var role: Role?,
	var sex: Sex?,
	var status: EmployeeStatus?
) : AppQuery<Employee>() {

	override fun getCondition(): Specification<Employee> {
		return Specification { root, query, builder ->
			if (null != keywords) {
				val express: Path<String> = root!!.get("name")
				query!!.where(builder.like(express, "%$keywords%"))
			}
			if (null != role) {
				val express: Path<Role> = root!!.get("role")
				query!!.where(builder!!.equal(express, role))
			}
			if (null != sex) {
				val expression: Path<Sex> = root!!.get("sex")
				query!!.where(builder!!.equal(expression, sex))
			}
			if (null == status) {
				val expression: Path<EmployeeStatus> = root!!.get("status")
				query!!.where(builder!!.equal(expression, status))
			}
			val property: Path<Any> = root.get(orderProperty)
			when(orderType) {
				OrderType.ASC -> query!!.orderBy(builder!!.asc(property))
				OrderType.DESC -> query!!.orderBy(builder!!.desc(property))
				else -> {}
			}
			query!!.restriction
		}
	}

}