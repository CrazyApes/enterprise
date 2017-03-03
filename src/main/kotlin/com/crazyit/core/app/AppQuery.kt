package com.crazyit.core.app

import com.crazyit.core.constant.enum.OrderType
import org.springframework.data.jpa.domain.Specification

/**
 * @author CrazyApeDX
 * Created on 2017/3/3.
 */
abstract class AppQuery<Entity : AppEntity>(
	var keywords: String? = null,
	var orderType: OrderType = OrderType.ASC,
	var orderProperty: String = "id"
) {

	abstract fun getCondition(): Specification<Entity>
}