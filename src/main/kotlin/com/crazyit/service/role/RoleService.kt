package com.crazyit.service.role

import com.crazyit.core.constant.enum.OrderType
import org.springframework.http.ResponseEntity

/**
 * @author CrazyApeDX
 * Created on 2017/3/4.
 */
interface RoleService {

	fun create(title: String): ResponseEntity<String>

	fun remove(id: Long): ResponseEntity<String>

	fun load(id: Long): ResponseEntity<String>

	fun loadPage(keywords: String?, orderType: OrderType?,
	             orderProperty: String?, page: Int, size: Int): ResponseEntity<String>
}